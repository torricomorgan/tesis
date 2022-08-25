package Tesis.Util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DatabaseConnection {

    private static Connection conn = null;

    private DatabaseConnection()
    {
        String url = "jdbc:sqlserver://tesis-baterias.database.windows.net:1433;database=baterias_db;" +
                "user=torricomorgan@tesis-baterias;password=Passw0rd;" +
                "encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
        try {
            conn = DriverManager.getConnection(url);
        }
        catch (SQLException e) {
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