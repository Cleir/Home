package cn.cleir.home.controller;

import org.junit.Test;
import redis.clients.jedis.Jedis;

public class JedisDemo {

    @Test
    public void demo1(){
        //设置地址端口号
        Jedis jedis = new Jedis("192.168.31.79", 6379);
        //保存数据
        jedis.set("name", "cleir");
        //获取数据
        String value = jedis.get("name");
        System.out.println(value);
        jedis.close();
    }

}
