package com.iot.web.controller;

import com.iot.web.service.ObjectSseEmitterService;
import org.springframework.beans.factory.annotation.Autowired;
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
    public SseEmitter streamEvents() {
        SseEmitter emitter = new SseEmitter();
        this.objectSseEmitterService.addEmitter(emitter);
        return emitter;
    }

}
