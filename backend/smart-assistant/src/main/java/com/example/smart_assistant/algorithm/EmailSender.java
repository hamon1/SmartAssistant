package com.example.smart_assistant.algorithm;

import org.springframework.beans.factory.annotation.Value;

import javax.mail.*;
import javax.mail.internet.*;
import java.util.Properties;

public class EmailSender {
    @Value("${GOOGLE_PASSWORD}")
    private static String googlePassword;

    public static void sendEmail(String recipient, String subject, String content) {
        String host = "smtp.gmail.com";
        String sender = "leejh021122@gmail.com";
        String password = googlePassword;

        Properties props = new Properties();
        props.put("mail.smtp.auth", "true");
        props.put("mail.smtp.starttls.enable", "true");
        props.put("mail.smtp.host", host);
        props.put("mail.smtp.port", "587");

         // 인증 정보 설정
         Session session = Session.getInstance(props, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(sender, password);
            }
        });

        try {
            // 이메일 생성
            Message message = new MimeMessage(session);
            message.setFrom(new InternetAddress(sender));
            message.setRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject(subject);
            message.setText(content);

            // 이메일 전송
            Transport.send(message);
            System.out.println("Email sent successfully.");
        } catch (MessagingException e) {
            e.printStackTrace();
        }
    }
}
