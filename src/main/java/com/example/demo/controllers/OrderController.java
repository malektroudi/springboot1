package com.example.demo.controllers;

import com.example.demo.entities.OrderEntity;
import com.example.demo.repository.OrderInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class OrderController {

    @Autowired
    OrderInterface orderservice;




    public OrderEntity getOrderByID(int id) throws SQLException {
        return orderservice.getById(id);
    }

}
