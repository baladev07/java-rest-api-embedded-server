package com.ApplicationRunner;

public class RunApplication {

    public static void main(String args[])
    {
//        JmeterHandler.runJmeter();
        try{
            ApplicationUtil.startServer();
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
