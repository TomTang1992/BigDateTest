package com.tdf.MR;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
/*
    reduce 也有4个参数，两对K,V
    第一对K,V的类型需要与Mapper的输出类型一直
    第二对K,V类型与结果一直
 */

public class wordCountReduce  extends Reducer<Text, IntWritable,Text,IntWritable>{
    IntWritable total=new IntWritable();
    @Override
    protected void reduce(Text key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        int sum= 0;
        for (IntWritable value:
        values) {
            int i=value.get();
            sum +=1;
        }
        total.set(sum);
        context.write(key,total);
    }
}
