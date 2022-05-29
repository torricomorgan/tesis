package entities;

public class Bateria {
    private String modelo;
    private String manufacturador;
    private String serial;
    private String Capacidad_carga_fabrica;
    private String fecha_Registro;
    private String estado;

    public Bateria() {
    }

    public Bateria(String modelo, String manufacturador, String serial, String capacidad_carga_fabrica, String fecha_Registro, String estado) {
        this.modelo = modelo;
        this.manufacturador = manufacturador;
        this.serial = serial;
        Capacidad_carga_fabrica = capacidad_carga_fabrica;
        this.fecha_Registro = fecha_Registro;
        this.estado = estado;
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

    public String getCapacidad_carga_fabrica() {
        return Capacidad_carga_fabrica;
    }

    public void setCapacidad_carga_fabrica(String capacidad_carga_fabrica) {
        Capacidad_carga_fabrica = capacidad_carga_fabrica;
    }

    public String getFecha_Registro() {
        return fecha_Registro;
    }

    public void setFecha_Registro(String fecha_Registro) {
        this.fecha_Registro = fecha_Registro;
    }

    public String getEstado() {
        return estado;
    }

    public void setEstado(String estado) {
        this.estado = estado;
    }
}
