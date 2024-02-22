package com.backendgip;

import com.backendgip.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.web.servlet.config.annotation.CorsRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

@SpringBootApplication
@EnableScheduling
public class BackEndGipApplication {
    @Autowired
    public MailService mailService;

    public BackEndGipApplication() {
    }

    public static void main(String[] args) {
        SpringApplication.run(BackEndGipApplication.class, args);
    }

    @Bean
    public WebMvcConfigurer corsConfigurer() {
        return new WebMvcConfigurer() {
            public void addCorsMappings(CorsRegistry registry) {
                registry.addMapping("/**").allowedOrigins(new String[] { "*" })
                        .allowedMethods(new String[] { "GET", "POST", "PUT", "DELETE" });
            }
        };
    }
}