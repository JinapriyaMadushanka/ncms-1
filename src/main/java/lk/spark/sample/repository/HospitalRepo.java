package lk.spark.sample.repository;

import lk.spark.sample.dao.Hospital;
import lk.spark.sample.dao.HospitalsWithBeds;
import lk.spark.sample.db.DBConnectionPool;
import lk.spark.sample.utill.Constants;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.UUID;

public class HospitalRepo {

    public String registerHospital(Hospital hospital){
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;

        ResultSet rss = null;
        Connection conn = null;
        PreparedStatement stmtt = null;

        int changedRows = 0;
        int changeRowss = 0;

        try {
            UUID uid = UUID.randomUUID();
            String uId = uid.toString();

            con = DBConnectionPool.getInstance().getConnection();
            conn = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement(Constants.REGISTER_HOSPITAL);
            stmt.setString(1, uId);
            stmt.setString(2, hospital.getName());
            stmt.setString(3, hospital.getDistrict());
            stmt.setInt(4, hospital.getLocationX());
            stmt.setInt(5, hospital.getLocationY());
            stmt.setDate(6, new Date(new java.util.Date().getTime()));
            changedRows = stmt.executeUpdate();
            //System.out.println( );
            stmtt = conn.prepareStatement(Constants.createBedsForHospital(uId));
            changeRowss = stmtt.executeUpdate();


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
            stmt = con.prepareStatement(Constants.SELECT_HOSPITALS_WITH_BEDS);
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
            stmt = con.prepareStatement(Constants.ASSIGN_PATIENT_TO_BED);
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
