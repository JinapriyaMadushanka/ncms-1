package lk.spark.sample.repository;

import lk.spark.sample.dao.User;
import lk.spark.sample.db.DBConnectionPool;
import lk.spark.sample.utill.Constants;

import java.io.IOException;
import java.sql.*;

public class UserRepo {
    public String userRegister(User user) {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        int changedRow = 0;

        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement(Constants.REGISTER_USER);
            stmt.setString(1, user.getUserName());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getName());
            stmt.setInt(4, user.getMoh());
            stmt.setInt(5, user.getHospital());
            changedRow = stmt.executeUpdate();
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBConnectionPool.getInstance().close(rs);
            DBConnectionPool.getInstance().close(stmt);
            DBConnectionPool.getInstance().close(con);
        }
        return changedRow == 1 ? "User Registerd" : "User registration failed";
    }

    public String userLogin(String userName, String password) {
        ResultSet rs = null;
        Connection con = null;
        PreparedStatement stmt = null;
        String name = null;

        try {
            con = DBConnectionPool.getInstance().getConnection();
            stmt = con.prepareStatement(Constants.LOGIN_USER);
            stmt.setString(1, userName);
            stmt.setString(2, password);
            rs = stmt.executeQuery();
            while(rs.next()){
                name = rs.getString("name");
            }
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            DBConnectionPool.getInstance().close(rs);
            DBConnectionPool.getInstance().close(stmt);
            DBConnectionPool.getInstance().close(con);
        }
        return name;
    }
}
