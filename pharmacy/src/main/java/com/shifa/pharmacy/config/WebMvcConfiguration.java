package com.shifa.pharmacy.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.core.Ordered;
import org.springframework.web.servlet.config.annotation.ViewControllerRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class WebMvcConfiguration implements WebMvcConfigurer {

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/login").setViewName("user/login");
       /* registry.addViewController("/").setViewName("index");
        registry.addViewController("about us").setViewName("about us");*/
        registry.setOrder(Ordered.HIGHEST_PRECEDENCE);
    }


}
