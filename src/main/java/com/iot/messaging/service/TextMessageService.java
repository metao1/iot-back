package com.iot.messaging.service;

public interface TextMessageService {

    void sendSms(String message);

    void sendMms(String message);

}
