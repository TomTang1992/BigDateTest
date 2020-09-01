#!/bin/bash

time=$(date "+%M")
sub=$[$time/3]

if [ $sub -eq 0 ];then
  echo "$sub, 20200621"
  dt="20200621"
elif [ $sub -eq 1 ];then
  echo "$sub, 20200622"
  dt="20200622"
else
  dt="20200623"
  echo "$sub, 20200623"
fi

sql='load data inpath "/data/'$dt'/clicklog.dat" into table homework.user_clicks PARTITION(dt='$dt');'
echo $sql > /root/job/import.sql
