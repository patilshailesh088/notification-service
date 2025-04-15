package com.pisyst.pmt.Notification_Service.configuration;

import com.pisyst.pmt.Notification_Service.dto.EmployeeOnboardingEvent;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

@Slf4j
@Service
public class EmployeeOnboardingListener {

    @Autowired
    private EmailService emailService;

    @KafkaListener(topics="employee-onboarding-topic", groupId = "notification-group", containerFactory = "kafkaListenerContainerFactory")
    public void listen(EmployeeOnboardingEvent event) {
        log.info("Received onboarding Event: {}", event.toString());
        try {
            String subject = "New Employee Onboarded: " + event.getEmployeeName();
            String body = "Name: " +event.getEmployeeName() + "\n" +
                    "Email: " + event.getEmployeeEmail() + "\n" +
                    "Department: " + event.getDepartment() + "\n" +
                    "DOJ: " + event.getDoj();

            emailService.sendEmail(event.getManagerEmail(), subject, body);

        } catch (Exception e) {
            log.info("Failed to process the Event: {}", e.getMessage());
        }

    }
}
