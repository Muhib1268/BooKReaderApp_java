package model;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class ConnectionDB {
    public Connection getConnection(){
        try {
                return DriverManager.getConnection("jdbc:postgresql:bookVault", "postgres", "Sadaf");
            } catch (SQLException e) {
            	// TODO Auto-generated catch block
            	e.printStackTrace();
                throw new RuntimeException(e);
            }
    
    }
}
