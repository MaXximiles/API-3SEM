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
    
//    public static Statement conexao() throws Exception 
//    {
//      Connection connection = null;
//      Statement statement = null;
//      ResultSet resultSet = null;
      
//      connection=DriverManager.getConnection(DATABASE_URL,USERNAME,PASSWORD);
//      statement=connection.createStatement();
//        try{
//
//         
//
//      }catch(SQLException ex){}
//       finally{
//            resultSet.close();
//            statement.close();
//            connection.close();
//         }
//        return statement;
//    }    
//}
//         resultSet=statement.executeQuery
//            ("SELECT * FROM concessionaria");
//         while(resultSet.next()){
//            System.out.printf(resultSet.getString("concessionaria_id"));
//         }



