package com.hyh.config;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;


@Configuration
public class CorsConfig implements WebMvcConfigurer {
       @Override
         public void addCorsMappings(CorsRegistry registry) {
           registry.addMapping("/**") // 所有接口
                   .allowedOrigins("http://localhost") // 支持域
                   .allowedOrigins("http://staryh.me") // 支持域
                   .allowCredentials(true) // 是否发送 Cookie
                   .allowedOriginPatterns("*") // 支持域
                   .allowedMethods("GET", "POST", "PUT", "DELETE") // 支持方法
                   .allowedHeaders("*")
                   .exposedHeaders("*");
         }
}
