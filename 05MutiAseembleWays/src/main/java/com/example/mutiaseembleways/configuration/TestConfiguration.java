package com.example.mutiaseembleways.configuration;

import com.example.mutiaseembleways.TestObejects.Dog;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class TestConfiguration {

    @Bean
    public Dog NewDog(){
        return new Dog();
    }
}
