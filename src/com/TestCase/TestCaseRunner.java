package com.TestCase;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;

import java.io.InputStreamReader;
import java.net.URL;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.TimeUnit;

import javax.net.ssl.HttpsURLConnection;

import com.Model.TestCasesObject;
import com.Util.FileUtil;
import com.Util.Util;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.json.JSONException;
import org.json.JSONObject;
import org.yaml.snakeyaml.Yaml;

public class TestCaseRunner
{
    static List<Integer> timeList=new ArrayList<>();
    static int sc_ok=200;
    public static void runTestCase(String testGroupId) throws IOException {
        try
        {
//            InputStream inputStream = new FileInputStream(System.getProperty("user.home") + "/functions/newFramework/conf/api.yaml");
            InputStream inputStream1 = new FileInputStream(System.getProperty("user.home") + "/IdeaProjects/project/conf/properties.yml");  // No I18N
           Yaml yaml = new Yaml();
//            Map<Object, Map<Object, Map<Object, Object>>> ymlData = (Map<Object, Map<Object, Map<Object, Object>>>) yaml.load(inputStream);
//            List<Map<Object, Map<Object, Object>>> apiData = (List<Map<Object, Map<Object, Object>>>) ymlData.get("scenarios").get("ZE Expense CREATION 1-Http").get("requests");
//            List<Map<Object, Object>> apiList = (List<Map<Object, Object>>) apiData.get(0).get("do");
            Map<String, String> ymlData1 = (Map<String, String>) yaml.load(inputStream1);
//            String buildLabel = getBuildLabel(ymlData1);
            String token=getToken(ymlData1);
            Session session=null;
            Configuration con=new Configuration().configure().addAnnotatedClass(TestCasesObject.class);
            SessionFactory sf= con.buildSessionFactory();
            session= sf.openSession();
            session.beginTransaction();
//            CriteriaBuilder cb = session.getCriteriaBuilder();
//            CriteriaQuery<Object> crt = cb.createQuery(Object.class);
//            Root<TestCasesObject> root = crt.from(TestCasesObject.class);
//            crt.select(root);
//            crt.where(cb.equal(root.get("testGroupId"),Integer.parseInt(testGroupId)));
//            Query query= session.createQuery(crt);
            List<TestCasesObject>testCasesList= FileUtil.getTestCasesList(testGroupId);
            DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy");
            Date date = new Date();
            String currentDate = formatter.format(date);
            List<TestCasesObject> testCaseList=new ArrayList<>();
            for (TestCasesObject testCase : testCasesList) {
                session= sf.openSession();
                session.beginTransaction();
                TestCasesObject testCasesObject=session.load(TestCasesObject.class,testCase.getTestCaseId());
                Map<String, String> paramsMap = null;
                String url = testCase.getReqApi();
                String method = testCase.getReqMethod();
                String params=testCase.getReqPayload();
                int runCount=testCase.getRunCount()+1;
                paramsMap= Util.convertStringToMap(params);
                paramsMap.put("authtoken", "857343e0aabd957cf77a4471f579b0a6");
                String reqUrl = constructURL(url, paramsMap);
                String[] time = thread(reqUrl, method).split("\\+");
                Map<String, String> paramMap = new HashMap<>();
                paramMap.put("ZOHO_ACTION", "ADDROW");
                paramMap.put("ZOHO_OUTPUT_FORMAT", "JSON");
                paramMap.put("ZOHO_ERROR_FORMAT", "JSON");
                paramMap.put("ZOHO_API_VERSION", "1.0");
                paramMap.put("Date", currentDate);
               // paramMap.put("Build Label", buildLabel);
                System.out.print(time[0]+time[1]+time[2]);
                paramMap.put("Average", time[2]);
                paramMap.put("Min", time[0]);
                paramMap.put("Max", time[1]);
                paramMap.put("Status", "");
                paramMap.put("Label", "");
                testCasesObject.setTestCaseId(testCase.getTestCaseId());
                testCasesObject.setMinResTime(Integer.parseInt(time[0]));
                testCasesObject.setMaxResTime(Integer.parseInt(time[1]));
                //need to remove this
                //runCount=runCount==0?1:runCount;
                runCount=runCount==5?1:runCount;
                testCasesObject=setAvgResponseTime(testCasesObject,runCount,time[2]);
                testCasesObject.setRunCount(runCount);
                session.update(testCasesObject);
                session.getTransaction().commit();
                pushData(paramMap,token);
            }
            session.close();
        }
		catch(Exception e)
		{
           e.printStackTrace();
		}
		}

    public static void sendGetRequest(String reqUrl,String method)
    {
        try{
            URL url = new URL(reqUrl);
            HttpsURLConnection connect = (HttpsURLConnection) url.openConnection();
            connect.setRequestMethod(method);
            long startTime = System.nanoTime();
            int statusCode=connect.getResponseCode();
            long end=System.nanoTime();
            if(statusCode == sc_ok)
            {
                long elapsedTime = (end - startTime)- 100000000;
                timeList.add((int) TimeUnit.NANOSECONDS.toMillis(elapsedTime));
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static String thread(String url,String method) throws InterruptedException
    {
        int numberOfThreads=2;
        Thread[] t = new Thread[numberOfThreads+1];
        String total = null;
        for(int i=0;i<numberOfThreads;i++)
        {
            t[i]=new Thread(()->sendGetRequest(url,method));
        }
        for(int i=0; i<numberOfThreads; i++)
        {
            t[i].start();
        }
        for(int i=0; i<numberOfThreads; i++)
        {
            t[i].join();
        }
        if(!timeList.isEmpty())
        {
            Collections.sort(timeList);
            int min=timeList.get(0);
            int max=timeList.get(timeList.size()-1);
            int avg=timeList.stream().mapToInt(Integer::intValue).sum()/(timeList.size());
            total=min +"+"+ max +"+"+avg;
        }
        return total;
    }

    public static String constructURL(String requestURL, Map<String, String> paramMap) {
        Iterator<Map.Entry<String, String>> itr = paramMap.entrySet().iterator();
        requestURL=requestURL.contains("?")?requestURL:requestURL+"?";
        while (itr.hasNext()) {
            Map.Entry<String, String> entry = itr.next();
            if(!entry.getKey().contains("build_label"))
            {
                requestURL = requestURL + entry.getKey() + "=" + entry.getValue();
                if (itr.hasNext()) {
                    requestURL = requestURL + "&";
                }
            }
        }
        requestURL = requestURL.replaceAll("\\s", "+");     // No I18N
        return requestURL;
    }
    public static String getToken(Map<String, String> ymldata) throws Exception
    {
        String accessToken = "";
        try {
            String requestURL = "https://accounts.zoho.com/oauth/v2/token?";
            Map<String, String> paramMap = new HashMap<>();
            paramMap.put("client_id", ymldata.get("client_id"));
            paramMap.put("client_secret", ymldata.get("client_secret"));
            paramMap.put("redirect_uri", ymldata.get("redirect_uri"));
            paramMap.put("grant_type", "refresh_token");
            paramMap.put("refresh_token", ymldata.get("refresh_token"));
            requestURL = constructURL(requestURL, paramMap);
            URL url = new URL(requestURL);
            HttpsURLConnection connect;
            connect = (HttpsURLConnection) url.openConnection();
            connect.setRequestMethod("POST");
            BufferedReader reader = new BufferedReader(new InputStreamReader(connect.getInputStream()));
            JSONObject json = new JSONObject(reader.readLine());
            accessToken = (String) json.get("access_token");
        } catch (Exception e) {
            throw new Exception(e);
        }
        return accessToken;
    }

    public static String getBuildLabel(Map<String, String> ymldata) throws IOException, JSONException
    {
        String requestURL = "https://expense.localzoho.com/api/v1/userpreferences/editpage?";
        Map<String, String> paramMap = new HashMap<>();
        paramMap.put("organization_id", "66564612");
        paramMap.put("authtoken", ymldata.get("authtoken"));
        requestURL = constructURL(requestURL, paramMap);
        URL url = new URL(requestURL);
        HttpsURLConnection connect = (HttpsURLConnection) url.openConnection();
        connect.setRequestMethod("GET");
        return connect.getHeaderField("SERVER_BUILD_VERSION");
    }

    public static void pushData(Map<String, String> paramMap,String accessToken) throws Exception {
        String requestURL = "https://analyticsapi.zoho.com/api/nitharsana.r@zohocorp.com/ZF Expense/ZE - Performance testing?"; // No I18N
        requestURL = constructURL(requestURL, paramMap);
        //     System.out.println(requestURL);
        URL url = new URL(requestURL);
        HttpsURLConnection connect = (HttpsURLConnection) url.openConnection();
        connect.setRequestMethod("POST");
        connect.setRequestProperty("Authorization", "Zoho-oauthtoken " + accessToken + "");
        int statusCode = connect.getResponseCode();
        if (statusCode != sc_ok) {
            BufferedReader br = new BufferedReader(new InputStreamReader(connect.getErrorStream()));
            throw new Exception(br.readLine());
        }
        System.out.print("data pushed");
    }

    public static TestCasesObject setAvgResponseTime(TestCasesObject testCase, int runCount,String avgTime)
    {
        int avgTimeInNum=Integer.parseInt(avgTime);
        switch (runCount)
        {
            case 1:
                testCase.setResultTime1(avgTimeInNum);
                break;
            case 2:
                testCase.setResultTime2(avgTimeInNum);
                break;
            case 3:
                testCase.setResultTime3(avgTimeInNum);
                break;
            case 4:
                testCase.setResultTime4(avgTimeInNum);
                break;
            case 5:
                testCase.setResultTime5(avgTimeInNum);
                break;
        }
        return testCase;
    }

    public static void startTestCaseRunner(String testId)
    {
        try {
            Thread t = new Thread(() -> {
                try {
                     runTestCase(testId);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            });
            t.start();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }

//    public static void main(String agrs[]) throws IOException {
//        runTestCase("12");
//    }
}
