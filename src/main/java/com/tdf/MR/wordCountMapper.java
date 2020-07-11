package com.tdf.MR;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

/*
 * wordCount 实现类
 * 一、Map阶段：1、Mapf()方法把传入的数据转为String类型
 *  2、根据空格分隔单词
 *  3、输出<单词，1>
 *二、Mapper类的泛型：共4个，2对KV
 *LongWritable,Text:文本偏移量，文本内容:文本偏移量，后面不会用到
 * Text, IntWritable：单词，数量
 *
 */
public class wordCountMapper extends Mapper<LongWritable, Text,Text, IntWritable> {
    /* 重写Map方法
       1、接受文本内容
       2、按照空格分隔
       3、输出单词,1
     */
    //接受文本内容，转为String类型
    //提升为全局变量，避免每次执行map方法都生成
    final  Text word = new Text();
    IntWritable one = new IntWritable(1);
    @Override
    protected void map(LongWritable key, Text value, Context context) throws IOException, InterruptedException {
        //接受文本内容，转成String类型
        final  String str = value.toString();
        //用空格分隔单词
        final String[] words = str.split(" ");
        //输出单词

        //遍历数据
        for (String s:words
             ) {
            word.set(s);
            context.write(word,one);
        }

    }
}
