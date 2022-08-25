package Tesis.Dao;

import Tesis.Modelos.Laptop;
import Tesis.Util.DatabaseConnection;

import java.sql.*;

public class LaptopDAOImp implements ILaptopDAO{
    static Connection conn = DatabaseConnection.getConnection();

    @Override
    public boolean insertar(Laptop laptop){
        String query = "INSERT INTO baterias_db.dbo.laptop(nombre_pc, fecha_registro)VALUES (?,?)";
        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, laptop.getNombre_PC());
            stmt.setDate(2, laptop.getFecha_Registro());
            stmt.executeUpdate();
            return true;
        }
        catch (SQLException ex){
            //Logear excepcion a futuro
            return false;
        }
    }

    @Override
    public boolean existe(String nombrePC){
        String query = "SELECT nombre_pc FROM baterias_db.dbo.laptop WHERE nombre_pc = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query))
        {
            stmt.setString(1, nombrePC);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                rs.close();
                return true;
            }
            else{
                rs.close();
                return false;
            }

        }
        catch (SQLException ex){
            //Logear excepcion a futuro
            return false;
        }
    }

}
