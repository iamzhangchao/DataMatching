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
    private static final long serialVersionUID = 919286545866124006L;

    private static void Merge(Connection connection) {

        Map<Integer, Integer> setBrotherID = new TreeMap<Integer, Integer>();
//        TreeMap setBrotherID=new TreeMap();

        try {

            String query_1 = "SELECT firm_id,tag FROM test";
            PreparedStatement statement_1 = connection.prepareStatement(query_1);

            String query_2 = "SELECT ID,nianfen " +
                    "from test WHERE firm_id = ?";
            PreparedStatement statement_2 = connection.prepareStatement(query_2);

            // 更新数据LB,RB
            String query_3 = "UPDATE test SET RBrother_ID = ?,tag=TRUE WHERE ID = ?";
            String query_4 = "UPDATE test SET LBrother_ID = ?,tag=TRUE WHERE ID = ?";
            String query_5 = "UPDATE test SET tag=TRUE WHERE ID = ?";
            PreparedStatement statement_3 = connection.prepareStatement(query_3);
            PreparedStatement statement_4 = connection.prepareStatement(query_4);
            PreparedStatement statement_5 = connection.prepareStatement(query_5);

            ResultSet rs_1 = statement_1.executeQuery();
            while (rs_1.next()) {
                // Ensure the item has not been processed according to tag
                if (!rs_1.getBoolean(2)) {
                    int firm_id = rs_1.getInt(1);
                    statement_2.setInt(1, firm_id);
                    ResultSet rs_2 = statement_2.executeQuery();

                    //循环读取查询的结果，保存到setID中
                    while (rs_2.next()) {
                        setBrotherID.put(rs_2.getInt(2), rs_2.getInt(1));
                    }

                    //更新数据 LB,RB
                    Iterator it = setBrotherID.keySet().iterator();
                    int size = setBrotherID.size();
                    int a, b;
                    a = setBrotherID.get(it.next());
                    if (size == 1) {
                        statement_5.setInt(1, a);
                        statement_5.executeUpdate();
                    }

                    while (size > 1 && it.hasNext()) {
                        b = setBrotherID.get(it.next());

                        statement_3.setInt(1, b);
                        statement_3.setInt(2, a);
                        statement_3.executeUpdate();

                        statement_4.setInt(1, a);
                        statement_4.setInt(2, b);
                        statement_4.executeUpdate();

                        a = b;
                    }
                    rs_2.close();
                }
                setBrotherID.clear();

            }
            rs_1.close();
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle.getCause());
        }

    }

    public static void main(String[] args) {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to database...");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/industry_raw",
                    "root", "123456");
            Merge(connection);
            System.out.println("Close the connection!");
            connection.close();


        } catch (ClassNotFoundException cnfe) {
            LOG.error(cnfe.getMessage(), cnfe.getCause());
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle.getCause());
        }
    }


    /**
     * 建立list,依次读取法人代码，进行比较之后进行，查找操作判断，
     * 或者：依次读取法人代码，对全库进行查找，对于每个查找到的数据，设置一个tag
     */


}
