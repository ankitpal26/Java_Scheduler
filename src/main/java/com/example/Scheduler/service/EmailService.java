package com.example.Scheduler.service;
import com.example.Scheduler.model.EmailDetails;
import com.example.Scheduler.model.RequestDto;

public interface EmailService {
    RequestDto registerUser(RequestDto requestDto);
    void sendEmail(EmailDetails emailDetails);

}
