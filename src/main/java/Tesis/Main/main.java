package Tesis.Main;

import Tesis.Dao.*;
import Tesis.Modelos.Bateria;
import Tesis.Modelos.Historial_Capacidad;
import Tesis.Modelos.Laptop;
import Tesis.Util.ConversorHTML;
import Tesis.Util.DatabaseConnection;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Date;

import java.util.ArrayList;
import java.util.List;

public class main {
    //private static Logger logger = LogManager.getLogger(main.class);

    //Ruta
    private static final String ruta = "D:\\Google Drive\\UPSA\\UPSA 2022\\Tesis I\\Mis documentos\\Reportes\\DESKTOP-3KRT5CS 02-06-2022.html";

    //Objetos
    private static final ConversorHTML conversorHTML = new ConversorHTML();
    private static final ILaptopDAO laptopDAO = new LaptopDAOImp();
    private static final IBateriaDAO bateriaDAO = new BateriaDAOImp();
    private static final IHistorial_CapacidadDAO histCapDAO = new Historial_CapacidadDAOImp();

    public static void main(String[] args) throws IOException {
        //Variables
        Laptop laptop = new Laptop();
        Bateria bateria = new Bateria();
        List<Historial_Capacidad> capacidadList = new ArrayList<>();
        Historial_Capacidad histAux = new Historial_Capacidad();
        long millis = System.currentTimeMillis();
        Date fecha = new Date(millis);
        String idBateria = "";

        //Extrear informacion del HTML
        try (BufferedReader datosHtml = conversorHTML.extraerHTML(ruta)) {
            laptop = conversorHTML.transformarDatosLaptop(datosHtml);
            laptop.setFecha_Registro(fecha);

            bateria = conversorHTML.transformarDatosBateria(datosHtml);
            idBateria = bateria.getManufacturador() + bateria.getModelo() + bateria.getSerial();
            idBateria = idBateria.replaceAll("\\s", "");
            bateria.setIdBateria(idBateria);
            bateria.setEstado(100);
            bateria.setFecha_Registro(fecha);
            bateria.setNombre_pc(laptop.getNombre_PC());

            capacidadList = conversorHTML.transformarDatosHistoricoBateria(datosHtml, bateria);
        } //Fin del try del BufferReader

        //Borrar luego
        System.out.println(laptop.getNombre_PC());
        System.out.println(bateria.getModelo() + " " + bateria.getManufacturador() + " " + bateria.getSerial() + " " + bateria.getCapacidad_carga_fabrica());
        for (Historial_Capacidad elem : capacidadList) {
            System.out.println(elem.getCapacidad_carga_actual().toString() + " " + elem.getFecha() + " " + elem.getDesgaste());
        }
        //Hasta aqui

        boolean laptopExiste = laptopDAO.existe(laptop.getNombre_PC());
        //Si la laptop no existe, se registran todos los datos
        if (!laptopExiste) {
            laptopDAO.insertar(laptop);
            bateriaDAO.insertar(bateria);
            histCapDAO.insertarLista(capacidadList);
        }
        //La laptop ya existe
        else {
            //Si la laptop ya existe pero la bateria no, se da de baja la bateria antigua y se inserta la nueva.
            boolean bateriaExiste = bateriaDAO.existe(bateria.getIdBateria());
            if (!bateriaExiste) {
                bateriaDAO.insertar(bateria);
                String idBat = bateriaDAO.buscarBateriaLaptop(laptop.getNombre_PC());
                bateriaDAO.darDeBaja(idBat);
                histCapDAO.insertarLista(capacidadList);
            }
            //Si la bateria ya existe solo se inserta el valor actual al historial
            else {
                histAux = new Historial_Capacidad();
                histCapDAO.insertar(histAux);
            }
        }

        DatabaseConnection.closeConnection();
    }//Fin del main
}
