package com.example.schedule;

import javax.annotation.Resource;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Configuration;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;



/**
 * 定时任务配置类
 *
 * @author 单红宇(365384722)
 * @myblog http://blog.csdn.net/catoop/
 * @create 2016年3月21日
 */
@Configuration
@EnableScheduling // 启用定时任务
public class SchedulingConfig {

    private final Logger logger = LoggerFactory.getLogger(getClass());
   // @Resource
   // private JmsProducerService jmsProducerService;

//    @Scheduled(cron = "0/5 * * * * ?") // 每20秒执行一次
//    public void scheduler() {
//        logger.info(">>>>>>>>>>>>> scheduled ... ");
//    //    jmsProducerService.sendMsg("mqsss");
//    }

}