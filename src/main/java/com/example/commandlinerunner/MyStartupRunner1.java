package com.example.commandlinerunner;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

/**
 * 服务启动执行
 *
 * @author   单红宇(365384722)
 * @myblog  http://blog.csdn.net/catoop/
 * @create    2016年1月9日
 */
@Component
@Order(value=1)
public class MyStartupRunner1 implements CommandLineRunner {
	// 在Java类中创建 logger 实例
	private static final Logger logger = LoggerFactory.getLogger(MyStartupRunner1.class);
	// 在方法中使用日志输出，如
	private void logTest() {
	    logger.debug("日志输出测试 Debug");
	    logger.trace("日志输出测试 Trace");
	    logger.info("日志输出测试 Info");
	}
    @Override
    public void run(String... args) throws Exception {
    	 logTest() ;
        System.out.println(">>>>>>>>>>>>>>>服务启动执行，执行加载数据等操作 11111111 <<<<<<<<<<<<<");
    }

}