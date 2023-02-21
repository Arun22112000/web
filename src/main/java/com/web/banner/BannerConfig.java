package com.banner.qb.banner;

import org.modelmapper.ModelMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class BannerConfig {
    @Bean
    public ModelMapper modelMapper(){
        return new ModelMapper();
    }
}
