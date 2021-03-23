package org.spiral.kudedemo;

import org.apache.kudu.client.*;

/**
 * @description : 更改表数据
 */
public class UpdateItem {
    public static void main(String[] args) {
        try (final KuduClient client = new KuduClient.KuduClientBuilder(
                "master").defaultSocketReadTimeoutMs(5000).build()) {
            final KuduTable student = client.openTable("student");
            final KuduSession session = client.newSession();
            session.setFlushMode(SessionConfiguration.FlushMode.MANUAL_FLUSH);
            final Update update = student.newUpdate();
            final PartialRow row = update.getRow();
            row.addString("name", "Hello");
            row.addInt("id", 1);
            session.flush();
            session.apply(update);
            session.close();
        } catch (KuduException e) {
            e.printStackTrace();
        }
    }
}
