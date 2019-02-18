/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Model;

import java.sql.*;

/**
 *
 * @author rackarmattan
 */
public class User {

    //POSTGRESQL DATABASE
    static final String DATABASE = "jdbc:postgresql://localhost/local";
    static final String USERNAME = "rackarmattan";
    static final String PASSWORD = "hejhej123";
    
    private Connection connect(){
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(DATABASE, USERNAME, PASSWORD);
            System.out.println("Connected to the PostgreSQL server successfully.");
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
 
        return conn;
    }
    
    public String getUserName()throws SQLException{
        String userName = "hej";
        Connection conn = connect();
        try(PreparedStatement ps = conn.prepareStatement("SELECT * FROM Users");){
            ps.executeQuery();
        }
        return userName;
    }
    

}
