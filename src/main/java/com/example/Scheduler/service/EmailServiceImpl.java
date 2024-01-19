package com.example.Scheduler.service;

import com.example.Scheduler.Entity.User;
import com.example.Scheduler.constants.ExceptionConstants;
import com.example.Scheduler.exception.UserAlreadyExistsException;
import com.example.Scheduler.model.EmailDetails;
import com.example.Scheduler.model.RequestDto;
import com.example.Scheduler.repository.UserRepository;
import lombok.extern.slf4j.Slf4j;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.MailException;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;


@Service
@Slf4j
public class EmailServiceImpl implements EmailService {
    @Autowired
    private JavaMailSender javaMailSender;

    @Autowired
    private ModelMapper modelMapper;
    @Autowired
    private UserRepository userRepository;

    @Value("${spring.mail.username}")
    private String emailSender;


    @Override
    public RequestDto registerUser(RequestDto requestDto) {
        if(userRepository.existsByEmail(requestDto.getEmail()))
            throw new UserAlreadyExistsException(ExceptionConstants.USER_ALREADY_EXISTS);
        User user = modelMapper.map(requestDto, User.class);
        //Send email
        sendEmail(EmailDetails.builder()
                .MessageBody("Registration Successful with mail id: "+requestDto.getEmail())
                .Recipient(requestDto.getEmail())
                .Subject("REGISTRATION SUCCESS")
                .build());
        userRepository.save(user);
        return requestDto;
    }

    @Override
    public void sendEmail(EmailDetails emailDetails) {
        try {
            SimpleMailMessage mailMsg = new SimpleMailMessage();
            mailMsg.setFrom(emailSender);
            mailMsg.setTo(emailDetails.getRecipient());
            mailMsg.setText(emailDetails.getMessageBody());
            mailMsg.setSubject(emailDetails.getSubject());
            javaMailSender.send(mailMsg);
            log.info("Mail sent successfully");
        }catch (MailException exception){
            log.debug("Failure occurred while sending email");
        }
    }
}
