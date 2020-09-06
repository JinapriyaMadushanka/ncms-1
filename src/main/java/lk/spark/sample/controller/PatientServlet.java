package lk.spark.sample.controller;


import com.google.gson.JsonObject;
import lk.spark.sample.dao.Patient;
import lk.spark.sample.db.DBConnectionPool;
import lk.spark.sample.service.PatientService;
import lk.spark.sample.service.PatientServiceImpl;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

@WebServlet(name = "PatientServlet")
public class PatientServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {

        Patient patient = new Patient(req.getParameter("firstName"),req.getParameter("lastName"),
                req.getParameter("district"), Integer.parseInt(req.getParameter("locationX")),
                Integer.parseInt(req.getParameter("locationY")), req.getParameter("gender"), req.getParameter("contact"),
                req.getParameter("email"), Integer.parseInt(req.getParameter("age")));

        PatientService patientService = new PatientServiceImpl();
        String result =  patientService.registerPetient(patient);
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
