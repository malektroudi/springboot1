package com.example.demo.repository;

import com.example.demo.controllers.OrderController;
import com.example.demo.entities.CustomerEntity;
import com.example.demo.entities.DeliveryEntity;
import com.example.demo.entities.OrderEntity;
import com.example.demo.services.DbService;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Repository
public interface CustomerInterface extends JpaRepository<CustomerEntity, Integer> {

    @Override
    List<CustomerEntity> findAll();


    DbService db = new DbService();
    OrderController orderC = new OrderController();
    default List<CustomerEntity> getAll() throws SQLException {

        List<CustomerEntity> l  = new ArrayList<>();
        ResultSet result = db.getdatafromdb("select * from customer_entity");
        while (result.next()){
            CustomerEntity c = new CustomerEntity();

            c.setId(result.getInt(1));
            c.setActive(result.getInt(2) == 1);
            c.setContact(result.getString(3));
            c.setDeliveryAdress(result.getString(4));
            c.setNom(result.getString(5));
//            ResultSet r = db.getdatafromdb("select * from order_entity where customer_id = "+c.getId());
//            List<OrderEntity> o = new ArrayList<OrderEntity>();
//            while(r.next()){
//                o.add(orderC.getOrderByID(r.getInt(1)));
//            }
//            c.setOrders(o);
            l.add(c);
        }
        return l;

    }

    default CustomerEntity getCustomerById(int id) throws SQLException {

        List<CustomerEntity> l  = new ArrayList<>();
        ResultSet result = db.getdatafromdb("select * from customer_entity where id = "+id);
        CustomerEntity c = new CustomerEntity();
        if (result.next()){


            c.setId(result.getInt(1));
            c.setActive(result.getInt(2) == 1);
            c.setContact(result.getString(3));
            c.setDeliveryAdress(result.getString(4));
            c.setNom(result.getString(5));



        }
        return c;
    }

   public default List<Map<String, Object>> gettopfivecustomers() throws SQLException {

        List<Map<String, Object>> l  = new ArrayList<>();

        ResultSet result = db.getdatafromdb("select customer_entity.id, nom, contact, delivery_adress, count(*) as c from customer_entity, order_entity where customer_entity.id = customer_id group by nom order by c DESC limit 5");
        while (result.next()){
            CustomerEntity cus = new CustomerEntity();
            cus.setId(result.getInt(1));
            cus.setNom(result.getString(2));
            cus.setContact(result.getString(3));
            cus.setDeliveryAdress(result.getString(4));
            Map<String, Object> c = new HashMap<String, Object>();
             c.put("customer", cus.getdata());
             c.put("Orders", result.getString(5));

            l.add(c);
        }
       System.out.println(l);
        return l;

    }

}
