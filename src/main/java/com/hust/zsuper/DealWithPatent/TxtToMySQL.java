package com.hust.zsuper.DealWithPatent;

import com.hust.zsuper.Configuration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zsuper on 2017/5/8.
 */
public class TxtToMySQL {
    private static final Log LOG = LogFactory.getLog(TxtToMySQL.class);
    private static List<File> filelist = new ArrayList<File>();
    private static String[] all_labels ={
            "申请（专利权）人","type","firm_id","公开（公告）号","公开（公告）日","申请号","申请日",
            "专利号","名称","主分类号","分类号","分案原申请号","优先权","发明（设计）人","地址","颁证日",
            "国际申请","国际公布","进入国家日期","专利代理机构","代理人","摘要","主权项","参考文献",
            "审查员","光盘号","PCT公开","PCT申请","PCT信息","范畴分类","页数","国省代码"};


    private static List<File> getFileList(String strPath) {
        File dir = new File(strPath);
        File[] files = dir.listFiles(); // 该文件目录下文件全部放入数组
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (files[i].isDirectory()) { // 判断是文件还是文件夹
                    getFileList(files[i].getAbsolutePath()); // 获取文件绝对路径
                } else if (fileName.endsWith(".txt")) { // 判断文件名是否以结尾
                    String strFileName = files[i].getAbsolutePath();
                    System.out.println("---" + strFileName);
                    filelist.add(files[i]);
                } else {
                    continue;
                }
            }
        }
        return filelist;
    }

    private static void txtToMySQL(List<File> fileList, Connection connection) {
        /**
         * 循环进行读取txt文件
         */
        Map<String, String> basis_info = new HashMap<String, String>();
        Map<String, String> add_info = new HashMap<String, String>();
        for (File file : fileList) {
            System.out.println("！！！！！！！！！！开始读第一个文件");
            String encoding = "GBK";
            try {
                InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);
                BufferedReader bufferedReader = new BufferedReader(read);
                String lineTXT =null;
                String label = "";
                String data = "";
                String labels = "";
                String datas = "";
                Statement stmt = connection.createStatement();
                while ((lineTXT = bufferedReader.readLine()) != null) {
//                    System.out.println("进入循环");
//                    System.out.println(lineTXT);
                    if (lineTXT.startsWith("*")) {
                        System.out.println("开头为*");
                        basis_info.clear();
                        add_info.clear();
                    }
                    if (lineTXT.startsWith("<")) {
                        data = lineTXT.split("=")[1];
                        label = lineTXT.split("=")[0].replace("<", "").replace(">", "");
//                        if(!basis_info.containsKey(lable)){
//                            basis_info.put(lable,data);
//                        }

                        if(isIn(label,all_labels)){
                            datas = datas + "\""+data +"\""+ ",";
                            labels = labels + label + ",";
                        }
                    }
                    if(lineTXT.startsWith("【")){
                        if(!datas.isEmpty()){
                            System.out.println("写入数据库");
                            System.out.println(labels);
                            System.out.println(datas);
                            // 进行将map中数据写入数据库的操作
                            String query = "insert into patent_exp (" + labels.substring(0, labels.length() - 1) +
                                    ") values(" + datas.substring(0, datas.length() - 1) + ")";
                            stmt.executeUpdate(query);
                            datas = "";
                            labels = "";
                        }
                    }
                    //存储状态信息，暂时先不考虑
//                    if(lineTXT.startsWith("【")){
//
//                    }
                }
                bufferedReader.close();
                read.close();


            } catch (FileNotFoundException fnfe) {
                fnfe.printStackTrace();
            } catch (UnsupportedEncodingException usee) {
                usee.printStackTrace();
//                LOG.error(usee.getMessage(), usee.getCause());
            } catch (IOException ioe) {
                ioe.printStackTrace();
                LOG.error(ioe.getMessage(), ioe.getCause());
            } catch (SQLException sqle) {
                sqle.printStackTrace();
                LOG.error(sqle.getMessage(), sqle.getCause());
            }


        }


    }
    /**
     * JAVA判断字符串数组中是否包含某字符串元素
     *
     * @param substring 某字符串
     * @param source 源字符串数组
     * @return 包含则返回true，否则返回false
     */
    public static boolean isIn(String substring, String[] source) {
        if (source == null || source.length == 0) {
            return false;
        }
        for (int i = 0; i < source.length; i++) {
            String aSource = source[i];
            if (aSource.equals(substring)) {
                return true;
            }
        }
        return false;
    }

    public static void main(String[] args) {
        try {

            Class.forName("com.mysql.jdbc.Driver");
            System.out.println("Connecting to database...");
            Connection connection = DriverManager.getConnection(
                    "jdbc:mysql://localhost:3306/industry_raw",
                    "root", "123456");
            // 进行存储操作
            txtToMySQL(getFileList(Configuration.patentPath), connection);
            connection.close();

        } catch (ClassNotFoundException cnfe) {
            LOG.error(cnfe.getMessage(), cnfe.getCause());
        } catch (SQLException sqle) {
            LOG.error(sqle.getMessage(), sqle.getCause());
        }
    }

}
