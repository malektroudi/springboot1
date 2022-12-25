package com.example.demo.repository;

import com.example.demo.controllers.OrderController;
import com.example.demo.entities.CustomerEntity;
import com.example.demo.entities.DeliveryEntity;
import com.example.demo.entities.OrderDetailEntity;
import com.example.demo.services.DbService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RestController;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
@Repository
public interface DeliveryInterface extends JpaRepository<DeliveryEntity, Integer> {



    @Override
    List<DeliveryEntity> findAll();

    DbService db = new DbService();
    OrderController orderC = new OrderController();
    default List<DeliveryEntity> getDelivery() throws SQLException {

        List<DeliveryEntity> l  = new ArrayList<>();
                ResultSet result = db.getdatafromdb("select * from delivery_entity");
                while (result.next()){
                    DeliveryEntity c = new DeliveryEntity();

                    c.setId(result.getInt(1));
                    c.setDeliveryDate(result.getDate(2));
                    c.setDeliveryMan(result.getString(3));
                    c.setShoppingDate(result.getDate(4));
                    c.setOrder(orderC.getOrderByID(result.getInt(5)));

                    l.add(c);
                }
        return l;
    }

    default DeliveryEntity getDeliveryById(int id) throws SQLException {


        String sql = "select * from delivery_entity where order_id ="+String.valueOf(id);
        ResultSet result = db.getdatafromdb(sql);
        DeliveryEntity c = new DeliveryEntity();
        if (result.next()){
            c.setId(result.getInt(1));
            c.setDeliveryDate(result.getDate(2));
            c.setDeliveryMan(result.getString(3));
            c.setShoppingDate(result.getDate(4));
            c.setOrder(orderC.getOrderByID(result.getInt(5)));
        }
        return c;
    }
}
