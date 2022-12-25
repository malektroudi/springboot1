package com.example.demo.entities;

import javax.persistence.*;
import java.util.HashMap;
import java.util.Map;

@Table(name = "order_detail_entity")
@Entity
public class OrderDetailEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    public OrderDetailEntity() {
    }

    public int getQty() {
        return qty;
    }

    public void setQty(int qty) {
        this.qty = qty;
    }

    public double getTax() {
        return tax;
    }

    public void setTax(double tax) {
        this.tax = tax;
    }

    public OrderEntity getOrder() {
        return order;
    }

    public void setOrder(OrderEntity order) {
        this.order = order;
    }

    public ItemEntity getItem() {
        return item;
    }

    public void setItem(ItemEntity item) {
        this.item = item;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    private int qty;

    private double tax;

    @ManyToOne
    @JoinColumn(name = "order_id", nullable = false, foreignKey = @ForeignKey(name = "fk_order_id"))
    private OrderEntity order;

    @ManyToOne
    @JoinColumn(name = "item_id", nullable = false, foreignKey = @ForeignKey(name = "fk_item_id"))
    private ItemEntity item;

    public double calculateTotal(){

        return (item.getPrice() * qty) + (item.getPrice() * qty * tax * 0.01);

    }

    public double calculateWeight(){
        return item.getWeight() * qty ;
    }


    public Map<String, Object> getdata() {
        Map <String, Object> m = new HashMap<>();
        m.put("id",id);
        m.put("item",item.getdata());
        m.put("quantity",qty);
        m.put("Tax",getTaxString());
        m.put("Total",calculateTotal());
        m.put("weight",calculateWeight());

        return m;
    }

    public String getTaxString(){return String.valueOf(tax) + " %";}
}