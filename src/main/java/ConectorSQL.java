import java.sql.Connection;
import java.sql.DriverManager;

public class ConectorSQL {

    public Connection conectar(){
        String connectionUrl = "jdbc:sqlserver://buscaperroapi.database.windows.net:1433;database=tesis_db;user=BuscaPerroAdmin@buscaperroapi;password=Passw0rd;encrypt=true;trustServerCertificate=false;hostNameInCertificate=*.database.windows.net;loginTimeout=30;";
        try {
            // Load SQL Server JDBC driver and establish connection.
            System.out.print("Connecting to SQL Server ... ");
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
