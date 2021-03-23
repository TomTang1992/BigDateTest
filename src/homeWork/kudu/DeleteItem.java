package org.spiral.kudedemo;

import org.apache.kudu.client.*;

/**
 * @author : spiral
 * @description : 删除指定行
 * @since : 2020/12/16 - 5:32 下午
 */
public class DeleteItem {
    public static void main(String[] args) {
        try (final KuduClient client = new KuduClient.KuduClientBuilder(
                "master").defaultSocketReadTimeoutMs(5000).build()) {
            final KuduTable student = client.openTable("student");
            final KuduSession session = client.newSession();
            session.setFlushMode(SessionConfiguration.FlushMode.MANUAL_FLUSH);
            final Delete delete = student.newDelete();
            final PartialRow row = delete.getRow();
            row.addInt("id", 1);
            session.flush();
            session.apply(delete);
            session.close();
        } catch (KuduException e) {
            e.printStackTrace();
        }
    }
}
