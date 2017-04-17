package com.hust.zsuper.Utils;

import java.io.*;

/**
 * @author zsuper
 *         输入txt文档，输出为TXT文档
 *         遍历每一条数据，去除字段中的空格
 */
public class remove_illegal_character2 {
    private static void readPath(String documetnFolderPath) {
        String[] fileList = new File(documetnFolderPath).list();
        for (String filename : fileList) {
            String filePath = documetnFolderPath +"/"+ filename;
            String newFilePath="data3/"+filename;
//            System.out.println(filePath);

            removeError(filePath,newFilePath);
        }
    }

    private static void removeError(String filePath,String newFilePath) {
        try{
            String Encoding="UTF-8";
            File file=new File(filePath);
            newFilePath=newFilePath.replace("txt","csv");
            File newFile=new File(newFilePath);

            try{
                if(!newFile.exists()){
                    boolean b=newFile.createNewFile();
                }
            }catch(Exception e){
                System.out.println();
                System.out.println("无法创建新文件"+newFilePath);
                e.printStackTrace();

            }


            String eachLine=null;

            if(file.isFile()&&newFile.isFile()&&file.exists()&&newFile.exists()){
                System.out.println("adfadsfhaha");

                InputStreamReader read = new InputStreamReader(new FileInputStream(file), Encoding);
                BufferedReader bufferedReader = new BufferedReader(read);

                OutputStreamWriter writer=new OutputStreamWriter(new FileOutputStream(newFile),"GBK");
                BufferedWriter bw=new BufferedWriter(writer);

                while((eachLine=bufferedReader.readLine())!=null){
//                    System.out.println("haha");
                    String[] parts=eachLine.split("\t");

                    for(String part:parts){
                        //先判断是否字段为空，如果为空，则跳过，若有数据，则先去除掉空格（暂不考虑英文名）
                        if(!part.isEmpty()){
                            part=part.replace(" ","");
                            part=part.replace("（","(");
                            part=part.replace("）",")");
                            part=part.replace("'","");
                        }
                        bw.write(part);
                        bw.write(",");
                    }
                    bw.newLine();


                }
                bw.close();
                read.close();

            }
            System.out.println(newFilePath+"清洗成功！！！");

        }catch(Exception e){
            System.out.println(filePath+"文件读取失败！！");
            e.printStackTrace();
        }

    }

    public static void main(String[] args) {
        String documetnFolderPath = "data2";

        readPath(documetnFolderPath);

    }
}
