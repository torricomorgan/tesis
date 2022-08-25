package Tesis.Modelos;

import java.sql.Date;

public class Bateria {
    private String idBateria;
    private String modelo;
    private String manufacturador;
    private String serial;
    private Integer Capacidad_carga_fabrica;
    private Integer capacidadActual;
    private Date fecha_Registro;
    private Integer estado;
    private String nombre_pc;

    public Bateria() {
    }

    public Bateria(String idBateria, String modelo, String manufacturador, String serial, Integer capacidad_carga_fabrica, Date fecha_Registro, Integer estado, String nombre_pc) {
        this.idBateria = idBateria;
        this.modelo = modelo;
        this.manufacturador = manufacturador;
        this.serial = serial;
        this.Capacidad_carga_fabrica = capacidad_carga_fabrica;
        this.fecha_Registro = fecha_Registro;
        this.estado = estado;
        this.nombre_pc = nombre_pc;
    }

    public String getIdBateria() {
        return idBateria;
    }

    public void setIdBateria(String idBateria) {
        this.idBateria = idBateria;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String modelo) {
        this.modelo = modelo;
    }

    public String getManufacturador() {
        return manufacturador;
    }

    public void setManufacturador(String manufacturador) {
        this.manufacturador = manufacturador;
    }

    public String getSerial() {
        return serial;
    }

    public void setSerial(String serial) {
        this.serial = serial;
    }

    public Integer getCapacidad_carga_fabrica() {
        return Capacidad_carga_fabrica;
    }

    public void setCapacidad_carga_fabrica(Integer capacidad_carga_fabrica) {
        Capacidad_carga_fabrica = capacidad_carga_fabrica;
    }

    public Integer getCapacidadActual() {
        return capacidadActual;
    }

    public void setCapacidadActual(Integer capacidadActual) {
        this.capacidadActual = capacidadActual;
    }

    public Date getFecha_Registro() {
        return fecha_Registro;
    }

    public void setFecha_Registro(Date fecha_Registro) {
        this.fecha_Registro = fecha_Registro;
    }

    public Integer getEstado() {
        return estado;
    }

    public void setEstado(Integer estado) {
        this.estado = estado;
    }

    public String getNombre_pc() {
        return nombre_pc;
    }

    public void setNombre_pc(String nombre_pc) {
        this.nombre_pc = nombre_pc;
    }

}
