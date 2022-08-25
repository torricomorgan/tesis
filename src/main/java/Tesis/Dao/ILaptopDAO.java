package Tesis.Dao;
import Tesis.Modelos.Laptop;

public interface ILaptopDAO {
    boolean insertar(Laptop laptop);
    boolean existe(String nombrePC);

}
