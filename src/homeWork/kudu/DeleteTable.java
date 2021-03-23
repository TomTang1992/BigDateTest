package org.spiral.kudedemo;

import org.apache.kudu.client.KuduClient;
import org.apache.kudu.client.KuduException;

/**
 * @description : 删除表操作
 */
public class DeleteTable {
    public static void main(String[] args) {
        KuduClient.KuduClientBuilder kuduClientBuilder =
                new KuduClient.KuduClientBuilder("master")
                .defaultSocketReadTimeoutMs(5000);
        try(KuduClient client = kuduClientBuilder.build()) {
            client.deleteTable("student");
        } catch (KuduException e) {
            e.printStackTrace();
        }
    }
}
