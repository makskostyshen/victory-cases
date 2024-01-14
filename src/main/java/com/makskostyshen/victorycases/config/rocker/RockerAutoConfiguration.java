package com.makskostyshen.victorycases.config.rocker;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.ViewResolver;

@Configuration
public class RockerAutoConfiguration {

    @Value("${spring.rocker.prefix}")
    private String prefix;

    @Value("${spring.rocker.suffix}")
    private String suffix;

    @Bean
    public ViewResolver rockerViewResolver() {
        return new RockerViewResolver(prefix, suffix);
    }
}
