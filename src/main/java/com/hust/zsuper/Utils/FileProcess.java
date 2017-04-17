package com.hust.zsuper.Utils;

import com.hust.zsuper.Configuration;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by zsuper on 2017/4/13.
 */
public class FileProcess {
    /**
     * @param filePath {@link String} object
     * @return fileList - a list of File object
     * getfile中只考虑了txt
     */

    public static List<File> getFile(String filePath) throws Exception {
        List<File> fileList = new ArrayList<File>();
        if (null == filePath || filePath.isEmpty()) {
            throw new Exception("文件路径为空");
        }
        File dir = new File(filePath);
        File[] files = dir.listFiles();
        if (files != null) {
            for (int i = 0; i < files.length; i++) {
                String fileName = files[i].getName();
                if (files[i].isDirectory()) {
                    getFile(files[i].getAbsolutePath());
                } else if (fileName.endsWith("txt")) {
                    String strFileName = files[i].getAbsolutePath();
                    fileList.add(files[i]);
                }
            }
        }
        return fileList;

    }

    public static File createFile(String filePath) throws Exception {
        if (null == filePath) {
            throw new Exception("文件路径为空");
        }
        return new File(filePath);
    }

    /**
     * @param file {@link File} object
     * @return bufferedRead
     */

    public static BufferedReader getReader(File file) throws UnsupportedEncodingException, FileNotFoundException {
        if (file.isFile() && file.exists()) {
            InputStreamReader read = new InputStreamReader(
                    new FileInputStream(file), Configuration.encoding);
            return new BufferedReader(read);
        } else {
            System.out.println("创建BufferedReader过程中出现异常！");
            return null;
        }
    }

    /**
     * @param file {@link File} object
     * @return bufferedWriter
     */
    public static BufferedWriter getWriter(File file) throws FileNotFoundException, UnsupportedEncodingException {
        if (file.isFile() && file.exists()) {
            OutputStreamWriter writer = new OutputStreamWriter(
                    new FileOutputStream(file), Configuration.encoding);
            return new BufferedWriter(writer);

        } else {
            System.out.println("创建BufferedWriter过程中出现异常！");
            return null;
        }
    }
}
