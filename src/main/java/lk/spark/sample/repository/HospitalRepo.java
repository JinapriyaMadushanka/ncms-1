package lk.spark.sample.repository;

import lk.spark.sample.dao.Hospital;
import lk.spark.sample.dao.HospitalsWithBeds;
import lk.spark.sample.db.DBConnectionPool;

import java.io.PrintWriter;
import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

public class HospitalRepo {

    public String registerHospital(Hospital hospital) {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;

        ResultSet rss = null;
        Connection conn = null;
        PreparedStatement stmtt = null;

        int changedRows = 0;
        int changeRowss = 0;

        try {
//            String name = req.getParameter("name");
//            String district = req.getParameter("district");
//            int locationX = Integer.parseInt(req.getParameter("locationx"));
//            int locationY = Integer.parseInt(req.getParameter("locationy"));


            UUID uid = UUID.randomUUID();
            String uId = uid.toString();

            con = DBConnectionPool.getInstance().getConnection();
            conn = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement("INSERT INTO hospital (id, name, district, location_x, location_y, build_date) VALUES (?,?,?,?,?,?)");
            stmt.setString(1, uId);
            stmt.setString(2, hospital.getName());
            stmt.setString(3, hospital.getDistrict());
            stmt.setInt(4, hospital.getLocationX());
            stmt.setInt(5, hospital.getLocationY());
            stmt.setDate(6, new Date(new java.util.Date().getTime()));
            changedRows = stmt.executeUpdate();
            //System.out.println( );
            stmtt = conn.prepareStatement("INSERT INTO hospital_bed (hospital_id) VALUES ('" + uId + "'),('" + uId + "'),('" + uId + "'),('" + uId + "')," +
                    "('" + uId + "'),('" + uId + "'),('" + uId + "'),('" + uId + "'),('" + uId + "'),('" + uId + "')");
            changeRowss = stmtt.executeUpdate();

//            resp.setContentType("application/json");
//            PrintWriter writer = resp.getWriter();
//            //writer.print("Response: \n");
//            writer.print(changedRows == 1 ? "Successfully Hospital Registered" : "Insertion failed");
//            writer.flush();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnectionPool.getInstance().close(rs);
            DBConnectionPool.getInstance().close(stmt);
            DBConnectionPool.getInstance().close(con);

            DBConnectionPool.getInstance().close(rss);
            DBConnectionPool.getInstance().close(stmtt);
            DBConnectionPool.getInstance().close(conn);
        }
        return (changedRows > 0 && changeRowss > 0 ? "Registration Success" : "Registration Failed");
    }

    public ArrayList<HospitalsWithBeds> selectHospitalsWithBeds(){
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        ArrayList<HospitalsWithBeds> hospitalData = new ArrayList<>();

        try {
            con = DBConnectionPool.getInstance().getConnection();
//            stmt = con.prepareStatement("SELECT hospital.id, hospital.location_x, hospital.location_y FROM hospital INNER JOIN hospital_bed ON hospital.id = hospital_bed.hospital_id AND hospital_bed.patient_id IS NULL");
            stmt = con.prepareStatement("SELECT DISTINCT hospital.id, hospital.location_x, hospital.location_y FROM hospital INNER JOIN hospital_bed ON hospital.id=hospital_bed.hospital_id AND hospital_bed.patient_id IS NULL");
            rs = stmt.executeQuery();
            while(rs.next()){
                HospitalsWithBeds hospitalsWithBeds = new HospitalsWithBeds(rs.getString("id"),
                        rs.getInt("location_x"),
                        rs.getInt("location_y")
                        //rs.getInt("id")
                        );
                hospitalData.add(hospitalsWithBeds);
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBConnectionPool.getInstance().close(rs);
            DBConnectionPool.getInstance().close(stmt);
            DBConnectionPool.getInstance().close(con);
        }
        return hospitalData;
    }

    public void admitPatient(String hospitalId, String result){
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement("UPDATE hospital_bed SET hospital_bed.patient_id = ? WHERE hospital_bed.hospital_id = ? AND hospital_bed.patient_id IS NULL LIMIT 1");
            stmt.setString(1, result);
            stmt.setString(2, hospitalId);
            int rows = stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBConnectionPool.getInstance().close(rs);
            DBConnectionPool.getInstance().close(stmt);
            DBConnectionPool.getInstance().close(con);
        }
    }
}
