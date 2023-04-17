package com.Util;

import com.RestApi.RestAPIcontext;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.JSONException;
import org.json.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLIntegrityConstraintViolationException;
import java.util.Date;
import java.util.Map;

public class Util {

   // private Throwable cause;

    public static final String homeDir = System.getProperty("user.home")+"/home/project";

    public static long getCurrentTimeInMillis()
    {
        Date date=new Date();
        return date.getTime();
    }
    public static void handleResponse(RestAPIcontext context, JSONObject json) {
        try {
            HttpServletResponse res = context.getResponse();
            PrintWriter printwriter = res.getWriter();
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            JSONObject finalJson=constructFinalJson();
            if(json!=null)
            {
                finalJson.put("Result",json);
            }
            printwriter.write(finalJson.toString());
            printwriter.close();
        } catch (Exception e) {
            handleErrorResponse(context,null);
            e.printStackTrace();
        }
    }

    public static  JSONObject constructFinalJson() throws JSONException {
        return new JSONObject().put("message","success");
    }

    public static void handleErrorResponse(RestAPIcontext context,String msg) {
        try{
            HttpServletResponse res= context.getResponse();
            PrintWriter printwriter = res.getWriter();
            res.setContentType("application/json");
            res.setCharacterEncoding("UTF-8");
            res.setStatus(400);
            JSONObject json=new JSONObject();
            String defaultMsg="Internal Error";
            if(msg!=null)
            {
                defaultMsg=msg;
            }
            json.put("Message",defaultMsg);
            printwriter.write(json.toString());
            printwriter.close();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
    public static boolean isSqlDuplicatedKey(Exception e) {
        return e.getCause().getCause() != null && SQLIntegrityConstraintViolationException.class.isInstance(e.getCause().getCause());
    }

    public static void handleDuplicateField(Exception e, RestAPIcontext context) throws IOException, JSONException {
        String error=e.getCause().getCause().getLocalizedMessage();
        String[] fieldStr=null;
        String[] str=error.split(" ");
        for(String s:str)
        {
            if(s.contains("UNIQUE"))
            {

                fieldStr=s.split("_");
            }
        }
        handleErrorResponse(context,fieldStr[0].replace("'","")+" already exits");
    }

   public static Map<String,String> convertStringToMap(String val) throws IOException {
       ObjectMapper objMapper=new ObjectMapper();
       Map<String,String>valMap=objMapper.readValue(val,Map.class);
       return valMap ;
    }

    public static void main(String args[])
    {

        try{
            convertStringToMap("hii");
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }

    }
}
