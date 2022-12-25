package com.example.demo.controllers;


import com.example.demo.entities.CustomerEntity;
import com.example.demo.entities.DeliveryEntity;
import com.example.demo.repository.CustomerInterface;
import com.example.demo.repository.DeliveryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

@RestController
public class CustomerController {

    @Autowired
    CustomerInterface cust_Service;

    @Autowired
    DeliveryInterface ds;


    @GetMapping(path = "/get_customers__")
    List<CustomerEntity> getCustomers(){return cust_Service.findAll();}


    @GetMapping(path = "/get_customers")
    List<CustomerEntity> getCustomersNames() throws SQLException {
        return cust_Service.getAll();
    }


    public CustomerEntity getCustomerById(int id) throws SQLException {

        return cust_Service.getCustomerById(id);
    }



//    @GetMapping(path = "/get_delivery_by_id")
//    DeliveryEntity get_delivery_by_id() throws SQLException {
//
//        return ds.get_delivery_by_id(4);
//    }

}
