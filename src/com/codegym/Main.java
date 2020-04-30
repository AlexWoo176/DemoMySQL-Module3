package com.codegym;

import java.sql.Connection;
import java.sql.SQLException;

import static com.codegym.JDBCConnection.getJDBCConnection;

public class Main {
    public static void main(String[] args) {
        Connection connection = getJDBCConnection();
        try {
            if (connection != null) {
                System.out.println("Data connection successful");
            } else {
                System.out.println("Data connection failed");
            }
        } finally {
            try {
                connection.close();
            } catch (SQLException ex) {
                ex.printStackTrace();
            }
        }
    }
}
