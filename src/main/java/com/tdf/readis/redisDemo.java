package com.tdf.readis;
import org.junit.Test;
import redis.clients.jedis.HostAndPort;
import redis.clients.jedis.Jedis;
import redis.clients.jedis.JedisCluster;
import redis.clients.jedis.JedisPoolConfig;

import java.util.HashSet;
import java.util.Set;
public class redisDemo {
    @Test
    public void testCluster() {

        JedisPoolConfig config = new JedisPoolConfig();

        Set<HostAndPort> jedisClusterNode = new HashSet<>();
        jedisClusterNode.add(new HostAndPort("47.93.220.37", 7001));
        jedisClusterNode.add(new HostAndPort("47.93.220.37", 7002));
        jedisClusterNode.add(new HostAndPort("47.93.220.37", 7003));
        jedisClusterNode.add(new HostAndPort("47.93.220.37", 7004));
        jedisClusterNode.add(new HostAndPort("47.93.220.37", 7005));
        jedisClusterNode.add(new HostAndPort("47.93.220.37", 7006));
        JedisCluster jcd = new JedisCluster(jedisClusterNode, config);
        jcd.set("name:001","donald");

        String value = jcd.get("name:001");

        System.out.println(value);
    }
}
