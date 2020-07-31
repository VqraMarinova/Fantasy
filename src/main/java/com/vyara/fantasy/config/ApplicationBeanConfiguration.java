package com.vyara.fantasy.config;

import com.cloudinary.*;
import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ApplicationBeanConfiguration {

    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }

    @Bean
    Cloudinary cloudinary(){
        return new Cloudinary("cloudinary://337162944638481:qvn0OJWCMfLr7yqW-PkHiLeHTWM@dvxdlf9hx");
    }



}
