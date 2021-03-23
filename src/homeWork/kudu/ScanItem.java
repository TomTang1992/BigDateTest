package org.spiral.kudedemo;

import org.apache.kudu.client.*;

/**
 * @author : spiral
 * @description : 查询数据
 * @since : 2020/12/16 - 5:25 下午
 */
public class ScanItem {
    public static void main(String[] args) {
        try (KuduClient client = new KuduClient.KuduClientBuilder("master")
                .defaultSocketReadTimeoutMs(6000).build()) {
            final KuduTable student = client.openTable("student");
            KuduScanner scanner = client.newScannerBuilder(student).build();
            while (scanner.hasMoreRows()) {
                for (RowResult nextRow : scanner.nextRows()) {
                    System.out.println(nextRow.getInt("id") + "----" + nextRow
                            .getString("name"));
                }
            }
        } catch (KuduException e) {
            e.printStackTrace();
        }
    }
}
