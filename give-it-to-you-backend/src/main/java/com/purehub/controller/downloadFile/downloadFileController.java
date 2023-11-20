package com.purehub.controller.downloadFile;

import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;

@RestController
public class downloadFileController {

    @GetMapping("/download")

    public ResponseEntity<InputStreamResource> downloadFile() throws IOException {
        // 获取要下载的文件
        String currentPath = System.getProperty("user.dir"); // 获取当前项目路径
        File currentDir = new File(currentPath);
        File parentDir = currentDir.getParentFile(); // 获取当前项目的父目录
        // 构造 image 目录的路径
        File file = new File(parentDir, "image/purexu.jpg");

        // 设置响应头信息
        HttpHeaders headers = new HttpHeaders();
        headers.add(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=purexu.jpg");

        // 创建文件输入流
        FileInputStream fis = new FileInputStream(file);

        // 返回带有文件流的 ResponseEntity
        return ResponseEntity
                .ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_OCTET_STREAM)
                .body(new InputStreamResource(fis));
    }
}
