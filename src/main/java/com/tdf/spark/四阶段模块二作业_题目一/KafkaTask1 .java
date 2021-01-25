package com.tdf.homeWork;


-- KafkaTask1 
/*
    KafkaTask1
 */
    object KafkaTask1 {
    def main(args: Array[String]): Unit = {
        // KafkaProducer
        val properties = KafkaConfig.getProducerParameters()
        val producer = new KafkaProducer[Long, String](properties)
                val topic = "spark-task-topic1"

        // 读取文本日志
        val source = Source.fromFile("lagou-data/sample.log")

        var key = 0L
        val lines = source.getLines()

        // 将日志逐行写入 kafka
        lines.foreach{line =>
            key += 1
            val msg = new ProducerRecord[Long, String](topic, key, line)
            producer.send(msg)
            println(s"key = $key, message = $line")
            Thread.sleep(1000)
        }

        source.close()
        producer.close()
    }
}