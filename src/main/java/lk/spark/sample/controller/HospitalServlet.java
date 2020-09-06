package lk.spark.sample.controller;

import com.google.gson.JsonObject;
import lk.spark.sample.dao.Hospital;
import lk.spark.sample.db.DBConnectionPool;
import lk.spark.sample.service.HospitalService;
import lk.spark.sample.service.HospitalServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.UUID;

@WebServlet(name = "HospitalServlet")
public class HospitalServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Hospital hospital = new Hospital(req.getParameter("name"),req.getParameter("district"),
                Integer.parseInt(req.getParameter("locationX")), Integer.parseInt(req.getParameter("locationY")));
        HospitalService hospitalService = new HospitalServiceImpl();
        String result = hospitalService.registerHospital(hospital);
        sendResponse(result, resp);
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
}
