package org.spiral.druid.flink;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;
import org.apache.kafka.common.serialization.StringSerializer;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

/**
 * kafka生产者
 *
 * @author : spiral
 * @since : 2020/12/26 - 下午4:18
 */
public class FlinkInputKafkaProducer {
    public static void main(String[] args) {
        String broker = "master:9092,slave0:9092";
        String topic = "flink_input";
        final Properties prop = new Properties();

        prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, broker);
        prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);
        prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG,
                StringSerializer.class);

        KafkaProducer<String, String> producer = new KafkaProducer<>(prop);


        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(
                new File("src/main/resources/data/orders.json")))) {
            String line;
            while ((line = bufferedReader.readLine()) != null) {
                System.out.println(line);
                ProducerRecord<String, String> record = new ProducerRecord<>(
                        topic, line);
                producer.send(record);
                Thread.sleep(1000);
            }
        } catch (IOException | InterruptedException e) {
            e.printStackTrace();
        }

        producer.close();
    }
}
