package com.hust.zsuper.Utils;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;


/**
 * Created by zsuper on 2017/3/1.
 *
 * @author zsuper
 * @version 1.0
 */
public class Connect2Mysqllll {
    private static final Log LOG = LogFactory.getLog(Connect2Mysqllll.class);
    private String sql_address;
    private static final String company_sql_address="jdbc:mysql://localhost/industry";


    /**
     * Constructor: 初始化，并创建连接
     * 直接连接工企数据库
     */
//    public Connect2Mysqllll() {
//        this.sql_address=company_sql_address;
//        try {
//            Class.forName("com.mysql.jdbc.Driver");
//            System.out.println("成功加载数据库驱动!");
//            Connection connect = DriverManager.getConnection(
//                    "jdbc:mysql://localhost/industry", "root", "");
//            System.out.println("成功建立数据库连接!");
//            return connect;
//        } catch (SQLException sqle) {
//            LOG.error(sqle.getMessage(), sqle.getCause());
//        } catch (ClassNotFoundException cne) {
//            LOG.error(cne.getMessage(), cne.getCause());
//        }
//        return null;
//    }

//    public Connection Connect2Mysql(String ){
//
//    }
//
//    /**
//     * @param connection 连接数据库
//     * @param list 一条数据
//     * @param query 判断数据的格式是否与表结构相符合
//     */
//    public void writeData2Mysql(Connection connection,List<String> list,String query){
//
//
//    }


    public static void writecompany2sql(String filepath, Connection connect) {
        List<String> list = new ArrayList<String>();
        try {
            String encoding = "UTF-8";
            File file = new File(filepath);

            if (file.isFile() && file.exists()) {

                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);

                BufferedReader bufferedReader = new BufferedReader(read);

                String lineTex = null;

                String query = "insert into CompanyName (OIBC,name)" + "values(?,?)";
                PreparedStatement preparedStatement = connect.prepareStatement(query);

                while ((lineTex = bufferedReader.readLine()) != null) {
                    String[] parts = lineTex.split("\t");
                    if (parts.length > 1) {
                        preparedStatement.setString(1, parts[0]);
                        preparedStatement.setString(2, parts[1]);
                        preparedStatement.execute();
                    } else {
                        System.out.println(parts[0]);
                    }

                }
                bufferedReader.close();

                read.close();
                preparedStatement.close();
                System.out.println("prepareStatement关闭！");

            } else {
                System.out.println("找不到文件");
            }

        } catch (Exception e) {
            System.out.println("读取文件失败");
            e.printStackTrace();
        }
//        return list;

    }

    public static void main(String[] args) {
        String filepath = "data/2012.txt";

        try {
            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("成功加载数据库驱动!");
        } catch (Exception e) {
            System.out.println("加载数据库驱动失败!");
            e.printStackTrace();
        }
        try {
            Connection connect = DriverManager.getConnection(
                    "jdbc:mysql://localhost/industry", "root", "");
            System.out.println("成功建立数据库连接!");
//            Statement stmt=connect.createStatement();

            //在此对数据库进行操作
            writecompany2sql(filepath, connect);
            connect.close();
            System.out.println("数据库连接关闭!");


        } catch (Exception e) {
            System.out.println("建立数据库连接失败!");
            e.printStackTrace();
        }


    }
}
