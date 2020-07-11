package com.tdf.speake;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class SpeakMap  extends Mapper<LongWritable,Text,Text, SpeakBean> {
    SpeakBean v = new SpeakBean();
    Text k = new Text();
    public SpeakMap(LongWritable key,Text value,Context context) throws IOException, InterruptedException {
//        获取一行
        String  line=value.toString();
        //分隔字符
        String[] fileds=line.split("/t");
        // 3 封装对象
// 取出设备id
        String deviceId = fileds[1];
    // 取出自有和第三方时长数据
        long selfDuration = Long.parseLong(fileds[fileds.length - 3]);
        long thirdPartDuration = Long.parseLong(fileds[fileds.length - 2]);
        k.set(deviceId);
        v.set(selfDuration,thirdPartDuration);
// 4 写出
        context.write(k, v);

    }
}
