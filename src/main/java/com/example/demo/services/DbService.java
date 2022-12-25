package com.example.demo.services;

import java.sql.*;

public class DbService {

    public DbService(){}
    String dbURL = "jdbc:mysql://localhost:3306/examen";
    String username = "root";
    String password = "";
    Connection conn;

    public ResultSet getdatafromdb(String sql) throws SQLException {
        conn = DriverManager.getConnection(dbURL, username, password);
        Statement statement = conn.createStatement();

        return statement.executeQuery(sql);
    }
}
