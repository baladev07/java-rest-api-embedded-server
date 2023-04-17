package com.Util;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
import java.util.Map;
import java.util.stream.Collectors;

public class FetchRequestData {

    public static void fetchRequestData(ServletRequest servletRequest) throws SQLException {
        HttpServletRequest req= (HttpServletRequest) servletRequest;
        String dataBaseUrl = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        Connection connect = null;
        Date date = new Date();
        Long currentTimeInMillis=date.getTime();
        // String password="password";
        try {
            connect = DriverManager.getConnection(dataBaseUrl, username, "");
            String insertTestCaseQuery = "INSERT INTO TestCasesTable(RequestApi,RequestHeader,RequestPayload,TestGroupId,RequestMethod,CreatedTime,LastUpdatedTime) values(?,?,?,?,?,?,?);";
            String insertTestGroupQuery="INSERT INTO TestGroupTable(TestOrgId,CreatedTime,LastUpdatedTime) VALUES(?,?,?);";
            PreparedStatement testcaseStatement = connect.prepareStatement(insertTestCaseQuery);
            testcaseStatement.setString(1,req.getRequestURI());
            testcaseStatement.setString(2,getHeaders(req));
            testcaseStatement.setString(3,getPayload(req));
            testcaseStatement.setString(4,"12345");
            testcaseStatement.setString(5,req.getMethod());
            testcaseStatement.setLong(6,currentTimeInMillis);
            testcaseStatement.setLong(7,currentTimeInMillis);
            testcaseStatement.executeUpdate();
            PreparedStatement testGroupStatement=connect.prepareStatement(insertTestGroupQuery);
            testGroupStatement.setString(1,"12345");
            testGroupStatement.setLong(2,currentTimeInMillis);
            testGroupStatement.setLong(3,currentTimeInMillis);
            testGroupStatement.executeUpdate();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            connect.close();
        }
    }

    public static String getHeaders(HttpServletRequest request)
    {
        Map<String, String> headers = (Map<String, String>) Collections.list(request.getHeaderNames())
                .stream()
                .collect(Collectors.toMap(h -> h, request::getHeader));
        return headers.toString();
    }

    public static String getPayload(HttpServletRequest request) throws IOException {
        String reqPayload = request.getReader().lines()
                .reduce("", (accumulator, actual) -> accumulator + actual);
        return reqPayload;
    }
}
