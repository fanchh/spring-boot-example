package com.example;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsMessagingTemplate;
import org.springframework.stereotype.Service;

import javax.jms.Queue;

@Service
public class JmsProducerService {

    @Autowired
    private JmsMessagingTemplate jmsMessagingTemplate;

    @Autowired
    private Queue queue;

    public void sendMsg(String msg) {
        jmsMessagingTemplate.convertAndSend(queue, msg);
        System.out.println("Message was sent to the Queue");
    }
}