package Tesis.Modelos;
import Tesis.SQL;

import java.sql.Connection;
import java.sql.Date;
import java.text.MessageFormat;

public class Bateria {
    private String idBateria;
    private String modelo;
    private String manufacturador;
    private String serial;
    private Integer Capacidad_carga_fabrica;
    private Date fecha_Registro;
    private String estado;
    private String nombre_pc;

    public Bateria() {
    }

    public Bateria(String idBateria, String modelo, String manufacturador, String serial, Integer capacidad_carga_fabrica, Date fecha_Registro, String estado, String nombre_pc) {
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

    public Date getFecha_Registro() {
        return fecha_Registro;
    }

    public void setFecha_Registro(Date fecha_Registro) {
        this.fecha_Registro = fecha_Registro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }

    public String getNombre_pc() {
        return nombre_pc;
    }

    public void setNombre_pc(String nombre_pc) {
        this.nombre_pc = nombre_pc;
    }

    public void insertarEnBase(Connection connection, Bateria bateria){
        SQL sql = new SQL();
        String query = "INSERT INTO baterias_db.dbo.bateria (id_bateria, serial_bateria, modelo, manufacturador, capacidad_carga_fabrica, fecha_registro, estado, nombre_pc) " +
                "VALUES ({0}, {1}, {2}, {3}, {4}, {5}, {6}, {7});";
        query = MessageFormat.format(query, bateria.getIdBateria(),bateria.getSerial(), bateria.getModelo(),
                bateria.getManufacturador(), bateria.getCapacidad_carga_fabrica(), bateria.getFecha_Registro(), bateria.getEstado(), bateria.getNombre_pc());
        sql.ejecutarQuery(query,connection);
    }

    public boolean existe(Connection connection, String idBateria){
        SQL sql = new SQL();
        String query = "SELECT id_bateria FROM baterias_db.dbo.bateria WHERE id_bateria = '{0}';";
        query = MessageFormat.format(query, idBateria);
        if (sql.existe(query,connection))
            return true;
        else
            return false;
    }

    public String buscarBateriaLaptop(Connection connection, String nombrePC){
        String idBat = "";
        SQL sql = new SQL();
        String query = "SELECT id_bateria FROM baterias_db.dbo.bateria WHERE nombre_pc = '{0}' AND estado = 100;";
        query = MessageFormat.format(query, nombrePC);
        idBat = sql.devolverId(query, "id_bateria", connection);
        return idBat;
    }

    public void darDeBaja(Connection connection, String idBateria){
        SQL sql = new SQL();
        String query = "UPDATE baterias_db.dbo.bateria SET estado = 200 WHERE id_bateria = '{0}';";
        query = MessageFormat.format(query, idBateria);
        sql.ejecutarQuery(query,connection);
    }
}
