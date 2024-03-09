package edu.upb.chatupb.db;

import java.sql.Connection;

public class PruebaConexion {
    public static void main(String[] args) {
        Connection conn= ConnectionDB.instance.getConnection();
    }
}
