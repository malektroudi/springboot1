package com.example.demo.controllers;

import com.example.demo.entities.ItemEntity;
import com.example.demo.repository.ItemInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;

@RestController
public class ItemController {

    @Autowired
    ItemInterface it;


    public ItemEntity getItemById(int id) throws SQLException {
        return it.getById(id);
    }

}
