package com.hust.zsuper.DealWithEnterprises;

import jdk.nashorn.internal.runtime.arrays.IteratorAction;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.sql.*;
import java.util.*;

/**
 * Created by zsuper on 2017/4/18.
 * 用于检验 MergeCompany 的结果
 */
public class SearchCompany {
    private static final Log LOG = LogFactory.getLog(MergeCompany.class);

    /**
     * 连接数据库，输入查询命令
     */
    private static void searchCompany(Connection connection, String name) {
//        ArrayList<String> firm_corpus = new ArrayList<String>();
        Map<Integer, Integer> firm_corpus = new HashMap<Integer, Integer>();
        try {
            String command = "SELECT * FROM test WHERE firm_name LIKE ?";
            PreparedStatement statement_select = connection.prepareStatement(command);
            statement_select.setString(1, "%" + name + "%");

            String query_1 = "SELECT  FROM test WHERE firm_id=?";
            String query_2 = "SELECT * FROM test WHERE ID=?";
            PreparedStatement statement_1 = connection.prepareStatement(query_1);
            PreparedStatement statement_2 = connection.prepareStatement(query_2);


            System.out.println("正在查询：");
            ResultSet resultSet = statement_select.executeQuery();


            System.out.println("---------" + "年份" + "-----------" + "公司名" + "---------");
            //循环遍历，获取公司名
            while (resultSet.next()) {
                String firm_name = resultSet.getString(7);
                int firm_id = resultSet.getInt(3);
                int ID = resultSet.getInt(1);
                if (!firm_corpus.containsKey(firm_id)) {
                    System.out.println("         " + resultSet.getInt(2) + "     " + firm_name);
                }
                firm_corpus.put(firm_id, ID);
            }
            System.out.println();
            System.out.println();

            Iterator it = firm_corpus.keySet().iterator();
            int i = 1;

            while (it.hasNext()) {
                int ID_now = firm_corpus.get(it.next());


                statement_2.setInt(1, ID_now);
                ResultSet resultSet_2 = statement_2.executeQuery();

                while (resultSet_2.next()) {
                    System.out.println();
                    System.out.println("输出第" + i + "家公司具体信息");
                    System.out.println("---------" + "年份" + "-----------" + "公司名" + "---------"+
                                "state_hold"+"-----------" +"income_tax_pay");
                    System.out.println("         " + resultSet_2.getInt(2) + "         " +
                            resultSet_2.getString(7) + "         "+
                            resultSet_2.getDouble(12)+"               " +resultSet_2.getDouble(18));
                    if (resultSet_2.getInt(5) > 0) {
                        read_LBrother(connection, resultSet_2.getInt(5));
                    }
                    if (resultSet_2.getInt(6) > 0) {
                        read_RBrother(connection, resultSet_2.getInt(6));
                    }

                }
                i++;


                resultSet_2.close();

            }
            resultSet.close();



        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle.getCause());
        }
        firm_corpus.clear();


    }

    public static void read_LBrother(Connection connection, int LBrother_ID) {
        try{
            String query = "SELECT * FROM test WHERE ID=?";
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setInt(1,LBrother_ID);
            ResultSet rs=statement.executeQuery();

            while(rs.next()){
                System.out.println("         " + rs.getInt(2) + "         " +
                        rs.getString(7) + "         "+
                        rs.getDouble(12)+"               " +rs.getDouble(18));
                if (rs.getInt(5) > 0) {
                    read_LBrother(connection, rs.getInt(5));
                }
            }
            rs.close();


        }catch (SQLException sqle){
            LOG.error(sqle.getMessage(),sqle.getCause());
        }


    }

    public static void read_RBrother(Connection connection, int RBrother_ID) {
        try{
            String query = "SELECT * FROM test WHERE ID=?";
            PreparedStatement statement=connection.prepareStatement(query);
            statement.setInt(1,RBrother_ID);
            ResultSet rs=statement.executeQuery();

            while(rs.next()){
                System.out.println("         " + rs.getInt(2) + "         " +
                        rs.getString(7) + "         "+
                        rs.getDouble(12)+"               " +rs.getDouble(18));
                if (rs.getInt(6) > 0) {
                    read_RBrother(connection, rs.getInt(6));
                }
            }
            rs.close();


        }catch (SQLException sqle){
            LOG.error(sqle.getMessage(),sqle.getCause());
        }
    }

    public static void main(String[] args) {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to database...");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/industry_raw",
                    "root", "123456");

            /**
             * 输入查询的公司名
             */

            String firm_name = "东莞市深联造纸有限公司";
//            String firm_name = "长老乡万星锡矿";
//            String firm_name="中国海洋石油渤海公司";
//            String firm_name="启东恒融机械制造厂";

            searchCompany(connection, firm_name);
            System.out.println("Close the connection!");
            connection.close();


        } catch (ClassNotFoundException cnfe) {
            LOG.error(cnfe.getMessage(), cnfe.getCause());
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle.getCause());
        }

    }
}
