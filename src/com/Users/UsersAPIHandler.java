package com.Users;

import com.Exception.GeneralException;
import com.Model.UserObject;
import com.RestApi.APIHandler;
import com.RestApi.RestAPIcontext;
import com.Util.Util;
import org.hibernate.Session;
import org.json.JSONObject;


public class UsersAPIHandler extends APIHandler {

    @Override
    public Object handlePreprocess(RestAPIcontext context)
    {
        Object obj=null;
        try{
            String uri=context.getURI();
            JSONObject userJson = null;
            if(uri.contains("/user") & !uri.contains("/user/") )
            {
                userJson= UserAPI.getUserJsonList(context);
            }
            else{
                userJson=UserAPI.getUserJson((UserObject) context.getClassObj());
            }
            Util.handleResponse(context,userJson);
            return "success";
        }
        catch (Exception e)
        {
            Util.handleErrorResponse(context,null);
            e.printStackTrace();
        }
        return null;
    }


    @Override
    public void processDo(Object obj,RestAPIcontext context) throws Exception {
        try{
            UserObject userObject= (UserObject) obj;
            if(context.getMethod().contains("POST"))
            {
                userObject.setCreatedTime(Util.getCurrentTimeInMillis());
            }
            userObject.setLastUpdatedTime(Util.getCurrentTimeInMillis());
            Session session= context.getSession();
            session.saveOrUpdate(userObject);
            session.getTransaction().commit();
            session.close();
            Util.handleResponse(context,null);
        } catch(Exception e)
        {
           if(Util.isSqlDuplicatedKey(e))
           {
               throw new GeneralException(context,e);
           }
           e.printStackTrace();
        }
    }
    @Override
    public Object postProcess(RestAPIcontext context) {
        Object obj=null;
        try{
            UserObject user = new UserObject();
            user.setUserId(Integer.parseInt(context.getID()));
            Session session= context.getSession();
            session.delete(user);
            session.getTransaction().commit();
            session.close();
            Util.handleResponse(context,null);
            return "success";
        }
        catch(Exception e)
        {
            Util.handleErrorResponse(context,null);
            e.printStackTrace();
        }
        return null;
    }
}
