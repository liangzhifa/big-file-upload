package com.zhifa.bigfileupload;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
@MapperScan("com.zhifa.bigfileupload.mapper")
public class BigFileUploadApplication {

    public static void main(String[] args) {
        SpringApplication.run(BigFileUploadApplication.class, args);
    }

}
