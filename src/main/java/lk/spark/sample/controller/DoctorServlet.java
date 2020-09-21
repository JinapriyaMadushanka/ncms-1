package lk.spark.sample.controller;

import com.google.gson.Gson;
import com.google.gson.JsonObject;
import lk.spark.sample.dao.Doctor;
import lk.spark.sample.service.DoctorService;
import lk.spark.sample.service.DoctorServiceImpl;
import lk.spark.sample.utill.ServletConstants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.*;
import java.util.List;
import java.util.UUID;

@WebServlet(name = "DoctorServlet")
public class DoctorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getPathInfo().equals(ServletConstants.REGISTER_DOCTOR)) {
            Doctor doctor = new Doctor(req.getParameter("name"), req.getParameter("hospitalId"), Integer.parseInt(req.getParameter("isDirector")));
            DoctorService doctorService = new DoctorServiceImpl();
            String result = doctorService.registerDoctor(doctor);
            sendResponse(result, resp);
        }else if(req.getPathInfo().equals(ServletConstants.ADMIT_PATIENT)){
            DoctorService doctorService = new DoctorServiceImpl();
            String result = doctorService.admitPatient(req.getParameter("doctorId"), req.getParameter("patientId"), req.getParameter("severityLevel"));
            sendResponse(result, resp);
        }else if(req.getPathInfo().equals(ServletConstants.DISCHARGE_PATIENT)){
            DoctorService doctorService = new DoctorServiceImpl();
            String result = doctorService.dischargePatient(req.getParameter("doctorId"), req.getParameter("patientId"));
            sendResponse(result, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getPathInfo().equals("/getNotAdmitPatients")) {
            DoctorService doctorService = new DoctorServiceImpl();
            List<String> patientList = doctorService.notAdmitPatients();
            sendResponse(patientList, resp);
        }
    }

    private void sendResponse(String data, HttpServletResponse resp) throws IOException{
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        JsonObject json = new JsonObject();
        json.addProperty("Response", data);
        writer.print(json.toString());
        writer.flush();
    }

    private void sendResponse(List<String> data, HttpServletResponse resp) throws IOException{
        resp.setContentType("application/json");
        PrintWriter writer = resp.getWriter();
        String responseData = new Gson().toJson(data);
        JsonObject json = new JsonObject();
        json.addProperty("Response", responseData);
        writer.print(json.toString());
        writer.flush();
    }
}
