package com.zhifa.bigfileupload.config;

import com.zhifa.bigfileupload.constance.FileConstance;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
/*
* 静态资源映射配置
* */
@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        //http://localhost/f/文件全名就能映射本地的文件映射
        registry.addResourceHandler("/f/**").addResourceLocations("file:" + FileConstance.FILE_PATH);
    }
}