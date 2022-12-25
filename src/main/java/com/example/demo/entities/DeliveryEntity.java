package com.example.demo.entities;

import javax.persistence.*;
import java.time.Duration;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Table(name = "delivery_entity")
@Entity
public class DeliveryEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Column(length = 50)
    private String deliveryMan;

    public String getDeliveryMan() {
        return deliveryMan;
    }

    public void setDeliveryMan(String deliveryMan) {
        this.deliveryMan = deliveryMan;
    }

    public Date getShoppingDate() {
        return shoppingDate;
    }

    public void setShoppingDate(Date shoppingDate) {
        this.shoppingDate = shoppingDate;
    }

    public Date getDeliveryDate() {
        return deliveryDate;
    }

    public void setDeliveryDate(Date deliveryDate) {
        this.deliveryDate = deliveryDate;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public DeliveryEntity() {
    }

    @Temporal(TemporalType.DATE)
    private Date shoppingDate;

    @Temporal(TemporalType.DATE)
    private Date deliveryDate;


    @ManyToOne
    private OrderEntity order;


    public Map<String, Object> getdata() {
        Map <String, Object> m = new HashMap<>();
        m.put("id",id);
        m.put("delivery man",deliveryMan);
        m.put("shopping date",shoppingDate);
        m.put("delivery date",deliveryDate);
        return m;
    }

    public long getDuration(){

        LocalDate d1 = LocalDate.parse(this.deliveryDate.toString(), DateTimeFormatter.ISO_LOCAL_DATE);
        LocalDate d2 = LocalDate.parse(this.shoppingDate.toString(), DateTimeFormatter.ISO_LOCAL_DATE);
        Duration diff = Duration.between(d2.atStartOfDay(), d1.atStartOfDay());
        long diffDays = diff.toDays();
        return diffDays;
    }
}