package com.hust.zsuper.DealWithEnterprises;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.util.List;

import com.hust.zsuper.Utils.*;
import com.hust.zsuper.Configuration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

/**
 * Created by zsuper on 2017/4/13.
 */
public class SaveData2mysql {

    private static final Log LOG = LogFactory.getLog(SaveData2mysql.class);

    public static void main(String[] args) {
        //读取文件
        try {
            // 传入地址，返回File对象的list
            List<File> files = FileProcess.getFile(Configuration.sourceFilePath);
            // 连接数据库
            Connection connection = Connect2Mysql.getConnection(Configuration.company_sql_address,
                    "root", "");


            // 遍历File对象
            for (File file : files) {
                BufferedReader bufferedReader = FileProcess.getReader(file);

                // 判断label是否相同
                String firstline=bufferedReader.readLine();
                for(String label:firstline.split("\t")){
                    if(){

                    }
                }

                String eachLine;
                while ((eachLine = bufferedReader.readLine()) != null) {

                    String[] parts = eachLine.split("\t");
                    for (String part : parts) {
                        if (!part.isEmpty()) {
                            part = part.replace(" ", "");
                            part = part.replace("（", "(");
                            part = part.replace("）", ")");
                            part = part.replace("'", "");
                        }
                    }
                }
                bufferedReader.close();
            }


        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            LOG.error(e.getMessage(), e.getCause());
        }


    }
}
