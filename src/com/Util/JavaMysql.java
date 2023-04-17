package com.Util;

import java.sql.*;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class JavaMysql {
    public static void main(java.lang.String args[]) throws SQLException {
        //Boolean result=insertCustomer();
       // Boolean result = deleteCustomer();
      // List<Map<String, String>> m=getCustomer();
       // System.out.print(m);
    }


    public static Boolean insertCustomer(Map<String,String>customerDetails) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        Connection connect = null;
        // String password="password";
        try {
            connect = DriverManager.getConnection(url, username, "");
            String sql = "INSERT INTO customer_table (customer_name,customer_product) VALUES (?,?)";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1, customerDetails.get("customer_name"));
            statement.setString(2, customerDetails.get("customer_product"));
            int rows = statement.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;

        } finally {
            connect.close();

        }
    }
    public  List<Map<String, String>> getCustomer() throws SQLException {
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        Connection connect = null;
        Map<String,String> detailsMap=null;
        List<Map<String, String>> customerList=new ArrayList<>();
        // String password="password";
        try {
            connect = DriverManager.getConnection(url, username, "");
            String sql = "select * From Customer_table";
            Statement statement = connect.createStatement();
            ResultSet result = statement.executeQuery(sql);
            while(result.next())
            {
                detailsMap=new HashMap<String,String>();
                detailsMap.put("customer_name",result.getString("customer_name"));
                detailsMap.put("customer_id",result.getString("customer_id"));
                detailsMap.put("customer_product",result.getString("customer_product"));
                customerList.add(detailsMap);
            }

        } catch (Exception e) {
            System.out.println(e);
            connect.close();

        }
        return customerList;

    }

    public static Boolean updateCustomer(Map<String,String> customerUpdate) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        Connection connect = null;
        // String password="password";
        try {
            connect = DriverManager.getConnection(url, username, "");
            String sql = "update customer_table set customer_name= ?,customer_product= ? where customer_id= ?";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1,customerUpdate.get("customer_name"));
            statement.setString(2,customerUpdate.get("customer_product"));
            statement.setString(3,customerUpdate.get("customer_id"));
            statement.executeUpdate();
            return true;
        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            assert connect != null;
            connect.close();
        }
    }
    public static Boolean deleteCustomer(String customerID) throws SQLException {
        String url = "jdbc:mysql://localhost:3306/test";
        String username = "root";
        Connection connect = null;
        // String password="password";
        try {
            connect = DriverManager.getConnection(url, username, "");
            String sql = "delete from customer_table where customer_id = ?";
            PreparedStatement statement = connect.prepareStatement(sql);
            statement.setString(1,customerID);
            statement.executeUpdate();
            return true;

        } catch (Exception e) {
            System.out.println(e);
            return false;
        } finally {
            assert connect != null;
            connect.close();
        }
    }
    public static Connection getConnection() {
        Connection connection=null;
        try{
            String url = "jdbc:mysql://localhost:3306/test";
            String username = "root";
            connection=DriverManager.getConnection(url, username, "");;
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
        return connection;
    }
}

