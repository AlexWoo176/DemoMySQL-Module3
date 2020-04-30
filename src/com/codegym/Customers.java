package com.codegym;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Customers {
    public static void main(String[] args) {
        Connection connection = JDBCConnection.getJDBCConnection();
        Statement statement = null;
        {
            try {
                statement = connection.createStatement();
                String sql = "SELECT * FROM customers WHERE customername like '%A%'";
                ResultSet resultSet = statement.executeQuery(sql);

                System.out.printf("%-40s%-25s%-25s%-25s\n", "CustomerID", "CustomerName", "PhoneNumber", "City", "Country");
                while (resultSet.next()) {
                    String name = resultSet.getString("customerName");
                    String phone = resultSet.getString("phone");
                    String city = resultSet.getString("city");
                    String country = resultSet.getString("country");

                    System.out.printf("%-40s%-25s%-25s%-25s\n", name, phone, city, country);
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            } finally {
                if (statement != null) {
                    try {
                        statement.close();
                    } catch (SQLException e) {
                        e.printStackTrace();
                    }
                }
                if (connection != null) {
                    try {
                        connection.close();
                    } catch (SQLException throwables) {
                        throwables.printStackTrace();
                    }
                }
            }
        }
    }
}
