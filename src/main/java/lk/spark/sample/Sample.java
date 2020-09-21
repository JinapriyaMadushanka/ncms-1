package lk.spark.sample;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.*;
import java.util.Properties;

@WebServlet(name = "Sample")
public class Sample extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
//        Properties properties = new Properties();
//        OutputStream outputStream = new FileOutputStream("/dbConfig.properties");
//
//        properties.setProperty("driverClassName","com.mysql.cj.jdbc.Driver");
//        properties.setProperty("url","jdbc:mysql://localhost:3306/ncms");
//        properties.setProperty("userName","root");
//        properties.setProperty("password", "");
//        System.out.println("okk");

        //properties.store(outputStream, null);



        System.out.println("olll");
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        writer.print("Response: \n");
        writer.print("This is a sample programme");
        writer.flush();

    }
}
