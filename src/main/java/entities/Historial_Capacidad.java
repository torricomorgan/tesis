package entities;

public class Historial_Capacidad {
    private String capacidad_carga_real;
    private String desgaste;
    private String fecha;

    public Historial_Capacidad() {
    }

    public Historial_Capacidad(String capacidad_carga_real, String desgaste, String fecha) {
        this.capacidad_carga_real = capacidad_carga_real;
        this.desgaste = desgaste;
        this.fecha = fecha;
    }

    public String getCapacidad_carga_real() {
        return capacidad_carga_real;
    }

    public void setCapacidad_carga_real(String capacidad_carga_real) {
        this.capacidad_carga_real = capacidad_carga_real;
    }

    public String getDesgaste() {
        return desgaste;
    }

    public void setDesgaste(String desgaste) {
        this.desgaste = desgaste;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}
