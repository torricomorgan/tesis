package Tesis.Dao;
import Tesis.Modelos.Bateria;

public interface IBateriaDAO {
    boolean insertar(Bateria bateria);
    boolean existe(String idBateria);
    String buscarBateriaLaptop(String nombreLaptop);
    boolean darDeBaja(String idBateria);
}
