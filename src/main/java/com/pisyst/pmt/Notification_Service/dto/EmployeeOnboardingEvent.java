package com.pisyst.pmt.Notification_Service.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class EmployeeOnboardingEvent {

    private String employeeName;
    private String employeeEmail;
    private  String department;
    private LocalDate doj;
    private String managerEmail;
}
