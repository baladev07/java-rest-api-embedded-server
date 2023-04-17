package com.Util;

import com.Model.FileObject;
import com.Model.TestCasesObject;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.hibernate.query.Query;
import org.yaml.snakeyaml.Yaml;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileUtil {

    public static void createTestGroup()
    {
        try{

            Map<String,Object>testGroupMap=getTestGroupDataMap();
            String ymlFile= (String) testGroupMap.get("ymlData");
            int testGroupId= (int) testGroupMap.get("id");
            Session session=null;
            Configuration con=new Configuration().configure().addAnnotatedClass(TestCasesObject.class);
            SessionFactory sf= con.buildSessionFactory();
            session= sf.openSession();
            session.beginTransaction();
            Yaml yaml = new Yaml();
            ObjectMapper objectMapper=new ObjectMapper();
            Map<Object, Map<Object, Map<Object, Object>>> ymlData = (Map<Object, Map<Object, Map<Object, Object>>>) yaml.load(ymlFile);
            List<Map<Object, Map<Object, Object>>> urlData = (List<Map<Object, Map<Object, Object>>>) ymlData.get("scenarios").get("ZE Expense CREATION 1-Http").get("requests");
            List<Map<Object, Object>> urlList = (List<Map<Object, Object>>) urlData.get(0).get("do");
            for (Map<Object, Object> urlMap : urlList) {
                session= sf.openSession();
                session.beginTransaction();
                TestCasesObject testCaseObj=new TestCasesObject();
                testCaseObj.setTestGroupId(testGroupId);
                Map<String,String>paramMap=null;
                Map<String,String>headerMap=null;
                String url = (String) urlMap.get("url");
                String method = (String) urlMap.get("method");
                paramMap = (Map<String, String>) urlMap.get("body");
                headerMap=(Map<String, String>)urlMap.get("headers");
                testCaseObj.setReqApi(url);
                testCaseObj.setReqMethod(method);
                String params=objectMapper.writeValueAsString(paramMap);
                String headers=objectMapper.writeValueAsString(headerMap);
                testCaseObj.setReqPayload(params);
                testCaseObj.setReqHeader(headers);
                session.saveOrUpdate(testCaseObj);
                session.getTransaction().commit();
            }
            session.close();
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static Map<String,Object> getTestGroupDataMap()
    {
        Session session=null;
        Configuration con=new Configuration().configure().addAnnotatedClass(FileObject.class);
        SessionFactory sf= con.buildSessionFactory();
        session= sf.openSession();
        session.beginTransaction();
        System.out.println("Done");
        Query query = session.createQuery("from FileObject order by FileId DESC");
        query.setMaxResults(1);
        FileObject fileObj= (FileObject) query.uniqueResult();
        int testGroupId=fileObj.getFileId();
        String ymlFile=fileObj.getFileReader();
        Map<String,Object>testGroupDateMap=new HashMap<>();
        testGroupDateMap.put("id",testGroupId);
        testGroupDateMap.put("ymlData",ymlFile);
        testGroupDateMap.put("fileName",fileObj.getFileName());
        testGroupDateMap.put("lastUpdatedTime",fileObj.getLastUpdatedTime());
        testGroupDateMap.put("createdTime",fileObj.getCreatedTime());
        return testGroupDateMap;

    }
    public static void main(String args[])

    {
        createTestGroup();
    }


    public static List<TestCasesObject>getTestCasesList(String testGroupId)
    {
        Session session=null;
        Configuration con=new Configuration().configure().addAnnotatedClass(TestCasesObject.class);
        SessionFactory sf= con.buildSessionFactory();
        session= sf.openSession();
        session.beginTransaction();
        CriteriaBuilder cb = session.getCriteriaBuilder();
        CriteriaQuery<Object> crt = cb.createQuery(Object.class);
        Root<TestCasesObject> root = crt.from(TestCasesObject.class);
        crt.select(root);
        crt.where(cb.equal(root.get("testGroupId"),Integer.parseInt(testGroupId)));
        javax.persistence.Query query= session.createQuery(crt);
        List<TestCasesObject>testCasesList=query.getResultList();
        return testCasesList;
    }
}
