package com.tdf.homeWork;


-- KafkaConfig类
/*
    KafkaConfig类
 */
object KafkaConfig {
        val BROKERS = "izuf66rzw9m809spfn9yuaz:9092,izuf66rzw9m809spfn9yubz:9092,izuf66rzw9m809spfn9yucz:9092"

        def getProducerParameters(): Properties = {
            val prop = new Properties()
            prop.put(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, BROKERS)
            prop.put(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, classOf[LongSerializer])
            prop.put(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, classOf[StringSerializer])
            prop
        }

        def getKafkaConsumerParameters(groupId: String): Map[String, Object] = {
        Map[String, Object](
            ConsumerConfig.BOOTSTRAP_SERVERS_CONFIG -> BROKERS,
            ConsumerConfig.KEY_DESERIALIZER_CLASS_CONFIG -> classOf[LongDeserializer],
            ConsumerConfig.VALUE_DESERIALIZER_CLASS_CONFIG -> classOf[StringDeserializer],
            ConsumerConfig.GROUP_ID_CONFIG -> groupId,
            ConsumerConfig.AUTO_OFFSET_RESET_CONFIG -> "earliest",
            ConsumerConfig.ENABLE_AUTO_COMMIT_CONFIG -> (false: java.lang.Boolean)
        )
        }
}