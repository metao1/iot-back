package com.gro.web.controller;

import com.gro.web.service.ObjectSseEmitterService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

@RestController
@RequestMapping("/api/event")
public class EventEmitterController {

    @Autowired
    private ObjectSseEmitterService objectSseEmitterService;

    @RequestMapping(method = RequestMethod.GET)
    public ResponseEntity<SseEmitter> streamEvents() {
        SseEmitter emitter = new SseEmitter();
        this.objectSseEmitterService.addEmitter(emitter);
        HttpHeaders httpHeaders = new HttpHeaders();
        httpHeaders.add("Content-Type", "text/event-stream");
        httpHeaders.add("Cache-Control", "no-cache");
        return new ResponseEntity<>(emitter, httpHeaders, HttpStatus.OK);
    }

}
