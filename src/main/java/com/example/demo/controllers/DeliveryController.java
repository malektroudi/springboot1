package com.example.demo.controllers;

import com.example.demo.entities.DeliveryEntity;
import com.example.demo.repository.DeliveryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class DeliveryController {
    @Autowired
    DeliveryInterface delivery;



   List<DeliveryEntity> getall(){
       return delivery.findAll();
   }
}
