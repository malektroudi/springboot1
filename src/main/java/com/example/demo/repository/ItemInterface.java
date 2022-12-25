package com.example.demo.repository;

import com.example.demo.entities.CustomerEntity;
import com.example.demo.entities.ItemEntity;
import com.example.demo.entities.OrderEntity;
import com.example.demo.services.DbService;
import org.springframework.data.jpa.repository.JpaRepository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public interface ItemInterface extends JpaRepository<ItemEntity, Integer> {


    @Override
    List<ItemEntity> findAll();
DbService db = new DbService();

    default List<ItemEntity> getAll() throws SQLException {

        List<ItemEntity> l  = new ArrayList<>();
        ResultSet result = db.getdatafromdb("select * from item_entity");
        while (result.next()){
            ItemEntity c = new ItemEntity();

            c.setId(result.getInt(1));
            c.setDescription(result.getString(2));
            c.setPrice(result.getDouble(3));
            c.setWeight(result.getDouble(4));

            l.add(c);
        }
        return l;
    }

    default ItemEntity getById(int id) throws SQLException {

        String sql = "select * from item_entity where id = " + id;
        ResultSet result = db.getdatafromdb(sql);
        ItemEntity c = new ItemEntity();
        if (result.next()){

            c.setId(result.getInt(1));
            c.setDescription(result.getString(2));
            c.setPrice(result.getDouble(3));
            c.setWeight(result.getDouble(4));


        }
        return c;
    }
}
