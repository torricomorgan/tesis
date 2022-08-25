package Tesis.Dao;

import Tesis.Modelos.Historial_Capacidad;

import java.util.List;

public interface IHistorial_CapacidadDAO {
    boolean insertarLista(List<Historial_Capacidad> listHistCap);
    boolean insertar(Historial_Capacidad histCap);
}
