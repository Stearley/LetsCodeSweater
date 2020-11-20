package com.ippsby.letscode.config;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.ResourceHandlerRegistry;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class MvcConfig implements WebMvcConfigurer {
    @Value("${upload.path}")
    private String uploadPath;


    public void addViewControllers(ViewControllerRegistry registry) {

        registry.addViewController("/login").setViewName("login.ftlh");
        registry.addViewController("/login").setViewName("login");
        registry.addViewController("/main").setViewName("main.ftlh");
        registry.addViewController("/registration").setViewName("registration.ftlh");

    }
    @Override
    public void addResourceHandlers(ResourceHandlerRegistry registry) {
        registry.addResourceHandler("C:/Work/")
                .addResourceLocations("file://" + uploadPath + "/");
        registry.addResourceHandler("/static/**")
                .addResourceLocations("classpath:/static/");
    }

}
