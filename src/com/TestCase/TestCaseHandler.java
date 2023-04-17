package com.TestCase;

import com.Exception.GeneralException;
import com.RestApi.APIHandler;
import com.RestApi.RestAPIcontext;
import com.Util.FileUtil;
import com.Util.SqlUtil;
import com.Util.Util;
import org.apache.commons.fileupload.FileItemIterator;
import org.apache.commons.fileupload.FileItemStream;
import org.apache.commons.fileupload.servlet.ServletFileUpload;
import org.json.JSONObject;

import java.io.*;

public class TestCaseHandler extends  APIHandler {
    @Override
    public Object handlePreprocess(RestAPIcontext context) {
        Object obj=null;
        JSONObject testCasesJson=null;
        try {
            String reqUri=context.getURI();
            if(reqUri.contains("/start/"))
            {
                String testGroupId=context.getID();
                TestCaseRunner.startTestCaseRunner(testGroupId);
                Util.handleResponse(context,new JSONObject().put("message","Investigation Started"));
            }
            if(reqUri.contains("/testgroup"))
            {
                  //TestCasesObject testCasesObject= (TestCasesObject) FileUtil.getTestCasesList(context.getID());
                  testCasesJson= TestCaseAPI.getTestCaseJson(FileUtil.getTestCasesList(context.getID()));
                  Util.handleResponse(context,testCasesJson);
            }
            else{
                throw  new GeneralException(context);
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return null;
    }

    @Override
    public void processDo(Object object,RestAPIcontext context) throws Exception {
        try {
            boolean isMultipart = ServletFileUpload.isMultipartContent(context.getRequest());
            if (isMultipart) {
                ServletFileUpload upload = new ServletFileUpload();
                FileItemIterator iter = upload.getItemIterator(context.getRequest());
                while (iter.hasNext()) {
                    FileItemStream item = iter.next();
                   // String name = item.getFieldName();
                    InputStream in = item.openStream();
                    if (item.isFormField()) {
                        continue;
                    } else {
                        InputStreamReader reader = new InputStreamReader(in);
                        String fileName = item.getName();
                        long createdTime = Util.getCurrentTimeInMillis();
                        Boolean isFileUploaded = SqlUtil.uploadFile(reader, fileName, createdTime, createdTime, context.getSqlQuery());
                        if (isFileUploaded) {
                            FileUtil.createTestGroup();
                            JSONObject resJson= com.TestCase.TestCaseAPI.postTestCaseJson();
                            Util.handleResponse(context,resJson);
                        }
                    }
                }
            }
        }
        catch(Exception e)
        {
            if(Util.isSqlDuplicatedKey(e))
            {
                throw new Exception(e);
            }
            e.printStackTrace();
        }
    }

    @Override
    public Object postProcess(RestAPIcontext context){
        return null;
    }
    public void method() throws InterruptedException {
        try{
            Thread.sleep(60000);
            System.out.print("i am executed finally hurrae");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
    }
    public static void  main(String args[]) throws Exception {
        try{
            com.TestCase.TestCaseRunner.runTestCase("13");

        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
