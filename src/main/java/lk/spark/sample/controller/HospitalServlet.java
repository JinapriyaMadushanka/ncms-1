package lk.spark.sample.controller;

import com.google.gson.JsonObject;
import lk.spark.sample.dao.Hospital;
import lk.spark.sample.service.HospitalService;
import lk.spark.sample.service.HospitalServiceImpl;
import lk.spark.sample.utill.ServletConstants;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;


@WebServlet(name = "HospitalServlet")
public class HospitalServlet extends HttpServlet {
    @Override
    protected void doPost(HttpServletRequest req, HttpServletResponse resp) throws ServletException, IOException {
        Hospital hospital = new Hospital(req.getParameter(ServletConstants.HOSPITAL_NAME),req.getParameter(ServletConstants.DISTRICT),
                Integer.parseInt(req.getParameter(ServletConstants.LOCATION_X)), Integer.parseInt(req.getParameter(ServletConstants.LOCATION_Y)));
        HospitalService hospitalService = new HospitalServiceImpl();
        String result = hospitalService.registerHospital(hospital);
        sendResponse(result, resp);
    }

    private void sendResponse(String data, HttpServletResponse resp) throws IOException
    {
        resp.setContentType(ServletConstants.CONTENT_TYPE);
        PrintWriter writer = resp.getWriter();
        JsonObject json = new JsonObject();
        json.addProperty(ServletConstants.RESPONSE_KEY, data);
        writer.print(json.toString());
        writer.flush();
    }
}
