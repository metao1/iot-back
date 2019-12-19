package com.iot.web.service;

import org.springframework.http.MediaType;
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public abstract class AbstractSseEmitterService<T> implements SseEmitterService<T> {

    private final List<SseEmitter> emitters = Collections.synchronizedList(new ArrayList<>());

    public final void removeEmitter(SseEmitter emitter) {
        emitters.remove(emitter);
    }

    public final void addEmitter(SseEmitter emitter) {
        emitter.onCompletion(() -> removeEmitter(emitter));
        emitter.onTimeout(() -> removeEmitter(emitter));
        this.emitters.add(emitter);
    }

    public final void emit(T t) {
        //Broadcast the events to the all subscribers where they've added with addEmitter()
        List<SseEmitter> deadEmitters = new ArrayList<>();
        this.emitters.forEach(emitter -> {
            try {
                emitter.send(t, MediaType.APPLICATION_JSON);
            } catch (Exception e) {
                deadEmitters.add(emitter);
            }
        });

        this.emitters.remove(deadEmitters);
    }
}
