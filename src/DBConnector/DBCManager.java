package DBConnector;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

import java.sql.*;

/**
 *
 * @author ADITYA N SAH
 */
public class DBCManager {
    
    public static Connection con = null;
    private static boolean ConnectionEstablished = false;
    
    public static void establishConnection() throws SQLException, ClassNotFoundException {
        Class.forName("com.mysql.jdbc.Driver");
        con = DriverManager.getConnection(Const.url, Const.user, Const.pass);
        ConnectionEstablished = true;
    }
    
    public static Connection getConnection()throws SQLException, ClassNotFoundException{
        if(!ConnectionEstablished)
            establishConnection();
        return con;
    }
}