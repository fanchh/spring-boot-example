package com.example;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.alibaba.fastjson.JSON;
import com.example.dao.OrderDao;
import com.example.entity.OrderEntity;

@RestController
@RequestMapping("/hello")
public class HelloController {
	private Logger log = Logger.getLogger(HelloController.class);
	@Autowired
	private OrderDao orderDao;

	@Autowired
	private SendMessage sendMessage;
    @RequestMapping
    public String hello() {
        return "Hello Spring-Boot";
    }

    @RequestMapping("/info")
    public Map<String, String> getInfo(@RequestParam String name) {
        Map<String, String> map = new HashMap<>();
        map.put("name", name);
        return map;
    }

    @RequestMapping("/list")
    public List<Map<String, String>> getList() {
        List<Map<String, String>> list = new ArrayList<>();
        Map<String, String> map = null;
        for (int i = 1; i <= 5; i++) {
            map = new HashMap<>();
            map.put("name", "Shanhy-" + i);
            list.add(map);
        }
        return list;
    }
    @RequestMapping("/insert.ajax")
    @ResponseBody
    public String getInfo(OrderEntity orderEntity) {
    	if (StringUtils.isEmpty(orderEntity.getOrdercode())){  
    		log.error("主键订单号不能为空!");
    		return "error";
    	}
    	//int count = orderDao.insertSelective(orderEntity);
    	int count =1;
    	if(count >0){
    		String json = JSON.toJSONString(orderEntity);
    	//	log.debug(json);
    		sendMessage.sendMessage(json);
    	}
       return "done";
    }

}