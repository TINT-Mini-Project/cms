/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package connection;


import java.sql.*;

/**
 *
 * @author ADITYA N SAH
 */
public class DBHandler {
        public static int setData(String query) throws SQLException, ClassNotFoundException {
        Connection conn = DBManager.getConnection();
        Statement stm = conn.createStatement();
        int result = stm.executeUpdate(query);
        return result;
    }

    public static ResultSet getData(String query) throws SQLException, ClassNotFoundException {
        Connection con = DBManager.getConnection();
        Statement stm = con.createStatement();
        ResultSet result = stm.executeQuery(query);
        return result;
    }
}
