package com.grupo2.API_TraceFinder;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;

public class DBConexao { 
    
    private static final String USERNAME = "root";
    private static final String PASSWORD = "root";
    private static final String DATABASE_URL = "jdbc:mysql://localhost:3306/trace_finder";
    
    public static Connection abrirConexao() throws Exception
    {
                
        Class.forName("com.mysql.jdbc.Driver");
        Connection conectar = DriverManager.getConnection(DATABASE_URL,USERNAME,PASSWORD);
        
        return conectar;
    }
    
    public static void fecharConexao(Connection conectar) throws SQLException
    {
        conectar.close();
    }
}
    



