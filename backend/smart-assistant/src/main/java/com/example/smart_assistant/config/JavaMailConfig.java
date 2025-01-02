package com.example.smart_assistant.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.JavaMailSenderImpl;

import lombok.AllArgsConstructor;

import org.springframework.beans.factory.annotation.Value;

import java.util.Properties;


@Configuration
@AllArgsConstructor
public class JavaMailConfig {
    // @Value("${GOOGLE_PASSWORD}")
    // private String password;

    @Bean
    public JavaMailSender javaMailSender() {
        JavaMailSenderImpl javaMailSender = new JavaMailSenderImpl();

        javaMailSender.setHost("smtp.gmail.com");
        javaMailSender.setUsername("leejh021122@gmail.com");
        // javaMailSender.setPassword(password);

        javaMailSender.setPort(587);

        javaMailSender.setJavaMailProperties(getMailProperties());


        return javaMailSender;

        // return new JavaMailSenderImpl();
    }

    private Properties getMailProperties() {
        Properties properties = new Properties();
        properties.setProperty("mail.transport.protocol", "smtp");
        properties.setProperty("mail.smtp.auth", "true");
        properties.setProperty("mail.smtp.starttls.enable", "true");
        properties.setProperty("mail.debug", "true");
        // properties.setProperty("mail.smtp.ssl.enable","true");
        return properties;
    }
}
