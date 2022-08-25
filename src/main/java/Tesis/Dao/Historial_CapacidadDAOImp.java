package Tesis.Dao;

import Tesis.Modelos.Historial_Capacidad;
import Tesis.Util.DatabaseConnection;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

public class Historial_CapacidadDAOImp implements IHistorial_CapacidadDAO{
    static Connection conn = DatabaseConnection.getConnection();

    @Override
    public boolean insertarLista(List<Historial_Capacidad> listHistCap) {
        String query = "INSERT INTO baterias_db.dbo.historial_capacidad (capacidad_carga_actual, desgaste, fecha, id_bateria) VALUES (?,?,?,?)";

        for (Historial_Capacidad hist : listHistCap){
            try(PreparedStatement stmt = conn.prepareStatement(query)){
                stmt.setInt(1,hist.getCapacidad_carga_actual());
                stmt.setFloat(2,hist.getDesgaste());
                stmt.setDate(3,hist.getFecha());
                stmt.setString(4,hist.getIdBateria());
                stmt.executeUpdate();
            }
            catch (SQLException ex){
                //Logear excepcion a futuro
                return false;
            }
        }
        return true;
    }

    @Override
    public boolean insertar(Historial_Capacidad histCap) {
        String query = "INSERT INTO baterias_db.dbo.historial_capacidad (capacidad_carga_actual, desgaste, fecha, id_bateria) VALUES (?,?,?,?)";
        try(PreparedStatement stmt = conn.prepareStatement(query)){
            stmt.setInt(1,histCap.getCapacidad_carga_actual());
            stmt.setFloat(2,histCap.getDesgaste());
            stmt.setDate(3,histCap.getFecha());
            stmt.setString(4,histCap.getIdBateria());
            stmt.executeUpdate();
            return true;
        }
        catch (SQLException ex){
            //Logear excepcion a futuro
            return false;
        }
    }

}
