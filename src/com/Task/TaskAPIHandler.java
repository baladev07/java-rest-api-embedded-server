package com.Task;

import com.Exception.GeneralException;
import com.Model.TaskObject;
import com.RestApi.APIHandler;
import com.RestApi.RestAPIcontext;
import com.Util.Util;
import org.hibernate.Session;
import org.json.JSONObject;

public class TaskAPIHandler extends APIHandler {


    @Override
    public Object handlePreprocess(RestAPIcontext context) {
        Object obj=null;
        try{
            String uri=context.getURI();
            JSONObject taskJson = null;
            if(uri.contains("/task") & !uri.contains("/task/") )
            {
                taskJson= TaskAPI.getTaskJsonList(context);
            }
            else{
                taskJson=TaskAPI.getTaskJson((TaskObject) context.getClassObj());
            }
            Util.handleResponse(context,taskJson);
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
            TaskObject task= (TaskObject) obj;
            if(context.getMethod().contains("POST"))
            {
                task.setCreatedTime(Util.getCurrentTimeInMillis());
            }
            task.setLastUpdatedTime(Util.getCurrentTimeInMillis());
            Session session= context.getSession();
            session.saveOrUpdate(task);
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
            TaskObject task=new TaskObject();
            task.setTaskId(Integer.parseInt(context.getID()));
            Session session= context.getSession();
            session.delete(task);
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
