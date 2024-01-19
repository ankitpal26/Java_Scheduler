package com.example.Scheduler.controller;

import com.example.Scheduler.model.ResponseBody;
import com.example.Scheduler.model.RequestDto;
import com.example.Scheduler.service.EmailServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api")
public class EmailController {

    @Autowired
    private EmailServiceImpl emailService;

    @PostMapping("/user/register")
    public ResponseEntity<ResponseBody> userRegistration(@RequestBody RequestDto requestDto) {
       RequestDto requestDto1= emailService.registerUser(requestDto);
       ResponseBody responseBody= new ResponseBody(requestDto1,"User register successfully!");
        return new ResponseEntity<>(responseBody,HttpStatus.CREATED);
    }
}
