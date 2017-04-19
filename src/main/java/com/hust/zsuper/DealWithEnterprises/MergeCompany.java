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
            String query1 = "SELECT * FROM test limit" + row + ",1";

            // 存在问题，遍历到最后的时候可能出现这句话运行不了
            PreparedStatement statement = connection.prepareStatement(query1);

            ResultSet rs = statement.executeQuery();

            nianfen=rs.getInt(2);
            if (!setID.containsKey(nianfen)){
                setID.put(nianfen,nianfen*10000000);

            }

            statement.setInt(1,setID.get(nianfen)+1);








        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle.getCause());
        }

    }

    public static void main(String[] args) {
        try {
            Class.forName("com.mysql.jdbc.Driver");
            Connection connection = DriverManager.getConnection(Configuration.company_sql_address,
                    "root", "");

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
