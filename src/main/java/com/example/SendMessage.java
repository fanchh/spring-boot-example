package com.example;

import javax.jms.JMSException;   
import javax.jms.Message;   
import javax.jms.Session;   
import javax.jms.TextMessage;   
import javax.jms.Topic;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jms.core.JmsTemplate;   
import org.springframework.jms.core.MessageCreator;
import org.springframework.stereotype.Service;   
  
@Service
public class SendMessage {   
	@Autowired
    private JmsTemplate jmsTemplate;   
  
    private String topicName;   
  
    private Topic topic;   
  
    public void setJmsTemplate(JmsTemplate jmsTemplate) {
  
        this.jmsTemplate = jmsTemplate;   
    }   
  
    public void setTopicName(String topicName) {
  
        this.topicName = topicName;   
    }   
  
    public SendMessage(JmsTemplate jmsTemplate,String topicName){
    	this.jmsTemplate = jmsTemplate; 
    	this.topicName = topicName; 
    }
    public void sendMessage(final String message) {
  
  
        try {   
            if (topic == null) {
  
                topic = jmsTemplate.getConnectionFactory().createConnection()   
                        .createSession(false, Session.AUTO_ACKNOWLEDGE)
  
                        .createTopic(topicName);   
            }   
            jmsTemplate.send(topic,new MessageCreator() {
  
  
                @Override  
                public Message createMessage(Session session)
  
                        throws JMSException {
  
  
                    TextMessage textMessage = session   
                            .createTextMessage(message);
  
                    return textMessage;
  
                }   
            });   
        } catch (JMSException e) {   
            e.printStackTrace();   
        }   
    }   
}  