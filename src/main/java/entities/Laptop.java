package entities;

public class Laptop {
    private String nombre_PC;
    private String fecha_Registro;

    public Laptop() {
    }

    public Laptop(String nombre_PC, String fecha_Registro) {
        this.nombre_PC = nombre_PC;
        this.fecha_Registro = fecha_Registro;
    }

    public String getNombre_PC() {
        return nombre_PC;
    }

    public void setNombre_PC(String nombre_PC) {
        this.nombre_PC = nombre_PC;
    }

    public String getFecha_Registro() {
        return fecha_Registro;
    }

    public void setFecha_Registro(String fecha_Registro) {
        this.fecha_Registro = fecha_Registro;
    }
}
