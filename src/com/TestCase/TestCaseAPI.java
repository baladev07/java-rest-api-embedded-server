package com.TestCase;

import com.Model.TestCasesObject;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.List;
import java.util.Map;

public class TestCaseAPI {

    public static JSONObject postTestCaseJson() throws JSONException {
        JSONObject json2=new JSONObject();
        Map<String,Object> testDateMap= com.Util.FileUtil.getTestGroupDataMap();
        try{
            JSONObject json1=new JSONObject();
            json2.put("Message","TestGroup  Created successfully");
            json1.put("testGroupId",testDateMap.get("id"));
            json1.put("TestGroupName",testDateMap.get("fileName"));
            json1.put("createdTime",testDateMap.get("createdTime"));
            json1.put("lastUpdatedTime",testDateMap.get("lastUpdatedTime"));
            json2.put("Test",json1);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return json2;
    }

    public static JSONObject getTestCaseJson(List<TestCasesObject> testCasesObject) throws JSONException {
        JSONObject json2=new JSONObject();
        JSONObject json1=new JSONObject();
        JSONArray json0=new JSONArray();
        //json2.put("Message","");
        try{
            for(TestCasesObject obj:testCasesObject)
            {
                json1.put("id",obj.getTestCaseId());
                json1.put("requestApi",obj.getReqApi());
                json1.put("requestHeader",obj.getReqHeader());
                json1.put("requestPayload",obj.getReqPayload());
                json1.put("createdTime",obj.getCreatedTime());
                json1.put("lastUpdatedTime",obj.getLastUpdatedTime());
                json1.put("requestMethod",obj.getReqMethod());
                json1.put("minResTime",obj.getMinResTime());
                json1.put("maxResTime",obj.getMaxResTime());
                json1.put("buildLabel",obj.getBuildLabel());
                json0.put(json1);
            }
            json2.put("TestCases",json0);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return json2;
    }


}
