package lk.spark.sample.repository;

import lk.spark.sample.db.DBConnectionPool;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatisticRepo {
    public int StatisticForGeneral(){
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        int patientCount = 0;

        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement("SELECT COUNT(patient.id) As patientCount FROM patient");
            rs = stmt.executeQuery();
            while(rs.next()){
                patientCount = rs.getInt("patientCount");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBConnectionPool.getInstance().close(rs);
            DBConnectionPool.getInstance().close(stmt);
            DBConnectionPool.getInstance().close(con);
        }
        return patientCount;
    }

    public List statisticForHospital(){
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        List<Integer> hospitalCounts = new ArrayList<Integer>();
        int bedCount = 0;
        int patientCount = 0;

        try {
            patientCount = StatisticForGeneral();
            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement("SELECT COUNT(hospital_bed.id) As bedCount FROM hospital_bed WHERE hospital_bed.patient_id IS NULL");
            rs = stmt.executeQuery();
            while(rs.next()){
                bedCount = rs.getInt("bedCount");
            }
            hospitalCounts.add(patientCount);
            hospitalCounts.add(bedCount);

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBConnectionPool.getInstance().close(rs);
            DBConnectionPool.getInstance().close(stmt);
            DBConnectionPool.getInstance().close(con);
        }
        return hospitalCounts;
    }
}
