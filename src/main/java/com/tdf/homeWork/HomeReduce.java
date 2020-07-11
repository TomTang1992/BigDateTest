package com.tdf.homeWork;

import org.apache.hadoop.io.IntWritable;
import org.apache.hadoop.mapreduce.Reducer;

import java.io.IOException;
import java.util.Iterator;

public class HomeReduce extends Reducer <IntWritable,IntWritable,IntWritable,IntWritable>{
    private static IntWritable linenumber = new IntWritable(1);
    @Override
    protected void reduce(IntWritable key, Iterable<IntWritable> values, Context context) throws IOException, InterruptedException {
        for (IntWritable value : values) {
            context.write(linenumber, key);
            linenumber.set(linenumber.get() + 1);
        }
    }
}
