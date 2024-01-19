package com.example.Scheduler.service;

import com.example.Scheduler.model.EmailDetails;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

@Component
@Slf4j
public class ScheduleTaskService {
    @Autowired
    private EmailServiceImpl emailService;
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy hh:mm:ss");

    @Scheduled(cron = "10 * * * * *")
    public void execute() throws InterruptedException {
        // some logic that will be executed on a schedule
        System.out.println("Code is being executed... Time: " + formatter.format(LocalDateTime.now()));
    }

    @Scheduled(cron = "${com.scheduled.cron}")
    public void sendEmail() throws InterruptedException{
        log.info("Scheduled email send as per time");
        emailService.sendEmail( new EmailDetails("ankitpal28090016@gmail.com","Email send by Scheduler spring boot project","Send by Scheduler Springboot project"));
    }


}
