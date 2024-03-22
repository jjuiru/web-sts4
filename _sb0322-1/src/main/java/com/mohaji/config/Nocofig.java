package com.mohaji.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import com.mohaji.dto.State;

@Configuration
public class Nocofig {
    @Bean
    public State state() {
        State state = new State();
        // state 초기화 작업 수행 (선택사항)
        return state;
    }
}
