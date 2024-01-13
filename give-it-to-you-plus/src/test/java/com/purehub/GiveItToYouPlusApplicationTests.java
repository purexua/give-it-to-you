package com.purehub;

import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.poi.xwpf.usermodel.XWPFParagraph;
import org.apache.poi.xwpf.usermodel.XWPFRun;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.redis.core.RedisTemplate;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
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
  @Test
  void test002() throws IOException {
    XWPFDocument document = new XWPFDocument();

    // 创建一个段落对象
    XWPFParagraph paragraph = document.createParagraph();
    XWPFRun run = paragraph.createRun();

    // 设置文本内容
    run.setText("Hello, World!");

    // 输出到文件
    FileOutputStream out = new FileOutputStream("output.docx");
    document.write(out);
    out.close();

    System.out.println("Word文档生成成功！");
  }

}
