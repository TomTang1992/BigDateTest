package com.tdf.homeWork;
import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.io.LongWritable;
import org.apache.hadoop.io.Text;
import org.apache.hadoop.mapreduce.Mapper;

import java.io.IOException;

public class HomeMapper extends
        Mapper<LongWritable, Text, IntWritable, IntWritable> {

    private static IntWritable data = new IntWritable();
    private static final IntWritable one = new IntWritable(1);

    @Override
    protected void map(LongWritable key, Text value, Context context)
            throws IOException, InterruptedException {
        String line = value.toString();
        data.set(Integer.parseInt(line));
        context.write(data, one);
    }
}
