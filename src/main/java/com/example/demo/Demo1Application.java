package com.example.demo;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@SpringBootApplication
public class Demo1Application {

	public static void main(String[] args) {
		SpringApplication.run(Demo1Application.class, args);
	}
   
	 @Configuration
	    public static class AuthenticationMananagerProvider extends WebSecurityConfigurerAdapter {

	        @Bean
	        @Override
	        public AuthenticationManager authenticationManagerBean() throws Exception {
	            return super.authenticationManagerBean();
	        }

	        @Bean
	        public PasswordEncoder passwordEncoder() {
	            return new BCryptPasswordEncoder();
	        }
	    }
}
