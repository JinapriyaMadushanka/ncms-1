package lk.spark.sample.repository;

import lk.spark.sample.dao.Patient;
import lk.spark.sample.db.DBConnectionPool;

import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PatientRepo {
    public String addPatient(Patient patient){

        int changedRows=0;
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        UUID uid = null;

        try {
//            String firstName = req.getParameter("firstName");
//            String lastName = req.getParameter("lastName");
//            String district = req.getParameter("district");
//            int locationX = Integer.parseInt(req.getParameter("locationX"));
//            int locationY = Integer.parseInt(req.getParameter("locationY"));
////            String hospitalId = req.getParameter("hospitalId");
////            int isDirector = Integer.parseInt(req.getParameter("isDirector"));
//            String gender = req.getParameter("gender");
//            String contact = req.getParameter("contact");
//            String email = req.getParameter("email");
//            int age = Integer.parseInt(req.getParameter("age"));

            uid =UUID.randomUUID();

            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement("INSERT INTO patient (id, first_name, last_name, district, location_x, location_y, gender, contact, email, age ) VALUES (?,?,?,?,?,?,?,?,?,?)");
            stmt.setString(1,uid.toString());
            stmt.setString(2, patient.getFirstName());
            stmt.setString(3, patient.getLastName());
            stmt.setString(4, patient.getDistrict());
            stmt.setInt(5, patient.getLocationX());
            stmt.setInt(6, patient.getLocationY());
            stmt.setString(7,patient.getGender());
            stmt.setString(8,patient.getContact());
            stmt.setString(9,patient.getEmail());
            stmt.setInt(10,patient.getAge());
            changedRows = stmt.executeUpdate();
            //System.out.println( );


//            resp.setContentType("application/json");
//            PrintWriter writer = resp.getWriter();
//            //writer.print("Response: \n");
//            writer.print(changedRows == 1 ? "Successfully Patient Registered" : "Insertion failed");
//            writer.flush();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBConnectionPool.getInstance().close(rs);
            DBConnectionPool.getInstance().close(stmt);
            DBConnectionPool.getInstance().close(con);
        }

        return changedRows == 1 ? uid.toString() : "";
    }
}
