package com.example;

import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageListener;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.Topic;

import org.apache.activemq.command.ActiveMQMessage;
import org.apache.activemq.command.ActiveMQTextMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.jms.core.JmsTemplate;
import org.springframework.jms.listener.DefaultMessageListenerContainer;

import com.alibaba.fastjson.JSON;
import com.example.dao.OrderDao;
import com.example.entity.OrderEntity;   
  

public class TestListener implements MessageListener{   
	
	@Autowired
	private OrderDao orderDao;
	
    private JmsTemplate jmsTemplate;     
    private String topicName;   
       
    public TestListener(JmsTemplate jmsTemplate,String topicName){   
           
        this.jmsTemplate = jmsTemplate;   
           
        this.topicName =  topicName;
         
        try {   
            Queue queue = this.jmsTemplate.getConnectionFactory().createConnection().createSession(false,   
                    Session.AUTO_ACKNOWLEDGE).createQueue(this.topicName);   
            System.out.println("消费者主题:"+topicName);   
            DefaultMessageListenerContainer dmc = new DefaultMessageListenerContainer();   
            	//消费者必须是true
            dmc.setPubSubDomain(true);   
            dmc.setDestination(queue);   
            dmc.setConnectionFactory(this.jmsTemplate.getConnectionFactory());   
            // true 不接受同一连接池的消息  false 接收
            dmc.setPubSubNoLocal(true);   
            dmc.setMessageListener(this);   
            dmc.setSessionAcknowledgeMode(Session.AUTO_ACKNOWLEDGE);   
            dmc.initialize();   
            dmc.start();   
        } catch (JMSException e) {   
            e.printStackTrace();   
        }   
    }   
    @Override  
    public void onMessage(Message message) {   
    	ActiveMQTextMessage amqMessage=(ActiveMQTextMessage)message;

    	OrderEntity orderEntity=null;
      try {
    	  orderEntity=JSON.parseObject(amqMessage.getText(), OrderEntity.class);
    	  int count = orderDao.insertSelective(orderEntity);
		} catch (JMSException e) {
			e.printStackTrace();
		}
      
      
      
        //System.out.println("消费者:"+JSON.toJSONString(message));   
    }   
  
}  