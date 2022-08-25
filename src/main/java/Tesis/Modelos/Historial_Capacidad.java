package Tesis.Modelos;

import Tesis.Util.SQL;

import java.sql.Connection;
import java.sql.Date;
import java.text.MessageFormat;
import java.util.List;

public class Historial_Capacidad {
    private Integer idHistorialCapacidad;
    private Integer capacidad_carga_actual;
    private Float desgaste;
    private Date fecha;
    private String idBateria;

    public Historial_Capacidad() {
    }

    public Historial_Capacidad(Integer capacidad_carga_actual, Float desgaste, Date fecha, String idBateria) {
        this.capacidad_carga_actual = capacidad_carga_actual;
        this.desgaste = desgaste;
        this.fecha = fecha;
        this.idBateria = idBateria;
    }

    public Integer getIdHistorialCapacidad() {
        return idHistorialCapacidad;
    }

    public void setIdHistorialCapacidad(Integer idHistorialCapacidad) {
        this.idHistorialCapacidad = idHistorialCapacidad;
    }

    public Integer getCapacidad_carga_actual() {
        return capacidad_carga_actual;
    }

    public void setCapacidad_carga_actual(Integer capacidad_carga_actual) {
        this.capacidad_carga_actual = capacidad_carga_actual;
    }

    public Float getDesgaste() {
        return desgaste;
    }

    public void setDesgaste(Float desgaste) {
        this.desgaste = desgaste;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public String getIdBateria() {
        return idBateria;
    }

    public void setIdBateria(String idBateria) {
        this.idBateria = idBateria;
    }

    /*
    public void insertarListaEnBase(Connection connection, List<Historial_Capacidad> listHistorialCapacidad){
        SQL sql = new SQL();
        for (Historial_Capacidad hist: listHistorialCapacidad) {
            String query = "INSERT INTO baterias_db.dbo.historial_capacidad (capacidad_carga_actual, desgaste, fecha, id_bateria) " +
                    "VALUES ({0}, {1}, ''{2}'', ''{3}'');";
            query = MessageFormat.format(query, hist.getCapacidad_carga_actual().toString(), hist.getDesgaste().toString(),
                    hist.getFecha().toString(), hist.getIdBateria());
            sql.ejecutarQuery(query,connection);
        }
    }

    public void insertarEnBase(Connection connection, Historial_Capacidad historialCapacidad){
        SQL sql = new SQL();
        String query = "INSERT INTO baterias_db.dbo.historial_capacidad (capacidad_carga_actual, desgaste, fecha, id_bateria) " +
                "VALUES ({0}, {1}, ''{2}'', ''{3}'');";
        query = MessageFormat.format(query, historialCapacidad.getCapacidad_carga_actual().toString(), historialCapacidad.getDesgaste().toString(),
                historialCapacidad.getFecha().toString(), historialCapacidad.getIdBateria());
        sql.ejecutarQuery(query,connection);
    }*/

}
