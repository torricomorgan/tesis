package Tesis.Util;

import java.sql.*;

public class SQL {

    private static final String connectionUrl = "";

    public Connection conectar(){
        try {
            // Load Tesis.Util.SQL Server JDBC driver and establish connection.
            System.out.print("Connecting to Tesis.Util.SQL Server ... ");
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

    //Para insertar o actualizar los registros
    public Boolean ejecutarQuery(String query, Connection conn){
        try(Statement stmt = conn.createStatement()){
            stmt.executeUpdate(query);
            conn.commit();
            return true;
        }
        catch (SQLException ex){
            //Logear excepcion a futuro
            return false;
        }
    }

    //Si existe el registro devuelve verdadero
    public Boolean existe(String query, Connection conn){
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query))
        {
            if (rs.next())
                return true;
        }
        catch (SQLException ex){
            //Logear excepcion a futuro
            return false;
        }
        return false;
    }

    public String devolverId(String query, String columna,Connection conn){
        String id = "";
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query))
        {
            if (rs.next()){
                id = rs.getString(columna);
                return id;
            }
        }
        catch (SQLException ex){
            //Logear excepcion a futuro

            return id;
        }
        return id;
    }

}
