package Tesis.Modelos;

import java.sql.Date;

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

}
