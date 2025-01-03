package com.example.smart_assistant.service;

import org.springframework.stereotype.Service;

import jakarta.mail.internet.MimeMessage;

import java.net.Authenticator;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;


import java.util.Properties;

@Service
public class EmailSender {

    private JavaMailSender javaMailSender;

    @Autowired
    public EmailSender(JavaMailSender javaMailSender) {
        this.javaMailSender = javaMailSender;
    } 

    public boolean sendEmail(String to, String subject, String content) {
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        System.out.println("?");
        simpleMailMessage.setFrom("leejh021122@gmail.com"); // replace with your actual email address
        simpleMailMessage.setTo(to);
        simpleMailMessage.setSubject(subject);
        simpleMailMessage.setText(content);

        System.out.println(simpleMailMessage);

        // JavaMailSender javaMailSender = new JavaMailSender();

        javaMailSender.send(simpleMailMessage);
    
        return false;
    }

    
}
