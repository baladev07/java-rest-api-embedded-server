package com.Util;

//import org.json.JSONArray;
//import org.json.JSONException;
//import org.json.JSONObject;
import org.yaml.snakeyaml.Yaml;

import javax.net.ssl.HttpsURLConnection;
import java.io.*;
import java.net.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

public class JmeterHandler {
//    public static String homeDir = System.getProperty("user.home") + "/IdeaProjects/project/apache-jmeter/bin";
//    public static int sc_ok = 200;
//
//    public JmeterHandler() {
//
//    }
//
//    public static Boolean runJmeter() {
//        ProcessBuilder processbuilder = new ProcessBuilder("sh", "run.sh");
//        processbuilder.directory(new File(homeDir));
//        try {
//            Process process = processbuilder.start();
//            process.waitFor();
//            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
//            String line = "";
//            while ((line = reader.readLine()) != null) {
//                System.out.println(line);
//            }
//            TimeUnit.SECONDS.sleep(150);
//            pushToAnalytics();
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return true;
//    }
//
//    public static Boolean pushToAnalytics() {
//        try {
//            InputStream inputStream = new FileInputStream("/Users/user/IdeaProjects/project/conf/properties.yml");
//            Yaml yaml = new Yaml();
//            Map<String, String> ymldata = (Map<String, String>) yaml.load(inputStream);
//            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
//            String line = "";
//            String spChar = ",";
//            LocalDateTime now = LocalDateTime.now();
//            String currentDate = dtf.format(now);
//            String filePath = homeDir + "/examples/jmeter_aggregate_csv/" + currentDate + ".csv";
//            BufferedReader br = new BufferedReader(new FileReader(filePath));
//            String buildLabel = getBuildLabel(ymldata);
//            Map<String,String>logMap=getLogResponseTime();
//           while ((line = br.readLine()) != null) {
//            for(String key:logMap.keySet()) {
//                String[] data = line.split(spChar);
//                if (key.equals(data[0])) {
//                    Map<String, String> param = new HashMap<>();
//                    param.put("ZOHO_ACTION", "ADDROW");
//                    param.put("ZOHO_OUTPUT_FORMAT", "JSON");
//                    param.put("ZOHO_ERROR_FORMAT", "JSON");
//                    param.put("ZOHO_API_VERSION", "1.0");
//                    param.put("API Label", data[0]);
//                    param.put("Min", logMap.get(data[0]).split("\\+")[0]);
//                    param.put("Max", logMap.get(data[0]).split("\\+")[1]);
//                    param.put("Error%", data[9]);
//                    param.put("Samples", data[1]);
//                    param.put("Build Label", buildLabel);
//                    param.put("Average", logMap.get(data[0]).split("\\+")[2]);
//                    pushData(param, ymldata);
//                }
//            }
//            }
//
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return true;
//    }
//
//    public static String getBuildLabel(Map<String, String> ymldata) throws IOException, JSONException {
//        String requestURL = "https://expense.localzoho.com/api/v1/userpreferences/editpage?";
//        Map<String, String> paramMap = new HashMap<>();
//        paramMap.put("organization_id", "66564612");
//        paramMap.put("authtoken", ymldata.get("authtoken"));
//        requestURL = constructURL(requestURL, paramMap);
//        URL url = new URL(requestURL);
//        HttpsURLConnection connect = (HttpsURLConnection) url.openConnection();
//        connect.setRequestMethod("GET");
//        return connect.getHeaderField("SERVER_BUILD_VERSION");
//    }
//
//    public static void pushData(Map<String, String> paramMap, Map<String, String> ymldata) throws Exception {
//        String requestURL = "https://analyticsapi.zoho.com/api/nitharsana.r@zohocorp.com/ZF Expense/Sample Table?";
//        String accessToken = getToken(ymldata);
//        requestURL = constructURL(requestURL, paramMap);
//   //     System.out.println(requestURL);
//        URL url = new URL(requestURL);
//        HttpsURLConnection connect = (HttpsURLConnection) url.openConnection();
//        connect.setRequestMethod("POST");
//        connect.setRequestProperty("Authorization", "Zoho-oauthtoken " + accessToken + "");
//        int statusCode = connect.getResponseCode();
//        if (statusCode != sc_ok) {
//            BufferedReader br = new BufferedReader(new InputStreamReader(connect.getErrorStream()));
//            throw new Exception(br.readLine());
//        }
//    }
//
//    public static String getToken(Map<String, String> ymldata) {
//        String accessToken = "";
//        try {
//            String requestURL = "https://accounts.zoho.com/oauth/v2/token?";
//            Map<String, String> paramMap = new HashMap<>();
//            paramMap.put("client_id", ymldata.get("client_id"));
//            paramMap.put("client_secret", ymldata.get("client_secret"));
//            paramMap.put("redirect_uri", ymldata.get("redirect_uri"));
//            paramMap.put("grant_type", "refresh_token");
//            paramMap.put("refresh_token", ymldata.get("refresh_token"));
//            requestURL = constructURL(requestURL, paramMap);
//            URL url = new URL(requestURL);
//            HttpsURLConnection connect;
//            connect = (HttpsURLConnection) url.openConnection();
//            connect.setRequestMethod("POST");
//            BufferedReader reader = new BufferedReader(new InputStreamReader(connect.getInputStream()));
//            JSONObject json = new JSONObject(reader.readLine());
//            accessToken = (String) json.get("access_token");
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//        return accessToken;
//    }
//
//    public static String constructURL(String requestURL, Map<String, String> paramMap) {
//        Iterator<Map.Entry<String, String>> itr = paramMap.entrySet().iterator();
//        while (itr.hasNext()) {
//            Map.Entry<String, String> entry = itr.next();
//            requestURL = requestURL + entry.getKey() + "=" + entry.getValue();
//            if (itr.hasNext()) {
//                requestURL = requestURL + "&";
//            }
//        }
//        requestURL = requestURL.replaceAll("\\s", "+");
//        return requestURL;
//    }
//    public static void main(String[] args)
//    {
//       runJmeter();
//    }
//
//    public static Map<String, String> getLogResponseTime() {
//        Map<String,String>logsMap=null;
//        String filePath = homeDir + "/logs.csv";
//        try {
//            BufferedReader br = new BufferedReader(new FileReader(filePath));
//            int count = (int) Files.lines(Paths.get(filePath)).count();
//            String line = "";
//            int index = 0;
//            String startTime = "";
//            String endTime = "";
//            int urlIndex = 0;
//            Map<String, String> map = new HashMap<>();
//            while ((line = br.readLine()) != null) {
//                String[] data = line.split(",");
//                if (index < count - 1) {
//                    if (index == 0) {
//                        List<Object> list = IntStream.range(0, data.length).filter(i -> data[i].contains("URL")).mapToObj(i -> i).collect(Collectors.toList());
//                        urlIndex = (int) list.get(0);
//                    }
//                    if (index == 1) {
//                        startTime = data[0];
//                    }
//                    if (index != 0 & data[urlIndex].contains("https://")) {
//                        map.put(data[2], data[urlIndex]);
//                    }
//                    endTime = data[0];
//                    index++;
//                }
//            }
//            if (!startTime.isEmpty() & !endTime.isEmpty()) //isDeleted)
//            {
//                logsMap=method(map, startTime, endTime);
//            }
//        } catch (Exception e) {
//            e.printStackTrace();
//        }
//     //   System.out.println(logsMap);
////        Boolean isDeleted=new File(filePath).delete();
//        return logsMap;
//    }
//
//    public static String getFormattedDate(String timeInMilli)
//    {
//        long milliSec = Long.parseLong(timeInMilli);
//        Date date = new Date(milliSec);
//        DateFormat formatter = new SimpleDateFormat("dd/MM/yyyy kk:mm");
//        return formatter.format(date).replaceAll("\\s","+");
//    }
//
//    public static String constructQuery(String url)
//    {
//         url= URLDecoder.decode(url);
//         Map<String,String>paramMap=new HashMap<>();
//         String[] params=url.split("\\?")[1].split("&");
//         for(String param:params)
//         {
//             String name=param.split("=")[0];
//             String value=param.split("=")[1];
//             paramMap.put(name,value);
//         }
//         StringBuilder sb=new StringBuilder();
//         sb.append("logtype=\"access\" ");
//         Iterator<Map.Entry<String,String>> itr=paramMap.entrySet().iterator();
//         while(itr.hasNext())
//         {
//            Map.Entry<String, String> entry = itr.next();
//            if(!entry.getKey().contains("authtoken"))
//            {
//                sb.append("and (params.param_name contains \""+entry.getKey()+"\" and ");
//                sb.append("params.param_value contains \""+entry.getValue()+"\") ");
//            }
//         }
//       return (sb.toString().replace("\"","%22").replaceAll("\\s","+"));
//
//    }
//    public static Map<String,String> method(Map<String,String> urlMap,String startTime,String endTime) throws Exception {
//        Map<String,String>timeMap=new HashMap<>();
//        for(String key:urlMap.keySet())
//        {
//            Map<String,String>map=new HashMap<>();
//            map.put("authtoken","8e2ecf07dacb79158c117d116a47ac13");
//            map.put("appid","10772528");
//            map.put("service","Expense");
//            map.put("fromDateTime",getFormattedDate(startTime));
//            map.put("toDateTime",getFormattedDate(endTime));
//            map.put("range","1-100");
//            map.put("query",constructQuery(urlMap.get(key)));
//            String url=constructURL("https://logs.localzoho.com/search?",map);
//      //      System.out.println(url);
//            URL url1 = new URL(url);
//            HttpsURLConnection connect = (HttpsURLConnection) url1.openConnection();
//            connect.setRequestMethod("GET");
//            int statusCode=connect.getResponseCode();
//            if(statusCode!=sc_ok)
//            {
//                BufferedReader br=new BufferedReader(new InputStreamReader(connect.getErrorStream()));
//                throw new Exception(br.readLine());
//            }
//            BufferedReader bufferreader=new BufferedReader(new InputStreamReader(connect.getInputStream()));
//            JSONArray array=new JSONObject(bufferreader.readLine()).getJSONArray("docs");
//            List<Integer>timeList=new ArrayList<>();
//            for(int i=0;i<array.length();i++)
//            {
//                JSONObject obj= (JSONObject) array.get(i);
//                String num=obj.get("_zlf_time_taken").toString().replace("ms","").replace("s","");
//                if(num.contains("."))
//                {
//                    int number=Integer.parseInt(num.replace(".",""));
//                    timeList.add(number*100);
//                    continue;
//                }
//                timeList.add(Integer.parseInt(num));
//            }
//            if(!timeList.isEmpty())
//            {
//                Collections.sort(timeList);
//                int min=timeList.get(0);
//                int max=timeList.get(timeList.size()-1);
//                int avg=timeList.stream().mapToInt(Integer::intValue).sum()/(timeList.size());
//                String total=String.valueOf(min)+"+"+String.valueOf(max)+"+"+String.valueOf(avg);
//                timeMap.put(key,total);
//            }
//        }
//        return timeMap;
//    }
//
}
