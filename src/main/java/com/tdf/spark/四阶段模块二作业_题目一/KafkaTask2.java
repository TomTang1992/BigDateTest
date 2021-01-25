package com.tdf.homeWork;


-- KafkaTask2
/*
    KafkaTask2
 */
        object KafkaTask2 {
            def main(args: Array[String]): Unit = {
            val conf: SparkConf = new SparkConf()
            .setAppName("spark kafka consumer")
            .setMaster("local[*]")
            val ssc = new StreamingContext(conf, Seconds(2))

            val kafkaParams: Map[String, Object] = KafkaConfig.getKafkaConsumerParameters("spark-consumer1")
            val topics: Array[String] = Array("spark-task-topic1")

            val dstream = KafkaUtils.createDirectStream(
            ssc,
            LocationStrategies.PreferConsistent,
            ConsumerStrategies.Subscribe[Long, String](topics, kafkaParams)
        )

            val gson = new Gson

            val properties = KafkaConfig.getProducerParameters()
            val producer = new KafkaProducer[Long, String](properties)
            val topic = "spark-task-topic2"
            var key = 0

            dstream.foreachRDD{(rdd, time) =>
            if (!rdd.isEmpty()) {
            println(s"****** rdd.count = ${rdd.count()}; time = $time ******")

            val logs = rdd.map{record => Log(record.value())}
            .collect()

            logs.foreach{log =>
            key += 1
            val msg = new ProducerRecord[Long, String](topic, key, gson.toJson(log))
            producer.send(msg)
        }
        }
        }

        ssc.start()
        ssc.awaitTermination()

        producer.close()
        }
        }