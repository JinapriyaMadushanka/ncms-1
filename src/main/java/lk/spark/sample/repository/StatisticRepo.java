package lk.spark.sample.repository;

import lk.spark.sample.db.DBConnectionPool;
import lk.spark.sample.utill.Constants;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class StatisticRepo {
    public int StatisticForGeneral() {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        int patientCount = 0;

        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement(Constants.GENARAL_STATISTICS);
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

    public List statisticForHospital() {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        List<Integer> hospitalCounts = new ArrayList<Integer>();
        int bedCount = 0;
        int patientCount = 0;

        try {
            patientCount = StatisticForGeneral();
            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement(Constants.HOSPITAL_STATISTICS);
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
