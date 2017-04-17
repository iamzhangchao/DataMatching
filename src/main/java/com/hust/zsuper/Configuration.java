package com.hust.zsuper;

import java.util.ArrayList;
import java.util.List;

import static java.util.Arrays.asList;

/**
 * @author zsuper
 *
 */

public class Configuration {

    public static String currentPath=System.getProperty("user.dir");
    public static String sourceFilePath =currentPath+"/Data/patent_sample.xls";
    public static String outResultPath=currentPath+"";
    public static String encoding="UTF-8";
    public static String company_sql_address="jdbc:mysql://localhost/industry";

    public static List<String> Sql_Label_Name=asList("id","name");


}