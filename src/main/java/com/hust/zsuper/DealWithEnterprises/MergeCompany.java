package com.hust.zsuper.DealWithEnterprises;


import com.hust.zsuper.Configuration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.*;
import java.util.*;

/**
 * Created by zsuper on 2017/4/13.
 */
public class MergeCompany {
    private static final Log LOG = LogFactory.getLog(MergeCompany.class);


    /**
     * 连接数据库
     */

    private static void createID(Connection connection) {

        Map<Integer, Integer> setID = new HashMap<Integer, Integer>();
        int row = 0;
        int nianfen=0;
        try {
            String query = "SELECT * FROM test limit " + row;
            System.out.println("Creating statement...");
            PreparedStatement statement = connection.prepareStatement(query);

            ResultSet rs = statement.executeQuery();
            while(rs.next()){

                nianfen=rs.getInt(2);
                System.out.println(nianfen);
                if (!setID.containsKey(nianfen)){
                    setID.put(nianfen,nianfen*10000000);
                    System.out.println("数据中新增年份："+nianfen);

                }
                statement.setInt(1,setID.get(nianfen)+1);
            }
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle.getCause());
        }

    }

    public static void main(String[] args) {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to database...");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost：3306/industry_raw",
                    "root", "123456");
            System.out.println("Connecting to database success ！");

            createID(connection);


        } catch (ClassNotFoundException cnfe) {
            LOG.error(cnfe.getMessage(), cnfe.getCause());
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle.getCause());
        }
    }


    /**
     * 新建ID字段，建立ID索引、公司名索引。新建LBrother_id,RBrother_id字段，新建tag字段，默认为N。
     */

    /**
     * 建立list,依次读取法人代码，进行比较之后进行，查找操作判断，
     * 或者：依次读取法人代码，对全库进行查找，对于每个查找到的数据，设置一个tag
     */


}
