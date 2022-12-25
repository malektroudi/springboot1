package com.example.demo.entities;


import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "customer_entity")
public class CustomerEntity {


    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;


    @Column(length = 50)
    private String nom;

    @Column(length = 50)
    private String deliveryAdress;

    public CustomerEntity() {
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getDeliveryAdress() {
        return deliveryAdress;
    }

    public void setDeliveryAdress(String deliveryAdress) {
        this.deliveryAdress = deliveryAdress;
    }

    public String getContact() {
        return contact;
    }

    public void setContact(String contact) {
        this.contact = contact;
    }

    public boolean isActive() {
        return active;
    }

    public void setActive(boolean active) {
        this.active = active;
    }

    public List<OrderEntity> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderEntity> orders) {
        this.orders = orders;
    }

    @Column(length = 50)
    private String contact;


    public Map <String, Object> getdata() {
        Map <String, Object> m = new HashMap<>();
        m.put("id",id.toString());
        m.put("nom",nom);
        m.put("Delivery Adress",deliveryAdress);
        m.put("contact",contact);
//        m.put("active",active ? "True" : "False");
//        List<Object> _orders = new ArrayList<>();
//        orders.forEach(E -> _orders.add(E.getdata()));
//        m.put("orders",_orders);
        return m;
    }

    private boolean active;


    @OneToMany(mappedBy ="customer")
    private List<OrderEntity> orders;
}
