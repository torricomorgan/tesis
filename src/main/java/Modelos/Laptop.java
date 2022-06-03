package Modelos;

import java.sql.Connection;
import java.sql.Date;

public class Laptop {
    private String nombre_PC;
    private Date fecha_Registro;

    public Laptop() {
    }

    public Laptop(String nombre_PC, Date fecha_Registro) {
        this.nombre_PC = nombre_PC;
        this.fecha_Registro = fecha_Registro;
    }

   public String getNombre_PC() {
        return nombre_PC;
    }

    public void setNombre_PC(String nombre_PC) {
        this.nombre_PC = nombre_PC;
    }

    public Date getFecha_Registro() {
        return fecha_Registro;
    }

    public void setFecha_Registro(Date fecha_Registro) {
        this.fecha_Registro = fecha_Registro;
    }

    public void insertarEnBase(Connection connection, Laptop laptop){

    }

    public void buscar(Connection connection, int id){

    }
}
