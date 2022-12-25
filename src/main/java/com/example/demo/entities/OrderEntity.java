package com.example.demo.entities;


import javax.persistence.*;
import java.util.*;

@Entity
@Table(name = "order_entity")
public class OrderEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public CustomerEntity getCustomer() {
        return customer;
    }

    public void setCustomer(CustomerEntity customer) {
        this.customer = customer;
    }

    public List<DeliveryEntity> getDelivery() {
        return delivery;
    }

    public Map<String, Object> getdata() {
        Map <String, Object> m = new HashMap<>();
        m.put("id",id);
        m.put("create date",createDate);
        List<Object> deliveries = new ArrayList<>();
        delivery.forEach(E -> deliveries.add(E.getdata()));
        m.put("delivery",deliveries);
        List<Object> od = new ArrayList<>();
        orderdetails.forEach(E -> od.add(E.getdata()));
        m.put("order detail",od);
        m.put("order status",orderStatus);

        return m;
    }

    public void setDelivery(List<DeliveryEntity> delivery) {
        this.delivery = delivery;
    }

    public List<OrderDetailEntity> getOrderdetails() {
        return orderdetails;
    }

    public void setOrderdetails(List<OrderDetailEntity> orderdetails) {
        this.orderdetails = orderdetails;
    }

    public OrderStautsEntity getOrderStatus() {
        return orderStatus;
    }

    public void setOrderStatus(OrderStautsEntity orderStatus) {
        this.orderStatus = orderStatus;
    }

    public OrderEntity() {
    }

    @Temporal(TemporalType.DATE)
    private Date createDate;

    @ManyToOne
    @JoinColumn(name = "customer_id", nullable = false, foreignKey = @ForeignKey(name = "fk_customer_id"))
    private CustomerEntity customer;

    @OneToMany(mappedBy = "order")
    private List<DeliveryEntity> delivery;

    @OneToMany(mappedBy = "order")
    private List<OrderDetailEntity> orderdetails;


    @Enumerated(EnumType.STRING)
    private OrderStautsEntity orderStatus;

}
