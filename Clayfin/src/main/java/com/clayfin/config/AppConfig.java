package com.clayfin.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.validation.beanvalidation.LocalValidatorFactoryBean;

import com.clayfin.services.CsvDataTransferService;

import jakarta.validation.Validator;

@Configuration
public class AppConfig {

    @Bean
    public Validator validator() {
        return new LocalValidatorFactoryBean();
    }
    
//    @Bean
//    public CsvDataTransferService csvDataTransferService() {
//        return new CsvDataTransferService();
//    }

}
