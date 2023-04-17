package com.ApplicationRunner;

import com.RestApi.RestApiServlet;
import org.apache.catalina.Context;
import org.apache.catalina.LifecycleException;
import org.apache.catalina.startup.Tomcat;

import java.io.BufferedReader;
import java.io.File;
import java.io.InputStreamReader;
import java.util.concurrent.TimeUnit;

public class ApplicationUtil {

    private static final String homeDir = System.getProperty("user.home") + "/EmbeddedServer/tomcat/bin";
    private static final String basDir =  System.getProperty("user.home") + "/home/project/build/WEB-INF";
    static
    {

    }
    public static void startServer() throws Exception {
        RestApiServlet restApiServlet=new RestApiServlet();
        Tomcat tomcat=new Tomcat();
        tomcat.setBaseDir(basDir);
        Context context =tomcat.addContext("",basDir);
        tomcat.setPort(8081);
//        tomcat.addWebapp("/project",basDir);
        tomcat.addServlet("","testServlet", restApiServlet);
        context.addServletMappingDecoded("/api/*","testServlet");
        tomcat.start();
        tomcat.getServer().await();
    }

    public static void executeCommand(String... command)
    {
        ProcessBuilder processbuilder = new ProcessBuilder("sh","run.sh");
        processbuilder.directory(new File(homeDir));
        try {
            Process process = processbuilder.start();
            process.waitFor();
            BufferedReader reader = new BufferedReader(new InputStreamReader(process.getInputStream()));
            String line = "";
            while ((line = reader.readLine()) != null) {
                System.out.println(line);
            }
//            TimeUnit.SECONDS.sleep(150);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
