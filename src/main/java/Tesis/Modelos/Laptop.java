package Tesis.Modelos;

import Tesis.SQL;

import java.sql.Connection;
import java.sql.Date;
import java.text.MessageFormat;

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
        SQL sql = new SQL();
        String query = "INSERT INTO baterias_db.dbo.laptop(nombre_pc, fecha_registro)VALUES ({0}, {1});";
        query = MessageFormat.format(query, laptop.getNombre_PC(), laptop.getFecha_Registro());
        sql.ejecutarQuery(query,connection);
    }

    public boolean existe(Connection connection, String nombre_PC){
        SQL sql = new SQL();
        String query = "SELECT nombre_pc FROM baterias_db.dbo.laptop WHERE nombre_pc = '{0}'";
        query = MessageFormat.format(query, nombre_PC);
        if (sql.existe(query,connection))
            return true;
        else
            return false;
    }
}
