package com.iot.web.controller;

import com.iot.model.User;
import com.iot.model.dto.RPiDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class Ping {

    @GetMapping("/ping")
    public String ping(){
        return "pong";
    }

    @PostMapping("/ping")
    public String ping(RPiDTO rPiDTO){
        return "pong";
    }

}
