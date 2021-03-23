package org.spiral.kudedemo;

import org.apache.kudu.ColumnSchema;
import org.apache.kudu.Schema;
import org.apache.kudu.Type;
import org.apache.kudu.client.CreateTableOptions;
import org.apache.kudu.client.KuduClient;
import org.apache.kudu.client.KuduException;

import java.util.LinkedList;
import java.util.List;

/**
 * @author : spiral
 * @description : 使用API创建表
 * @since : 2020/12/16 - 2:57 下午
 */
public class CreateTable {

    /**
     * @param name  列名
     * @param type  数据类型
     * @param isKey 是否主键
     * @return 列信息
     */
    private static ColumnSchema newColumn(String name, Type type,
            Boolean isKey) {
        ColumnSchema.ColumnSchemaBuilder columnSchemaBuilder = new ColumnSchema.ColumnSchemaBuilder(
                name, type);
        columnSchemaBuilder.key(isKey);
        return columnSchemaBuilder.build();
    }

    public static void main(String[] args) throws KuduException {
        String masterAddr = "master";

        //创建kudu的数据库连接

        try (KuduClient client = new KuduClient.KuduClientBuilder(masterAddr)
                .defaultSocketReadTimeoutMs(3000).build()) {
            //设置表Schema
            List<ColumnSchema> columns = new LinkedList<ColumnSchema>();
            columns.add(newColumn("id", Type.INT32, true));
            columns.add(newColumn("name", Type.STRING, false));
            Schema schema = new Schema(columns);
            CreateTableOptions options = new CreateTableOptions();
            List<String> columnNames = new LinkedList<String>();
            columnNames.add("id");
            options.setNumReplicas(1);
            options.addHashPartitions(columnNames, 3);
            client.createTable("student", schema, options);
        }
    }
}
