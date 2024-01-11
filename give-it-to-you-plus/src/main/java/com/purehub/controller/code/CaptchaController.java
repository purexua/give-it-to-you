package com.purehub.controller.code;


import com.google.code.kaptcha.Producer;
import com.purehub.common.result.R;
import jakarta.servlet.http.HttpServletRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.Base64;
import java.util.HashMap;
import java.util.Map;
import java.io.ByteArrayOutputStream;

@RestController
@Slf4j
public class CaptchaController {
    @Autowired
    protected Producer captchaProducer;
    @GetMapping("/captcha")
    public R getCaptcha(HttpServletRequest request) throws IOException {
        // 创建验证码文本
        String capText = captchaProducer.createText();
        // 将验证码文本设置到 session
        request.getSession().setAttribute("captcha", capText);
        // 创建验证码图片
        BufferedImage bi = captchaProducer.createImage(capText);

        // 将验证码图片转换成 Base64 编码字符串
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        ImageIO.write(bi, "jpg", baos);
        byte[] imageBytes = baos.toByteArray();
        String base64Image = Base64.getEncoder().encodeToString(imageBytes);

        // 构建返回的 JSON 数据
        Map<String, String> response = new HashMap<>();
        response.put("captcha", capText);
        response.put("img", "data:image/jpeg;base64," + base64Image);

        log.info("验证码是:" + capText);
        // 将 JSON 数据转换成字符串并返回
        return R.ok(response);
    }
}
