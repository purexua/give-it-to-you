package com.purehub;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.util.concurrent.TimeUnit;

@SpringBootTest
class GiveItToYouPlusApplicationTests {
  @Autowired
  private RedisTemplate redisTemplate;
  @Test
  void contextLoads() {
    System.out.println(java.time.LocalDateTime.now());
  }
  @Test
  void testRedis(){
    Thread thread = new Thread();
    thread.start();
    redisTemplate.opsForHash().put("myMap", "map1", "hhhh");
    redisTemplate.expire("myMap", 10, TimeUnit.SECONDS);
    System.out.println(redisTemplate.opsForHash().get("myMap","map1"));
    try {
      thread.sleep(10001);
    } catch (InterruptedException e) {
      throw new RuntimeException(e);
    }
    System.out.println(redisTemplate.opsForHash().get("myMap","map1"));
  }
}
