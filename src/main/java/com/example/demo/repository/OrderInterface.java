package com.example.demo.repository;

import com.example.demo.controllers.CustomerController;
import com.example.demo.controllers.ItemController;
import com.example.demo.entities.CustomerEntity;
import com.example.demo.entities.OrderDetailEntity;
import com.example.demo.entities.OrderEntity;
import com.example.demo.entities.OrderStautsEntity;
import com.example.demo.services.DbService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

public interface OrderInterface extends JpaRepository<OrderEntity, Integer> {
    @Override
    List<OrderEntity> findAll();


    DbService db = new DbService();

    CustomerController customer = new CustomerController();

    default OrderEntity getById(int id) throws SQLException {

        String sql = "select * from order_entity where id = " + id;
        ResultSet result = db.getdatafromdb(sql);
        OrderEntity c = new OrderEntity();
        if (result.next()){

            c.setId(result.getInt(1));
            c.setCreateDate(result.getDate(2));
            String value = result.getString(3);
            OrderStautsEntity statut = value.equals("CREATE")  ? OrderStautsEntity.CREATE :
                    value.equals("SHIPPING")  ? OrderStautsEntity.SHIPPING :
                            value.equals("DELIVERED")  ? OrderStautsEntity.DELIVERED : OrderStautsEntity.PAID ;
            c.setOrderStatus(statut);
            c.setCustomer(customer.getCustomerById(result.getInt(4)));
            
        }
        return c;
    }

}
