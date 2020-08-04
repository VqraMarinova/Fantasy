package com.vyara.fantasy.config;

import com.vyara.fantasy.services.filters.GreetingInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppWebConfigurations implements WebMvcConfigurer {

    private final GreetingInterceptor greetingInterceptor;


    public AppWebConfigurations(GreetingInterceptor greetingInterceptor) {
        this.greetingInterceptor = greetingInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(greetingInterceptor).addPathPatterns("/**");

    }


}
