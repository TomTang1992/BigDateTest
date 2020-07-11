package com.tdf.MR;


import org.apache.hadoop.conf.Configuration;

import org.apache.hadoop.fs.Path;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Job;
import org.apache.hadoop.mapreduce.lib.input.FileInputFormat;
import org.apache.hadoop.mapreduce.lib.output.FileOutputFormat;

import java.io.IOException;

//封装任务，并提交运行
public class wordCountDriver {

    public static void main(String[] args) throws IOException, ClassNotFoundException, InterruptedException {
    /*
        1. 获取配置文件对象，获取job对象实例
        2. 指定程序jar的本地路径
        3. 指定Mapper/Reducer类
        4. 指定Mapper输出的kv数据类型
        5. 指定最终输出的kv数据类型
        6. 指定job处理的原始数据路径
        7. 指定job输出结果路径
        8. 提交作业
     */
//        1. 获取配置文件对象，获取job对象实例
        final  Configuration configuration = new Configuration();
        final  Job job = Job.getInstance(configuration,"wordCountDriver");
//        2. 指定程序jar的本地路径
        job.setJarByClass(wordCountDriver.class);
//        3. 指定Mapper/Reducer类
        job.setMapperClass(wordCountMapper.class);
        job.setReducerClass(wordCountReduce.class);
//        4. 指定Mapper输出的kv数据类型
        job.setMapOutputKeyClass(Text.class);
        job.setMapOutputValueClass(IntWritable.class);
//        5. 指定最终输出的kv数据类型
        job.setOutputKeyClass(Text.class);
        job.setOutputValueClass(IntWritable.class);
//        6. 指定job处理的原始数据路径
        FileInputFormat.setInputPaths(job,new Path(args[0]));
//        7. 指定job输出结果路径
        FileOutputFormat.setOutputPath(job,new Path(args[1]));
//        8. 提交作业
        final  boolean flag = job.waitForCompletion(true);
        System.exit(flag?0:1);

    }

}
