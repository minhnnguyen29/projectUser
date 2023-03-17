package com.example.project01;

import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class WebSecurityConfig 
{
    @Bean
    //disable CSRF with SecurityFilterChain to allow POST requests
    public SecurityFilterChain filterChain(HttpSecurity http) throws Exception 
    {    
        http.csrf(csrf -> csrf.disable());
        return http.build();
    }

    
}
