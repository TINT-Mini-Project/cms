package test;


import java.sql.*;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

/**
 *
 * @author ADITYA N SAH
 */
public class Test {
    public static void main(String args[]) throws SQLException, ClassNotFoundException{
        ResultSet rs = connection.DBHandler.getData("show tables");
        while(rs.next())  
            System.out.println(rs.getString(1)); 
        rs.close();
    }
}
