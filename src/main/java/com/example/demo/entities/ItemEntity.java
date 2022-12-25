package com.example.demo.entities;

import javax.persistence.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Table(name = "item_entity")
@Entity
public class ItemEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public ItemEntity() {
    }

    public double getWeight() {
        return weight;
    }

    public void setWeight(double weight) {
        this.weight = weight;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    public List<OrderDetailEntity> getOrderDetail() {
        return orderDetail;
    }

    public void setOrderDetail(List<OrderDetailEntity> orderDetail) {
        this.orderDetail = orderDetail;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }


    private double weight;

    @Column(length = 50)
    private String description;

    private double price;

    @OneToMany(mappedBy = "item")
    private List<OrderDetailEntity> orderDetail;


    public Map<String, Object> getdata() {
        Map <String, Object> m = new HashMap<>();
        m.put("id",id);
        m.put("description",description);
        m.put("price",price);
        m.put("weight",weight);
        return m;
    }
}