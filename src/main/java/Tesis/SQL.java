package Tesis;

import java.sql.Connection;
import java.sql.DriverManager;

public class SQL {

    public Connection conectar(){
        String connectionUrl = "jdbc:sqlserver://tesis-baterias.database.windows.net:1433;database=baterias_db;user=torricomorgan@tesis-baterias;password=Passw0rd;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
        try {
            // Load Tesis.SQL Server JDBC driver and establish connection.
            System.out.print("Connecting to Tesis.SQL Server ... ");
            try (Connection connection = DriverManager.getConnection(connectionUrl)) {
                System.out.println("Done.");
                return connection;
            }
        } catch (Exception e) {
            System.out.println();
            e.printStackTrace();
        }
        return null;
    }


}
