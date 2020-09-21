package lk.spark.sample.repository;

import lk.spark.sample.db.DBConnectionPool;
import lk.spark.sample.utill.Constants;

import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class QueueRepo {
    public String insertIntoQueue(String patientId) {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement(Constants.INSERT_PATIENT_TO_QUEUE);
            stmt.setString(1, patientId);


        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBConnectionPool.getInstance().close(rs);
            DBConnectionPool.getInstance().close(stmt);
            DBConnectionPool.getInstance().close(con);
        }
        return "Patient added to Queue";
    }
}
