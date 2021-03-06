
object JDBCSinkDemo {
        def main(args: Array[String]): Unit = {
        //1.创建SparkSession
        val spark: SparkSession = SparkSession.builder().master("local[*]").appName("SparkSQL").getOrCreate()
        val sc: SparkContext = spark.sparkContext
        sc.setLogLevel("WARN")
import spark.implicits._
//2.连接Kafka消费数据
    val dataDF: DataFrame = spark.readStream
            .format("kafka")
            .option("kafka.bootstrap.servers", "node01:9092")
            .option("subscribe", "spark_kafka")
            .load()
            //3.处理数据
            //注意:StructuredStreaming整合Kafka获取到的数据都是字节类型,所以需要按照官网要求,转成自己的实际类型
            val dataDS: Dataset[String] = dataDF.selectExpr("CAST(value AS STRING)").as[String]
            val wordDS: Dataset[String] = dataDS.flatMap(_.split(" "))
            val result: Dataset[Row] = wordDS.groupBy("value").count().sort($"count".desc)
            val writer = new JDBCSink("jdbc:mysql://localhost:3306/bigdata?characterEncoding=UTF-8", "root", "root")
            result.writeStream
            .foreach(writer)
            .outputMode("complete")
            .trigger(Trigger.ProcessingTime(0))
            .start()
            .awaitTermination()
            }

class JDBCSink(url:String,username:String,password:String) extends ForeachWriter[Row] with Serializable{
        var connection:Connection = _ //_表示占位符,后面会给变量赋值
        var preparedStatement: PreparedStatement = _
        //开启连接
        override def open(partitionId: Long, version: Long): Boolean = {
        connection = DriverManager.getConnection(url, username, password)
        true
        }

    /*
    CREATE TABLE `t_word` (
        `id` int(11) NOT NULL AUTO_INCREMENT,
        `word` varchar(255) NOT NULL,
        `count` int(11) DEFAULT NULL,
        PRIMARY KEY (`id`),
        UNIQUE KEY `word` (`word`)
      ) ENGINE=InnoDB AUTO_INCREMENT=26 DEFAULT CHARSET=utf8;
     */
        //replace INTO `bigdata`.`t_word` (`id`, `word`, `count`) VALUES (NULL, NULL, NULL);
        //处理数据--存到MySQL
        override def process(row: Row): Unit = {
        val word: String = row.get(0).toString
        val count: String = row.get(1).toString
        println(word+":"+count)
        //REPLACE INTO:表示如果表中没有数据这插入,如果有数据则替换
        //注意:REPLACE INTO要求表有主键或唯一索引
        val sql = "REPLACE INTO `t_word` (`id`, `word`, `count`) VALUES (NULL, ?, ?);"
        preparedStatement = connection.prepareStatement(sql)
        preparedStatement.setString(1,word)
        preparedStatement.setInt(2,Integer.parseInt(count))
        preparedStatement.executeUpdate()
        }

        //关闭资源
        override def close(errorOrNull: Throwable): Unit = {
        if (connection != null){
        connection.close()
        }
        if(preparedStatement != null){
        preparedStatement.close()
        }
        }
        }
        }
