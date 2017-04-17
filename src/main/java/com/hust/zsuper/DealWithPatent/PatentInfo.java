package com.hust.zsuper.DealWithPatent;

/**
 * ValueObject for Reading a Value from Excel File (XLSX)
 * Created by zsuper on 2017/4/13.
 */
public class PatentInfo {

    private String PatentId;    //申请号
    private String Patent_Name;     //名称
    private String Patent_primary_classification_number;    //主分类号
    private String Patent_classification_number;    //分类号
    private String Patentee;    //申请人、专利权人
    private String Designer;    //发明人
    private String Patent_publication_date;     //公开日
    private String Patent_agency;   //代理机构
    private String Patent_agencyer;     //代理人
    private String Patent_application_date;     //申请日
    private String Patent_location;     //地址
    private String Patent_priority;     //优先权
    private String Patent_country_code;     //国省代码
    private String Patent_abstract;     //摘要
    private String Patent_sovereignty;      //主权项
    private String Patent_oversea_applicatino;      //国际申请
    private String Patent_oversea_publication;      //国际公布
    private String Patent_oversea_date;     //进入国家日期
    private String Patent_Id_before;        //分案原申请号
    private String Patent_claim;        //专利要求书
    private String Patent_legal_status;     //法律专挑
    private String Patent_priority_status;      //专利权状态代码
    private String Patent_type;    //专利类型

    public String getPatentId() {
        return PatentId;
    }

    public void setPatentId(String patentId) {
        PatentId = patentId;
    }

    public String getPatent_Name() {
        return Patent_Name;
    }

    public void setPatent_Name(String patent_Name) {
        Patent_Name = patent_Name;
    }

    public String getPatent_primary_classification_number() {
        return Patent_primary_classification_number;
    }

    public void setPatent_primary_classification_number(String patent_primary_classification_number) {
        Patent_primary_classification_number = patent_primary_classification_number;
    }

    public String getPatent_classification_number() {
        return Patent_classification_number;
    }

    public void setPatent_classification_number(String patent_classification_number) {
        Patent_classification_number = patent_classification_number;
    }

    public String getPatentee() {
        return Patentee;
    }

    public void setPatentee(String patentee) {
        Patentee = patentee;
    }

    public String getDesigner() {
        return Designer;
    }

    public void setDesigner(String designer) {
        Designer = designer;
    }

    public String getPatent_publication_date() {
        return Patent_publication_date;
    }

    public void setPatent_publication_date(String patent_publication_date) {
        Patent_publication_date = patent_publication_date;
    }

    public String getPatent_agency() {
        return Patent_agency;
    }

    public void setPatent_agency(String patent_agency) {
        Patent_agency = patent_agency;
    }

    public String getPatent_agencyer() {
        return Patent_agencyer;
    }

    public void setPatent_agencyer(String patent_agencyer) {
        Patent_agencyer = patent_agencyer;
    }

    public String getPatent_application_date() {
        return Patent_application_date;
    }

    public void setPatent_application_date(String patent_application_date) {
        Patent_application_date = patent_application_date;
    }

    public String getPatent_location() {
        return Patent_location;
    }

    public void setPatent_location(String patent_location) {
        Patent_location = patent_location;
    }

    public String getPatent_priority() {
        return Patent_priority;
    }

    public void setPatent_priority(String patent_priority) {
        Patent_priority = patent_priority;
    }

    public String getPatent_country_code() {
        return Patent_country_code;
    }

    public void setPatent_country_code(String patent_country_code) {
        Patent_country_code = patent_country_code;
    }

    public String getPatent_abstract() {
        return Patent_abstract;
    }

    public void setPatent_abstract(String patent_abstract) {
        Patent_abstract = patent_abstract;
    }

    public String getPatent_sovereignty() {
        return Patent_sovereignty;
    }

    public void setPatent_sovereignty(String patent_sovereignty) {
        Patent_sovereignty = patent_sovereignty;
    }

    public String getPatent_oversea_applicatino() {
        return Patent_oversea_applicatino;
    }

    public void setPatent_oversea_applicatino(String patent_oversea_applicatino) {
        Patent_oversea_applicatino = patent_oversea_applicatino;
    }

    public String getPatent_oversea_publication() {
        return Patent_oversea_publication;
    }

    public void setPatent_oversea_publication(String patent_oversea_publication) {
        Patent_oversea_publication = patent_oversea_publication;
    }

    public String getPatent_oversea_date() {
        return Patent_oversea_date;
    }

    public void setPatent_oversea_date(String patent_oversea_date) {
        Patent_oversea_date = patent_oversea_date;
    }

    public String getPatent_Id_before() {
        return Patent_Id_before;
    }

    public void setPatent_Id_before(String patent_Id_before) {
        Patent_Id_before = patent_Id_before;
    }

    public String getPatent_claim() {
        return Patent_claim;
    }

    public void setPatent_claim(String patent_claim) {
        Patent_claim = patent_claim;
    }

    public String getPatent_legal_status() {
        return Patent_legal_status;
    }

    public void setPatent_legal_status(String patent_legal_status) {
        Patent_legal_status = patent_legal_status;
    }

    public String getPatent_priority_status() {
        return Patent_priority_status;
    }

    public void setPatent_priority_status(String patent_priority_status) {
        Patent_priority_status = patent_priority_status;
    }


    public String getPatent_type() {
        return Patent_type;
    }

    public void setPatent_type(String patent_type) {
        Patent_type = patent_type;
    }
}
