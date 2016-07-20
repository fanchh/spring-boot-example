package com.example.dao;

import com.example.entity.OrderEntity;

public interface OrderDao {

    int insertSelective(OrderEntity record);

    OrderEntity selectByPrimaryKey(String ordercode);


}