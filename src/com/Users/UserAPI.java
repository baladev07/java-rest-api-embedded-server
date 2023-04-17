package com.Users;

import com.RestApi.RestAPIcontext;
import com.Model.UserObject;
import com.Util.SqlUtil;
import com.Util.Util;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;

public class UserAPI {


    public static  JSONObject getUserJson(UserObject user)
    {
        JSONObject json2=new JSONObject();
        try{
            JSONObject json1=new JSONObject();
            json1.put("user_name",user.getUserName());
            json1.put("user_email",user.getUserEmail());
            json1.put("user_id",user.getUserId());
            json1.put("created_time",user.getCreatedTime());
            json1.put("last_modified_time",user.getLastUpdatedTime());
            json1.put("user_role",user.getRole());
            json2.put("user",json1);
            //jsonStr=json2.toString();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return json2;
    }

    public static JSONObject getUserJsonList(RestAPIcontext context)
    {
        JSONObject json= new JSONObject();
        try{
            ResultSet resultSet= SqlUtil.getList(context);
            JSONArray jsonArray=new JSONArray();
            while(resultSet.next())
            {
                UserObject user =new UserObject();
                user.setUserName(resultSet.getString("username"));
                user.setUserEmail(resultSet.getString("useremail"));
                user.setUserId(resultSet.getInt("userid"));
                user.setCreatedTime(resultSet.getLong("createdtime"));
                user.setLastUpdatedTime(resultSet.getLong("lastupdatedtime"));
                user.setRole(resultSet.getInt("role"));
                jsonArray.put(getUserJson(user).get("user"));
            }
            json.put("users",jsonArray);
        }
        catch(Exception e)
        {
            Util.handleErrorResponse(context,null);
            e.printStackTrace();
        }
        return json;
    }


}
