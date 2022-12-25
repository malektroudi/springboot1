package com.example.demo.repository;

import com.example.demo.controllers.ItemController;
import com.example.demo.controllers.OrderController;
import com.example.demo.entities.ItemEntity;
import com.example.demo.entities.OrderDetailEntity;
import com.example.demo.services.DbService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface OrderDetailInterface extends JpaRepository<OrderDetailEntity, Integer> {

    @Override
    List<OrderDetailEntity> findAll();

    DbService db = new DbService();

    ItemController itController = new ItemController();
    OrderController orderC = new OrderController();

    default OrderDetailEntity getById(int id) throws SQLException {

        String sql = "select * from Order_detail_entity where id = " + id;
        ResultSet result = db.getdatafromdb(sql);
        OrderDetailEntity c = new OrderDetailEntity();
        if (result.next()){

            c.setId(result.getInt(1));
            c.setQty(result.getInt(2));
            c.setTax(result.getDouble(3));
            c.setItem(itController.getItemById(result.getInt(4)));
            c.setOrder(orderC.getOrderByID(result.getInt(5)));

        }
        return c;
    }

}
