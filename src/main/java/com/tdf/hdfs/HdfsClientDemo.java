package com.tdf.hdfs;

import org.apache.hadoop.conf.Configuration;
import org.apache.hadoop.fs.FileSystem;
import org.apache.hadoop.fs.Path;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.net.URI;
import java.net.URISyntaxException;


public class HdfsClientDemo {
    FileSystem fileSystem=null;
    @Before
    public void Init() throws URISyntaxException, IOException, InterruptedException {
        //1、获取configration对象
        Configuration configuration = new Configuration();
       // configuration.set("fs.defaultFs","hdfs://linux121:9000");
        //configuration.set("dfs.replication","2");
        //设置通过域名访问datanode：解决上传文件连接超时的问题：https://www.cnblogs.com/krcys/p/9146329.html
        configuration.set("dfs.client.use.datanode.hostname", "true");
        //2、根据configuration获取FileSystem
        configuration.set("dfs.client.use.datanode.hostname", "true");
        fileSystem = FileSystem.get(new URI("hdfs://linux121:9000"),configuration,"root");
    }

    @After
    public  void destory() throws IOException {
        fileSystem.close();
    }

    @Test
    public  void testMkeDirs() throws URISyntaxException, IOException, InterruptedException {
        fileSystem.mkdirs(new Path("/api_test2"));
    }
    //上传文件
    @Test
    public  void uplodeFile() throws URISyntaxException, IOException, InterruptedException {
        //目标文件路径，hdfs文件路径
        fileSystem.copyFromLocalFile(new Path("e:/uplode.txt"),new Path("/uplode.txt"));
    }
    //下载文件
    @Test
    public  void downLodeFile() throws IOException {
       // fileSystem.copyToLocalFile(true,new Path("/uplode.txt")),new Path("e:uplode_copy.txt"));
        fileSystem.copyToLocalFile(false, new Path("/uplode.txt"), new Path("e:/uplode.txt"), true);
    }
}
