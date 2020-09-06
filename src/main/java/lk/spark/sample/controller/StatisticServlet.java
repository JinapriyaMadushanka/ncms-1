package lk.spark.sample.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lk.spark.sample.service.StatisticService;
import lk.spark.sample.service.StatisticServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

@WebServlet(name = "StatisticServlet")
public class StatisticServlet extends HttpServlet {
    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        String user = req.getParameter("user");
        StatisticService statisticService = new StatisticServiceImpl();

        if(user.equals("general")){
            int result = statisticService.getStatisticForGeneral();
            sendResponse(Integer.toString(result), resp);
        }else if(user.equals("hospital")){
            List<Integer> hospitalCounts = statisticService.getStatisticForHospital();
            sendResponse(hospitalCounts, resp);
        }
    }

    private void sendResponse(String data, HttpServletResponse resp) throws IOException
    {
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        JsonObject json = new JsonObject();
        json.addProperty("Response", data);
        writer.print(json.toString());
        writer.flush();
    }

    private void sendResponse(List<Integer> data, HttpServletResponse resp) throws IOException{
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        String responseData = new Gson().toJson(data);
        JsonObject json = new JsonObject();
        json.addProperty("Response", responseData);
        writer.print(json.toString());
        writer.flush();
    }
}
