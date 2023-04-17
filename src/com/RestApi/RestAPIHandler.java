package com.RestApi;


import com.Util.JavaMysql;
import com.google.gson.Gson;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class RestAPIHandler {

    public static Object handlePreprocess(RestAPIcontext context) throws IOException {
        JavaMysql data=new JavaMysql();
        HttpServletResponse res = context.getResponse();
        PrintWriter printwriter = res.getWriter();
        String requestUrl = context.getURI();
        String method = context.getMethod();
        res.setContentType("application/json");
        res.setCharacterEncoding("UTF-8");
        Gson gson =new Gson();
        if(requestUrl.contains("/customer") & method.contains("GET"))
        {
            try {
                List<Map<String,String>> details=data.getCustomer();
                String customerJson=gson.toJson(details);
                printwriter.write(customerJson);
                printwriter.close();

            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        if(requestUrl.contains("/customer") & method.contains("POST"))
        {
            try{
                String json=context.getParameter("JSONString");
                JSONObject j=new JSONObject(json);
                Map<String,String> customerMap=new HashMap<>();
                customerMap.put("customer_name", (String) j.get("customer_name"));
                customerMap.put("customer_product", (String) j.get("customer_product"));
                data.insertCustomer(customerMap);
                Map<String,String> map=new HashMap<>();
                map.put("message","Customer added successfully");
                res.setStatus(201);
                String message=gson.toJson(map);
                printwriter.write(message);
                printwriter.close();
            }
            catch (Exception e) {

            }
        }
        if(requestUrl.contains("/customer/") & method.contains("PUT"))
        {
            try{
                String json=context.getParameter("JSONString");
                JSONObject j=new JSONObject(json);
                Map<String,String> customerMap=new HashMap<>();
                customerMap.put("customer_name", (String) j.get("customer_name"));
                customerMap.put("customer_product", (String) j.get("customer_product"));
                customerMap.put("customer_id",context.getID());
                if(data.updateCustomer(customerMap))
                {
                    Map<String,String> map=new HashMap<>();
                    map.put("message","Customer updated successfully");
                    res.setStatus(201);
                    String message=gson.toJson(map);
                    printwriter.write(message);
                    printwriter.close();
                }
            }
            catch (Exception e)
            {

            }
        }

        if(requestUrl.contains("/customer/") & method.contains("DELETE"))
        {
            try{
                String customerID=context.getID();
                if(data.deleteCustomer(customerID))
                {
                    Map<String,String> map=new HashMap<>();
                    map.put("message","Customer deleted successfully");
                    String message=gson.toJson(map);
                    printwriter.write(message);
                    printwriter.close();
                }
            } catch (SQLException throwables) {
                throwables.printStackTrace();
            }
        }
        return "sucess";

    }
    public static void handleResponse(RestAPIcontext context, Map<String, String> map, List<Map<String, String>> mapList) {
        try{
            HttpServletResponse res = context.getResponse();
            PrintWriter printwriter = res.getWriter();
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            Gson gson =new Gson();
            String message=gson.toJson((map != null)?map:mapList);
            printwriter.write(message);
            printwriter.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
    public static void createOrUpdateCustomer(RestAPIcontext context)
    {
//        try{
//            String json=context.getParameter("JSONString");
//            JSONObject j=new JSONObject(json);
//            Map<String,String> customerMap=new HashMap<>();
//            customerMap.put("customer_name", (String) j.get("customer_name"));
//            customerMap.put("customer_product", (String) j.get("customer_product"));
//            RequestMapper.Request request= context.getRequestMapper();
//            String CVName= request.getCVName();
//            String sqlQuery= context.getSqlQuery(CVName);
//            SqlUtil sql=new SqlUtil(sqlQuery);
//            sql.update(customerMap);
//            Map<String,String>map=new HashMap<>();
//            map.put("message", context.getMsg("eq.customer.added"));
//            handleResponse(context,map,null);
//        }
//        catch(Exception e)
//        {
//            e.printStackTrace();
//        }
    }
}
