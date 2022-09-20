package Tesis.Util;

import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public class DatabaseConnection {

    private static Connection conn = null;

    private DatabaseConnection()
    {
        try {
            Properties props = new Properties();
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
            String url = props.getProperty("jdbc.url");

            conn = DriverManager.getConnection(url);
        }
        catch (SQLException | IOException e) {
            e.printStackTrace();
        }
    }

    public static Connection getConnection(){

        if (conn == null){
            new DatabaseConnection();
        }

        return conn;
    }

    public static void closeConnection()
    {
        if (conn != null)
        {
            try {
                conn.close();
            }
            catch (SQLException e){

            }
        }
    }

}