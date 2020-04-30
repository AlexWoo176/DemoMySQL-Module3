package com.codegym;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class ShowCustomers {
    public static void main(String[] args) {
        Connection connection = JDBCConnection.getJDBCConnection();
        PreparedStatement pst = null;
        String sql = "SELECT * FROM customers WHERE city IN ('Nantes',' Las Vegas',' Warszawa','NYC', 'San Jose')";
        try {
            pst = connection.prepareStatement(sql);
            ResultSet resultSet = pst.executeQuery();
            showResult(resultSet);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (pst != null) {
                try {
                    pst.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public static void showResult(ResultSet resultSet) throws SQLException {
        System.out.printf("%-5s%-40s%-20s%-20s%-20s\n", "Id", "Fullname", "Phone", "City", "Country");
        while (resultSet.next()) {
            int id = resultSet.getInt("customerNumber");
            String name = resultSet.getString("customerName");
            String phone = resultSet.getString("phone");
            String city = resultSet.getString("city");
            String country = resultSet.getString("country");

            System.out.printf("%-5d%-40s%-20s%-20s%-20s\n", id, name, phone, city, country);
        }
    }
}
