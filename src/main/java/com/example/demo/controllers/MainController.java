package com.example.demo.controllers;

import com.example.demo.entities.CustomerEntity;
import com.example.demo.entities.DeliveryEntity;
import com.example.demo.entities.OrderDetailEntity;
import com.example.demo.entities.OrderEntity;
import com.example.demo.repository.CustomerInterface;
import com.example.demo.repository.DeliveryInterface;
import org.hibernate.criterion.Order;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class MainController {
    
    @Autowired
    DeliveryInterface delivery;

    @Autowired
    CustomerInterface cust_Service;

    @GetMapping(path = "get_fast_delivery_man")
    String get_fast_delivery_man(){
    List<DeliveryEntity> d = delivery.findAll();
        DeliveryEntity fast_delivery = d.get(0);
        for (DeliveryEntity e: d
             ) {
            if (e.getDuration() < fast_delivery.getDuration()) {
                fast_delivery = e;
            }
        }
        return "Fast delivery man is "+fast_delivery.getDeliveryMan()+" whith a duration of "+fast_delivery.getDuration() + " Day";
    }

    @GetMapping(path = "get_longest_delivery_man")
    String get_longest_delivery_man(){
        List<DeliveryEntity> d = delivery.findAll();
        DeliveryEntity fast_delivery = d.get(0);
        for (DeliveryEntity e: d
        ) {
            if (e.getDuration() > fast_delivery.getDuration()) {
                fast_delivery = e;
            }
        }
        return "Longest delivery man is "+fast_delivery.getDeliveryMan()+" whith a duration of "+fast_delivery.getDuration() + " Days";
    }


    @GetMapping(path = "/get_top_five_by_orders")
    List<Map<String, Object>> getTopFiveByOrders() throws SQLException {

       return cust_Service.gettopfivecustomers();

    }

    @GetMapping(path = "/get_top_five_customers_by_money")

    List<Map<String, Object>> get_top_five_customers_by_money(){

        List<CustomerEntity> customers = cust_Service.findAll();
        List<Map<String, Object>> filtred = new ArrayList<>();
        for (CustomerEntity c: customers
             ) {
            double Somme = 0;
            for (OrderEntity o: c.getOrders()
                 ) {
                for (OrderDetailEntity d: o.getOrderdetails()
                     ) {
                    Somme += d.calculateTotal();
                }
            }

            Map<String, Object> cust = new HashMap<>();
            cust.put("Total money", String.format("%.2f", Somme) + "DT");
            cust.put("Customer", c.getdata());
            filtred.add(cust);
        }


        System.out.println(filtred);

        return filtred;
    }



    @GetMapping(path = "/get_top_five_customers_by_wheight")

    List<Map<String, Object>> get_top_five_customers_by_wheight(){

        List<CustomerEntity> customers = cust_Service.findAll();
        List<Map<String, Object>> filtred = new ArrayList<>();
        for (CustomerEntity c: customers
        ) {
            double Somme = 0;
            for (OrderEntity o: c.getOrders()
            ) {
                for (OrderDetailEntity d: o.getOrderdetails()
                ) {
                    Somme += d.calculateWeight();
                }
            }

            Map<String, Object> cust = new HashMap<>();
            cust.put("Total orders wheight", Somme / 1000 + "Kg");
            cust.put("Customer", c.getdata());
            filtred.add(cust);
        }


        System.out.println(filtred);

        return filtred;
    }
}
