package org.spiral.kudedemo;

import org.apache.kudu.client.*;

/**
 * @description : 插入数据案例
 */
public class InsertItem {
    public static void main(String[] args) {
        try (KuduClient client = new KuduClient.KuduClientBuilder("master")
                .defaultSocketReadTimeoutMs(6000).build();) {
            final KuduTable student = client.openTable("student");
            final KuduSession session = client.newSession();
            session.setFlushMode(SessionConfiguration.FlushMode.MANUAL_FLUSH);
            session.setMutationBufferSpace(300);
            for (int i = 0; i < 10; i++) {
                final Insert insert = student.newInsert();
                final PartialRow row = insert.getRow();
                row.addInt("id", i);
                row.addString("name", "name" + i);
                session.flush();
                session.apply(insert);
            }
            session.close();
        } catch (KuduException e) {
            e.printStackTrace();
        }
    }
}
