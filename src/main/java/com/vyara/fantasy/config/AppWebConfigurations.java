package com.vyara.fantasy.config;

import com.vyara.fantasy.services.filtersAndInterseptors.GreetingInterceptor;
import com.vyara.fantasy.services.filtersAndInterseptors.ThemeInterceptor;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@Configuration
public class AppWebConfigurations implements WebMvcConfigurer {

    private final GreetingInterceptor greetingInterceptor;
    private final ThemeInterceptor themeInterceptor;


    public AppWebConfigurations(GreetingInterceptor greetingInterceptor, ThemeInterceptor themeInterceptor) {
        this.greetingInterceptor = greetingInterceptor;
        this.themeInterceptor = themeInterceptor;
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(greetingInterceptor).addPathPatterns("/**");
        registry.addInterceptor(themeInterceptor).addPathPatterns("/**");

    }


}
