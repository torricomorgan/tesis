package Tesis.Dao;

import Tesis.Modelos.Bateria;
import Tesis.Util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class BateriaDAOImp implements IBateriaDAO{
    static Connection conn = DatabaseConnection.getConnection();

    @Override
    public boolean insertar(Bateria bateria) {
        String query = "INSERT INTO baterias_db.dbo.bateria (id_bateria, serial_bateria, modelo, manufacturador, capacidad_carga_fabrica, fecha_registro, estado, nombre_pc) " +
                "VALUES (?,?,?,?,?,?,?,?)";
        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1,bateria.getIdBateria());
            stmt.setString(2,bateria.getSerial());
            stmt.setString(3,bateria.getModelo());
            stmt.setString(4,bateria.getManufacturador());
            stmt.setInt(5,bateria.getCapacidad_carga_fabrica());
            stmt.setDate(6,bateria.getFecha_Registro());
            stmt.setInt(7,bateria.getEstado());
            stmt.setString(8,bateria.getNombre_pc());
            stmt.executeUpdate();
            return true;
        }
        catch (SQLException ex){
            //Logear excepcion a futuro
            return false;
        }
    }

    @Override
    public boolean existe(String idBateria) {
        String query = "SELECT id_bateria FROM baterias_db.dbo.bateria WHERE id_bateria = ?";

        try (PreparedStatement stmt = conn.prepareStatement(query))
        {
            stmt.setString(1, idBateria);
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

    @Override
    public String buscarBateriaLaptop(String nombreLaptop) {
        String query = "SELECT id_bateria FROM baterias_db.dbo.bateria WHERE nombre_pc = ? AND estado = 100";
        String idBateria = "";

        try (PreparedStatement stmt = conn.prepareStatement(query))
        {
            stmt.setString(1, nombreLaptop);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()){
                idBateria = rs.getString("id_bateria");
                rs.close();
                return idBateria;
            }
            else{
                rs.close();
                return idBateria;
            }

        }
        catch (SQLException ex){
            //Logear excepcion a futuro
            return idBateria;
        }
    }

    @Override
    public boolean darDeBaja(String idBateria) {
        String query = "UPDATE baterias_db.dbo.bateria SET estado = 200 WHERE id_bateria = ?";
        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setString(1, idBateria);
            stmt.executeUpdate();
            return true;
        }
        catch (SQLException ex){
            //Logear excepcion a futuro
            return false;
        }
    }
}
