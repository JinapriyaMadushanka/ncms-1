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
import java.util.List;

@WebServlet(name = "DoctorServlet")
public class DoctorServlet extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getPathInfo().equals(ServletConstants.REGISTER_DOCTOR)) {
            Doctor doctor = new Doctor(req.getParameter(ServletConstants.DOCTOR_NAME), req.getParameter(ServletConstants.HOSPITAL_ID), Integer.parseInt(req.getParameter(ServletConstants.IS_DIRECTOR)));
            DoctorService doctorService = new DoctorServiceImpl();
            String result = doctorService.registerDoctor(doctor);
            sendResponse(result, resp);
        }else if(req.getPathInfo().equals(ServletConstants.ADMIT_PATIENT)){
            DoctorService doctorService = new DoctorServiceImpl();
            String result = doctorService.admitPatient(req.getParameter(ServletConstants.DOCTOR_ID), req.getParameter(ServletConstants.PATIENT_ID), req.getParameter(ServletConstants.SEVERITY_LEVEL));
            sendResponse(result, resp);
        }else if(req.getPathInfo().equals(ServletConstants.DISCHARGE_PATIENT)){
            DoctorService doctorService = new DoctorServiceImpl();
            String result = doctorService.dischargePatient(req.getParameter(ServletConstants.DOCTOR_ID), req.getParameter(ServletConstants.PATIENT_ID));
            sendResponse(result, resp);
        }
    }

    @Override
    protected void doGet(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        if(req.getPathInfo().equals(ServletConstants.GET_NOT_ADMIT_PATIENTS)) {
            DoctorService doctorService = new DoctorServiceImpl();
            List<String> patientList = doctorService.notAdmitPatients();
            sendResponse(patientList, resp);
        }
    }

    private void sendResponse(String data, HttpServletResponse resp) throws IOException{
        resp.setContentType(ServletConstants.CONTENT_TYPE);
        PrintWriter writer = resp.getWriter();
        JsonObject json = new JsonObject();
        json.addProperty(ServletConstants.RESPONSE_KEY, data);
        writer.print(json.toString());
        writer.flush();
    }

    private void sendResponse(List<String> data, HttpServletResponse resp) throws IOException{
        resp.setContentType(ServletConstants.CONTENT_TYPE);
        PrintWriter writer = resp.getWriter();
        String responseData = new Gson().toJson(data);
        JsonObject json = new JsonObject();
        json.addProperty(ServletConstants.RESPONSE_KEY, responseData);
        writer.print(json.toString());
        writer.flush();
    }
}
