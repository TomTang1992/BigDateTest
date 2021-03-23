package org.spiral.kudedemo;

import org.apache.kudu.client.*;

/**
 * @description : 查询数据
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
