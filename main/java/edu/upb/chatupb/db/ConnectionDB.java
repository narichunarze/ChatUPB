package edu.upb.chatupb.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class ConnectionDB {
    private final String url, user, password;
    static final ConnectionDB instance= new ConnectionDB();

    private ConnectionDB(){
        this.url="jdbc:mysql://localhost:3306/mensajeria";
        this.user= "root";
        this.password="sns181923";
    }

    public Connection getConnection(){
        Connection conn = null;
        try{
            conn= DriverManager.getConnection(url,user,password);
            if(conn != null){
                System.out.println("Conexion exitosa");
            }else{
                System.out.println("Conexion fallida");
            }

        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }

}
