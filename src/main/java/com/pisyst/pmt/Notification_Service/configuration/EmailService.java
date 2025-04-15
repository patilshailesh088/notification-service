package com.pisyst.pmt.Notification_Service.configuration;

import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmailService {

    private final JavaMailSender mailSender;

    public EmailService(JavaMailSender mailSender) {
        this.mailSender = mailSender;
    }

    public void sendEmail(String toManagerEmail, String subject, String body) {
        try {
            SimpleMailMessage message = new SimpleMailMessage();
            message.setTo(toManagerEmail);
            message.setSubject(subject);
            message.setText(body);
            mailSender.send(message);

            log.info("E-Mail sent to {}", toManagerEmail);

        } catch (MailException e) {
            log.info("Failed to send the Email to {} : {}", toManagerEmail, e.getMessage());
        }
    }
}
