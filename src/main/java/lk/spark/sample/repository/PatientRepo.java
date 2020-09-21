package lk.spark.sample.repository;

import lk.spark.sample.dao.Patient;
import lk.spark.sample.db.DBConnectionPool;
import lk.spark.sample.utill.Constants;

import java.io.IOException;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.UUID;

public class PatientRepo {
    public String addPatient(Patient patient) {


        ResultSet rs = null;
        PreparedStatement stmt = null;
        Connection con = null;
        int changedRows = 0;
        UUID uid = null;
        try {
            changedRows = 0;
            uid = UUID.randomUUID();

            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement(Constants.INSERT_PATIENT);
            stmt.setString(1, uid.toString());
            stmt.setString(2, patient.getFirstName());
            stmt.setString(3, patient.getLastName());
            stmt.setString(4, patient.getDistrict());
            stmt.setInt(5, patient.getLocationX());
            stmt.setInt(6, patient.getLocationY());
            stmt.setString(7, patient.getGender());
            stmt.setString(8, patient.getContact());
            stmt.setString(9, patient.getEmail());
            stmt.setInt(10, patient.getAge());
            changedRows = stmt.executeUpdate();

        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            DBConnectionPool.getInstance().close(rs);
            DBConnectionPool.getInstance().close(stmt);
            DBConnectionPool.getInstance().close(con);
            return changedRows == 1 ? uid.toString() : "";
        }

    }
}
