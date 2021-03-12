package org.spiral.druid.flink;

import com.alibaba.fastjson.JSON;
import org.apache.flink.api.common.functions.FlatMapFunction;
import org.apache.flink.api.common.functions.MapFunction;
import org.apache.flink.api.common.serialization.SimpleStringSchema;
import org.apache.flink.api.common.typeinfo.Types;
import org.apache.flink.streaming.api.datastream.DataStreamSource;
import org.apache.flink.streaming.api.datastream.SingleOutputStreamOperator;
import org.apache.flink.streaming.api.environment.StreamExecutionEnvironment;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaConsumer;
import org.apache.flink.streaming.connectors.kafka.FlinkKafkaProducer;
import org.apache.flink.util.Collector;
import org.spiral.druid.bean.OrderBean;
import org.spiral.druid.bean.OrdersBean;
import org.spiral.druid.bean.ProductBean;

import java.util.List;
import java.util.Properties;

/**
 * 使用Flink将orders扁平化为Order
 *
 * @author : spiral
 * @since : 2020/12/26 - 下午4:19
 */
public class OrdersToOrderWithFlink {
    public static void main(String[] args) throws Exception {
        final StreamExecutionEnvironment env = StreamExecutionEnvironment
                .getExecutionEnvironment();
        Properties properties = new Properties();
        properties.setProperty("bootstrap.servers",
                "master:9092,slave2:9092,slave0:9092");
        FlinkKafkaConsumer<String> consumer = new FlinkKafkaConsumer<>(
                "flink_input", new SimpleStringSchema(), properties);
        DataStreamSource<String> data = env.addSource(consumer);
        final SingleOutputStreamOperator<String> outputStream = data
                .flatMap(new OrdersToOrderFlatMap())
                .map((MapFunction<OrderBean, String>) JSON::toJSONString)
                .returns(Types.STRING);
        outputStream.print();

        final FlinkKafkaProducer<String> druid_input = new FlinkKafkaProducer<>(
                "druid_input", new SimpleStringSchema(), properties);
        outputStream.addSink(druid_input);
        env.execute();
    }

    static class OrdersToOrderFlatMap
            implements FlatMapFunction<String, OrderBean> {
        @Override
        public void flatMap(String value, Collector<OrderBean> out)
                throws Exception {
            final OrdersBean orders = JSON.parseObject(value, OrdersBean.class);
            final List<ProductBean> products = orders.getProducts();
            for (ProductBean product : products) {
                out.collect(OrderBean.of(orders, product));
            }
        }
    }
}


