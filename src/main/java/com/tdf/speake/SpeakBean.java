package com.tdf.speake;

import org.apache.hadoop.io.Writable;

import java.io.DataInput;
import java.io.DataOutput;
import java.io.IOException;

//这个类型是Map输出中KV的类型，需要实现writeble 序列化接口
public class SpeakBean implements Writable {
//    定义属性
    private long  selfDuration;//自由内容时常
    private long  thirdPatDuration;//第三方时常
    private long  sumDuration;//总时长
    private String deviceID;//设备ID

    public String getDeviceID() {
        return deviceID;
    }

    public void setDeviceID(String deviceID) {
        this.deviceID = deviceID;
    }

    //序列化方法
    @Override
    public void write(DataOutput out) throws IOException {
        out.writeLong(selfDuration);
        out.writeLong(thirdPatDuration);
        out.writeUTF(deviceID);
        out.writeLong(sumDuration);

    }
    //空参构造
    public SpeakBean() {
    }
   //有参构造

    public void set(long selfDuration, long thirdPartDuration) {
        this.selfDuration = selfDuration;
        this.thirdPatDuration = thirdPartDuration;
        this.sumDuration=this.selfDuration+this.thirdPatDuration;
    }


    //反序列化方法
    @Override
    public void readFields(DataInput in) throws IOException {
        this.selfDuration=in.readLong();
        this.thirdPatDuration=in.readLong();
        this.deviceID=in.readUTF();
        this.sumDuration=in.readLong();
    }

    public long getSelfDuration() {
        return selfDuration;
    }

    public long getThirdPatDuration() {
        return thirdPatDuration;
    }

    public long getSumDuration() {
        return sumDuration;
    }

    public void setSelfDuration(long selfDuration) {
        this.selfDuration = selfDuration;
    }

    public void setThirdPatDuration(long thirdPatDuration) {
        this.thirdPatDuration = thirdPatDuration;
    }

    public void setSumDuration(long sumDuration) {
        this.sumDuration = sumDuration;
    }

    @Override
    public String toString() {
        return
                "\t" + selfDuration +
                "\t " + thirdPatDuration +
                "\t" + sumDuration +
                "\t'" + deviceID ;
    }
}
