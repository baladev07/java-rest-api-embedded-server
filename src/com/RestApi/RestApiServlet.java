package com.RestApi;


import com.Exception.GeneralException;

import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


public class RestApiServlet extends HttpServlet {
    public void service(HttpServletRequest req, HttpServletResponse res) {
        RestAPIcontext context=new RestAPIcontext(req,res);
        try{
            Object responseObj=null;
            RequestMapper.Request request= context.getRequestMapper();
            if(request == null)
            {
                throw new GeneralException(context);
            }
            String classname= request.getClassname();
            //String uri=request.getUri();
            String entityName=request.getEntityName();
            //RestAPIHandler.handlePreprocess(context);
            Class classObj= Class.forName(classname);
            if(request.getUri(null).contains("/jmeter/start"))
            {
//               JmeterHandler.runJmeter();
            }
            APIHandler obj= (APIHandler) classObj.newInstance();
            String method = req.getMethod();
            if(method.contains("GET"))
            {
                responseObj=obj.handlePreprocess(context);
            }
            if(method.contains("POST") || method.contains("PUT"))
            {
                Object classObj1=context.getClassObj();
                obj.processDo(classObj1,context);
            }
            if(method.contains("DELETE"))
            {
                obj.postProcess(context);
            }
//            Method method=classobj.getMethod(request.getMethod(),null);
//            method.invoke(null,null);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

}

