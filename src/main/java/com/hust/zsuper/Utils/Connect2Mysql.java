package com.hust.zsuper.Utils;

import java.io.File;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;

/**
 * Created by zsuper on 2017/4/13.
 */
public class Connect2Mysql {

    /**
     * @param host
     * @param port
     * @param schema
     * @param user
     * @param password
     * @return connection
     */
    public static Connection getConnection(
            final String host,
            final int port,
            final String schema,
            final String user,
            final String password) throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection("jdbc:mysql://" + host + ":" + port + "/" + schema, user, password);
    }

    /**
     * 便于操作，直接传入地址
     * @return connection
     */
    public static Connection getConnection(String address, String user, String password)
            throws ClassNotFoundException, SQLException {
        Class.forName("com.mysql.jdbc.Driver");
        return DriverManager.getConnection(address, user, password);
    }

    /**
     * 增删字段
     */

//    public static void build_some_labels(Connection connection){
//        try{
//            Statement statement =connection.createStatement();
//
//
//            String create_ID="ALTER TABLE test add ID int()"
//
//        }catch(SQLException sqle){
//            LOG.error(sqle.getMessage(),sqle.getCause());
//        }
//
//    }







}
