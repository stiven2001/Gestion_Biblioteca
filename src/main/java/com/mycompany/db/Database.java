package com.mycompany.db;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Database {
    
    protected Connection conexion;
    private final String JDBC_DRIVER = "oracle.jdbc.driver.OracleDriver";
    private final String DB_URL = "jdbc:oracle:thin:@localhost:1521:ORCL";
    
    private final String USER = "BibliotecaIbero";
    private final String PASS = "123456";
    
    public void Conectar() throws ClassNotFoundException {
        try {
            conexion = DriverManager.getConnection(DB_URL, USER, PASS);
            Class.forName(JDBC_DRIVER);
        } catch (SQLException ex) {
            Logger.getLogger(Database.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    public void Cerrar() throws SQLException{
        if (conexion != null) {
            if (!conexion.isClosed()) {
                conexion.close();
            }
        }
    }
}