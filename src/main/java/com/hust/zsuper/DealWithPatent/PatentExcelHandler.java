package com.hust.zsuper.DealWithPatent;

/**
 * Created by zsuper on 2017/4/13.
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.HashMap;
import java.util.List;
import java.util.Map;


import com.hust.zsuper.Configuration;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class PatentExcelHandler {
    private static final Log LOG = LogFactory.getLog(PatentExcelHandler.class);


//    public PatentExcelHandler(){}

    public static void main(String[] args) throws Exception {
        String PATENT_DATA_FILE_PATH = Configuration.sourceFilePath;

        // input file initialize
        File file = new File(PATENT_DATA_FILE_PATH);
        InputStream inputStream = new FileInputStream(file);

        Map<String, String> cellMapping = new HashMap<String, String>();
        cellMapping.put("HEADER", "申请号,名称,主分类号,分类号,申请（专利权）人,发明（设计）人,公开（公告）日," +
                "公开（公告）号,专利代理机构,代理人,申请日,地址,优先权,国省代码,摘要,主权项,国际申请,国际公布," +
                "进入国家日期,分案原申请号,权利要求书,法律状态,专利权状态代码,专利类型");
        cellMapping.put("A", "PatentId");
        cellMapping.put("B", "Patent_Name");
        cellMapping.put("C", "Patent_primary_classification_number");
        cellMapping.put("D", "Patent_classification_number");
        cellMapping.put("E", "Patentee");
        cellMapping.put("F", "Designer");
        cellMapping.put("G", "Patent_publication_date");
        cellMapping.put("H", "Patent_agency");
        cellMapping.put("I", "Patent_agencyer");
        cellMapping.put("J", "Patent_application_date");
        cellMapping.put("K", "Patent_location");
        cellMapping.put("L", "Patent_priority");
        cellMapping.put("M", "Patent_country_code");
        cellMapping.put("N", "Patent_abstract");
        cellMapping.put("O", "Patent_sovereignty");
        cellMapping.put("P", "Patent_oversea_applicatino");
        cellMapping.put("Q", "Patent_oversea_publication");
        cellMapping.put("R", "Patent_oversea_date");
        cellMapping.put("S", "Patent_Id_before");
        cellMapping.put("T", "Patent_claim");
        cellMapping.put("U", "Patent_legal_status");
        cellMapping.put("V", "Patent_priority_status");
        cellMapping.put("W", "Patent_type");

    }

        // 瞬时完成


    private static void displayPersonList(List<PatentInfo> patents) {
        for (PatentInfo p : patents){
            System.out.println(p.getPatent_Name());
        }
    }


}
