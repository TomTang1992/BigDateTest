package com.tdf.zookeeper;
import org.I0Itec.zkclient.IZkChildListener;
import org.I0Itec.zkclient.IZkDataListener;
import org.I0Itec.zkclient.ZkClient;
import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FSDataInputStream;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IOUtils;
import org.apache.zookeeper.data.StatPersistedV1;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;
public class zkClient {
    private ZkClient zkClient = null;
    FileSystem fs = null;
    Configuration configuration = null;
    FSDataInputStream in = null;

    String path = "/etc";
    String zk_conf = "mr00:2181, mr01:2181, mr02:2181";

    @Before
    public void init() throws IOException, URISyntaxException, InterruptedException {
        // 获取hdfs对象
        configuration = new Configuration();
        fs = FileSystem.get(new URI("hdfs://mr00:9000"), configuration, "root");
        // 初始化配置文件
        Path path = new Path("/conf/mysql.txt");
        in = fs.open(path);
        final BufferedReader br = new BufferedReader(new InputStreamReader(in, "utf-8"));
        final String mysqlconf = br.readLine();


        // 获取zk对象
        zkClient = new ZkClient(zk_conf);
        // 同步配置文件到zk
        if(!zkClient.exists(this.path)){
            zkClient.createPersistent(this.path, true);
        }
        zkClient.writeData("/etc", mysqlconf);
    }

    @After
    public void destory() throws IOException {
        zkClient.close();
        fs.close();

    }

    @Test
    public void confListener() throws InterruptedException {
        zkClient.subscribeChildChanges(path, new IZkChildListener() {
            @Override
            public void handleChildChange(final String s, final List<String> list) throws Exception {

            }
        });


        zkClient.subscribeDataChanges(path, new IZkDataListener() {
            @Override
            public void handleDataChange(final String dataPath, final Object data) throws Exception {
                System.out.println("Mysql configuration is changed, new conf is : " + data);
            }

            @Override
            public void handleDataDeleted(final String dataPath) throws Exception {

                System.out.println(dataPath + "is deleted. Unable to connect Mysql.");
            }
        });

        final Object o = zkClient.readData("/etc");
        System.out.println("Mysql configuration is:" + o);

        zkClient.writeData("/etc", "192.168.0.240,192.168.0.241");
        Thread.sleep(1000);

    }

}
