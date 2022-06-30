package com.example.my_shop.config;


import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.nio.file.Path;
import java.nio.file.Paths;

@Configuration
@EnableWebMvc
public class MVCConfig implements WebMvcConfigurer {

    @Value("{$upload.path}")
    private String uploadPath;

    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
       registry.addResourceHandler("/styles/css/**")
               .addResourceLocations("classpath:/static/css/");
       registry.addResourceHandler("/static/**")//?
                .addResourceLocations("classpath:/static/");
        Path productUploadDir = Paths.get("./product-filename/");
        String productUploadPath =  productUploadDir.toFile().getAbsolutePath();
        registry.addResourceHandler("/product-filename/**")
                .addResourceLocations("/file:/"+ productUploadPath+ "/");
    }
}
