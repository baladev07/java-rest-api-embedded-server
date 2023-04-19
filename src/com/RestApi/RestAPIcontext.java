package com.RestApi;

import com.Util.Util;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;
import org.json.JSONObject;
import org.yaml.snakeyaml.Yaml;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileReader;
import java.io.InputStream;
import java.lang.reflect.Field;
import java.lang.reflect.Modifier;
import java.util.*;

public class RestAPIcontext
{
    private static  HttpServletRequest req;
    private HttpServletResponse res;


    public RestAPIcontext(HttpServletRequest req, HttpServletResponse res)
    {
        this.req=req;
        this.res=res;
    }
    public static String getID() {
        String[] str=getURI().split("/");
        for(String string:str)
        {
            if(isDigit(string))
            {
                return string;
            }
        }
        return "";
    }
    public HttpServletResponse getResponse()
    {
        return res;
    }
    public HttpServletRequest getRequest()
    {
        return req;
    }
    public static String getURI()
    {
//        String uri=req.getRequestURI();
//        String[] str=uri.split("/");
//        StringBuilder sb = new StringBuilder();
//        for(String string:str)
//        {
//            if(!string.equals("pro") & !string.equals("api"))
//            {
//                sb.append(string+"/");
//                if(isDigit(string))
//                {
//                    return sb.toString();
//                }
//            }
//        }
        return req.getRequestURI();
    }

    public static boolean isDigit(String name)
    {
        char[] chars = name.toCharArray();
        int i=0;
        for (char c : chars) {
            if (Character.isDigit(c)) {
                i++;
            }
            if(i==chars.length)
            {
                return true;
            }
        }
        return false;
    }
    public static String getMethod()
    {
        return req.getMethod();
    }
    public String getParameter(String param)
    {
       return req.getParameter(param);
    }
    public static RequestMapper.Request getRequestMapper() throws JAXBException
    {
            File file=new File(System.getProperty("user.dir")+"/conf/APIdoc.xml");
            JAXBContext jaxbContext=JAXBContext.newInstance(RequestMapper.class);
            Unmarshaller unmarshall= jaxbContext.createUnmarshaller();
            RequestMapper request= (RequestMapper) unmarshall.unmarshal(file);
            RequestMapper.Request request1=null;
            List<RequestMapper.Request> r=request.getRequest();
            for(RequestMapper.Request Req:r)
            {
                if(!(getURI().equals(Req.getUri(getID()))) || !(Req.getMethod().equals(getMethod())))
                {
                      continue;
                }
                request1=Req;
            }
            return request1;
    }

    public String getSqlQuery() throws JAXBException {
        String query="";
        RequestMapper.Request request = getRequestMapper();
        String CVName= request.getQName();
        try{
//            System.out.print(Util.homeDir);
            InputStream inputStream = new FileInputStream(System.getProperty("user.dir")+"/conf/queries.yml");
            Yaml yaml=new Yaml();
            Map<String,String> queryMap= (Map<String, String>) yaml.load(inputStream);
            query=queryMap.get(CVName);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return query;
    }
    public String getMsg(String key)
    {
        String msg="";
        try
        {
            FileReader filereader=new FileReader("/Users/user/IdeaProjects/project/conf/app.properties");
            Properties p= new Properties();
            p.load(filereader);
            msg=p.getProperty(key);
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return msg;
    }

    public  Object getClassObj()
    {
        Object obj1=null;
        try{
            RequestMapper.Request request =getRequestMapper();
            String entityName= request.getEntityName();
            ObjectMapper mapper =new ObjectMapper();
            Class class0=Class.forName(entityName);
            if(isRequestContainsParam("JSONString"))
            {
                String json = getParameter("JSONString");
                JSONObject josn=new JSONObject(json);
                obj1= mapper.readValue(josn.toString(), class0);
            }
            if(!getMethod().contains("POST"))
            {
                int id=Integer.parseInt(getID());
                Object obj2=getSession().get(class0,id);
                if(obj1!=null)
                {
                    Field[] allFields=obj1.getClass().getDeclaredFields();
                    for(Field field:allFields)
                    {
                        if(Modifier.isProtected(field.getModifiers()) & field.get(obj1) !=null & ifContainId(field.getName(), field.get(obj1)))
                        {
                            field.set(obj2,field.get(obj1));
                        }
                    }
                }
                obj1=obj2;
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return obj1;
    }

    public static Class getClassName() throws ClassNotFoundException, JAXBException {
        String entityName=getRequestMapper().getEntityName();
        return Class.forName(entityName);
    }

    public static Session getSession()
    {
        Session session = null;
        try{
            Configuration con=new Configuration().configure().addAnnotatedClass(getClassName());
            SessionFactory sf= con.buildSessionFactory();
            session= sf.openSession();
            session.beginTransaction();
            System.out.println("Done");
        }
        catch (Exception e)
        {
            e.printStackTrace();
        }
        return session;
    }
    public boolean ifContainId(String field, Object value)
    {
        if(field.contains("Id") || field.equals("createdTime"))
        {
            int val=Integer.parseInt(String.valueOf(value));
            if(val==0)
            {
                return false;
            }
        }
        return true;
    }

    public boolean isRequestContainsParam(String param)
    {
        Enumeration e=req.getParameterNames();
        while(e.hasMoreElements())
        {
          if(e.nextElement().equals(param));
           {
               return true;
           }
        }
        return false;

    }
   /* public static void main(String args[])
    {
        Session session=null;
        try {
//            File file = new File("/Users/user/IdeaProjects/project/src/resources/hibernate.cfg.xml");
//            Configuration con=new Configuration().configure(file).addAnnotatedClass(customer.class);
//            SessionFactory sf= con.buildSessionFactory();
//            session= sf.openSession();
//            session.beginTransaction();
//            customer c=new customer();
//            c.setCustomer_name("l");
//            String test="";
//            Map<Integer,Integer>map=new HashMap<>();
//
//            c.setCustomer_project("ee");
//            session.save(c);
//            method1(session);
//            session.getTransaction().commit();
//            session.close();
            String s="race a car";
            s=s.replaceAll("[^a-zA-Z0-9]", "").toLowerCase();
            StringBuilder sb=new StringBuilder();
            sb.
            System.out.print(s);
            int l=0,r=s.length()-1;
            while(l<=r)
            {
                if(s.charAt(l)!=s.charAt(r))
                {
                    System.out.print("hii");
                }
                l++;
                r--;
            }
//            System.out.print(Integer.parseInt(v));
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }

    public static void method1(Session session)
    {
        customer c=new customer();
        c.setCustomer_name("bel");
        c.setCustomer_project("expens");
        session.merge(c);
        method2(session);
    }

    public static void method2(Session session)
    {
        customer c=new customer();
        c.setCustomer_name("be");
        c.setCustomer_project("expen");
        session.merge(c);
    }
*/
}

