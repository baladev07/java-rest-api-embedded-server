package com.Task;

import com.RestApi.RestAPIcontext;
import com.Model.TaskObject;
import com.Util.SqlUtil;
import com.Util.Util;
import org.json.JSONArray;
import org.json.JSONObject;

import java.sql.ResultSet;

public class TaskAPI {
    public static JSONObject getTaskJson(TaskObject task)
    {
        JSONObject json2=new JSONObject();
        try{
            JSONObject json1=new JSONObject();
            json1.put("taskId",task.getTaskId());
            json1.put("taskTitle",task.getTaskTitle());
            json1.put("taskDescription",task.getTaskDescription());
            json1.put("createdTime",task.getCreatedTime());
            json1.put("lastUpdatedTime",task.getLastUpdatedTime());
            json1.put("createdBy",task.getCreatedBy());
            json1.put("AssignedTo",task.getAssignedTo());
            json1.put("Status",task.getStatus());
            json1.put("DueDate",task.getDueDate());
            json2.put("task",json1);
            //jsonStr=json2.toString();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return json2;
    }

    public static JSONObject getTaskJsonList(RestAPIcontext context)
    {
        JSONObject json= new JSONObject();
        try{
            ResultSet resultSet= SqlUtil.getList(context);
            JSONArray jsonArray=new JSONArray();
            while(resultSet.next())
            {
                TaskObject task=new TaskObject();
                task.setTaskId(resultSet.getInt(1));
                task.setTaskTitle(resultSet.getString(2));
                task.setTaskDescription(resultSet.getString(3));
                task.setCreatedBy(resultSet.getInt(6));
                task.setCreatedTime(resultSet.getLong(4));
                task.setLastUpdatedTime(resultSet.getLong(5));
                task.setStatus(resultSet.getInt(8));
                task.setDueDate(resultSet.getInt(9));
                task.setAssignedTo(resultSet.getInt(7));
                jsonArray.put(getTaskJson(task).get("task"));
            }
            json.put("tasks",jsonArray);
        }
        catch(Exception e)
        {
            Util.handleErrorResponse(context,null);
            e.printStackTrace();
        }
        return json;
    }

}
