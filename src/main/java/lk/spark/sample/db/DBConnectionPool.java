package lk.spark.sample.db;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import org.apache.commons.dbcp2.BasicDataSource;

public class DBConnectionPool {
//    protected static Connection initializeDatabase
private static DBConnectionPool instance;
    private BasicDataSource basicDataSource;

    private Properties getDbData() {

        Properties properties = new Properties();
        InputStream inputStream = null;
        try {
            inputStream = new FileInputStream("dbConfig.properties");
            properties.load(inputStream);
            System.out.println(properties.getProperty("url"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return properties;
    }


    private DBConnectionPool() {

        Properties properties = getDbData();

//        basicDataSource.setDriverClassName("com.mysql.cj.jdbc.Driver");     //this is the driver class for the mysql
//        basicDataSource.setUrl("jdbc:mysql://localhost:3306/ncms");         //should be of the form jdbc:mysql://localhost:3306/<db name>
//        basicDataSource.setUsername("root");                                //db username
//        basicDataSource.setPassword("");                            //db password
        basicDataSource = new BasicDataSource();
        basicDataSource.setDriverClassName(properties.getProperty("driverClassName"));     //this is the driver class for the mysql
        basicDataSource.setUrl(properties.getProperty("url"));         //should be of the form jdbc:mysql://localhost:3306/<db name>
        basicDataSource.setUsername(properties.getProperty("userName"));                                //db username
        basicDataSource.setPassword(properties.getProperty("password"));                            //db password
        basicDataSource.setMinIdle(2);
        basicDataSource.setMaxIdle(5);
        basicDataSource.setMaxTotal(10);
    }

    public static DBConnectionPool getInstance() {
        if(instance == null)
        {
            instance = new DBConnectionPool();
        }
        return instance;
    }

    public Connection getConnection() throws SQLException {
        return this.basicDataSource.getConnection();
    }

    public void close(AutoCloseable closeable)
    {
        try
        {
            if(closeable != null)
            {
                closeable.close();
            }
        }
        catch(Exception e)
        {
            e.printStackTrace();
        }
    }
}
