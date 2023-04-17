package com.Util;

import com.RestApi.RequestMapper;
import com.RestApi.RestAPIcontext;

import java.io.InputStreamReader;
import java.sql.*;
import java.util.Iterator;
import java.util.Map;

public class SqlUtil {
    private String sql;
    private Map map;
    private static Connection connect;
    private PreparedStatement statement;
    public SqlUtil() throws SQLException {
//         this.connect=JavaMysql.getConnection();
//         this.sql=sql;
         this.statement=connect.prepareStatement(sql);
    }


    protected void update(Map<String,String>map) throws SQLException {
        Iterator<Map.Entry<String,String>>itr=map.entrySet().iterator();
        int index=1;
        while(itr.hasNext())
        {
            Map.Entry<String,String> entry=itr.next();
            statement.setString(index, entry.getValue());
            index++;
        }
        statement.executeUpdate();
    }

    public static ResultSet getList(RestAPIcontext context) throws SQLException {
        ResultSet resultSet=null;
        try {
            RequestMapper.Request request = context.getRequestMapper();
            //String CVName = request.getCVName();
            String sqlQuery = context.getSqlQuery();
            Statement statement = JavaMysql.getConnection().createStatement();
            resultSet = statement.executeQuery(sqlQuery);
        }
        catch(Exception e)
        {
            Util.handleErrorResponse(context,null);
            e.printStackTrace();
        }
        return resultSet;
    }

    public static Boolean uploadFile(InputStreamReader reader,String fileName,long createdTime,long lastUpdatedTime,String query)  {
        try{
            Connection con=JavaMysql.getConnection();
            PreparedStatement p=con.prepareStatement(query);
            p.setString(1,fileName);
            p.setCharacterStream(2,reader);
            p.setLong(3,createdTime);
            p.setLong(4,lastUpdatedTime);
            p.execute();
            return true;
        }
        catch(Exception e)
        {
            e.printStackTrace();
            return false;
        }
    }

}
