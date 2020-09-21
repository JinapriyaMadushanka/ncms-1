package lk.spark.sample.repository;

import lk.spark.sample.dao.Doctor;
import lk.spark.sample.db.DBConnectionPool;
import lk.spark.sample.utill.Constants;

import java.io.IOException;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class DoctorRepo {
    public String doctorRegister(Doctor doctor) {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        int changedRows = 0;


        try {
            UUID uid =UUID.randomUUID();

            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement(Constants.INSERT_DOCTOR);
            stmt.setString(1,uid.toString());
            stmt.setString(2, doctor.getName());
            stmt.setString(3, doctor.getHospitalId());
            stmt.setInt(4, doctor.getIsDirector());
            changedRows= stmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBConnectionPool.getInstance().close(rs);
            DBConnectionPool.getInstance().close(stmt);
            DBConnectionPool.getInstance().close(con);
        }
        return (changedRows == 1 ? "Hospital Succesfully registered.": "Hospital reg failed");
    }

    public String admitPatients(String doctorId, String patientId, String severityLevel) {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        int rowCount = 0;

        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement(Constants.ADMIT_PATIENT);
            stmt.setDate(1, new Date(new java.util.Date().getTime()));
            stmt.setString(2, doctorId);
            stmt.setString(3, severityLevel);
            stmt.setString(4, patientId);
            rowCount = stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBConnectionPool.getInstance().close(rs);
            DBConnectionPool.getInstance().close(stmt);
            DBConnectionPool.getInstance().close(con);
        }
        return rowCount == 1 ? "Admitted" : "Admit failed";
    }

    public List getNewRegisteredPatients() {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        List<String> patientList = new ArrayList<String>();
        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement(Constants.NEW_REGISTER_PATIENTS);
            rs = stmt.executeQuery();
            while (rs.next()){
                patientList.add(rs.getString("id"));
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBConnectionPool.getInstance().close(rs);
            DBConnectionPool.getInstance().close(stmt);
            DBConnectionPool.getInstance().close(con);
        }
        return patientList;
    }

    public String dischargePatients(String doctorId, String patientId) {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;

        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement(Constants.DISCHARGE_PATIENT);
            stmt.setDate(1, new Date(new java.util.Date().getTime()));
            stmt.setString(2, doctorId);
            stmt.setString(3, patientId);
            stmt.executeUpdate();

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBConnectionPool.getInstance().close(rs);
            DBConnectionPool.getInstance().close(stmt);
            DBConnectionPool.getInstance().close(con);
        }
        return "patient Dischrged";
    }
}
