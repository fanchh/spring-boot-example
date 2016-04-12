package com.example.dao;

import java.util.List;

import com.example.entity.Student;


/**
 * StudentMapper，映射SQL语句的接口，无逻辑实现
 *
 * @author 单红宇(365384722)
 * @myblog http://blog.csdn.net/catoop/
 * @create 2016年1月20日
 */
public interface StudentDao {

    List<Student> likeName(String name);

    Student getById(int id);

    String getNameById(int id);

}