package Tesis.Main;

import Tesis.Dao.*;
import Tesis.Modelos.Bateria;
import Tesis.Modelos.Historial_Capacidad;
import Tesis.Modelos.Laptop;
import Tesis.Util.ConversorHTML;
import Tesis.Util.DatabaseConnection;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.sql.Date;

import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class main {
    //private static Logger logger = LogManager.getLogger(main.class);

    //Objetos
    private static final ConversorHTML conversorHTML = new ConversorHTML();
    private static final ILaptopDAO laptopDAO = new LaptopDAOImp();
    private static final IBateriaDAO bateriaDAO = new BateriaDAOImp();
    private static final IHistorial_CapacidadDAO histCapDAO = new Historial_CapacidadDAOImp();

    public static void main(String[] args){
        String rutaInicio = "";
        String rutaDestino = "";

        try {
            Properties props = new Properties();
            props.load(Thread.currentThread().getContextClassLoader().getResourceAsStream("config.properties"));
            rutaInicio = props.getProperty("ruta.inicio");
            rutaDestino = props.getProperty("ruta.destino");
        }
        catch (IOException e){
            //Log
        }

        File path = new File(rutaInicio);
        File [] files = path.listFiles();

        for (File reporte : files){
            if (reporte.isFile()) {
                //Variables
                Laptop laptop = new Laptop();
                Bateria bateria = new Bateria();
                List<Historial_Capacidad> capacidadList = new ArrayList<>();
                Historial_Capacidad histAux = new Historial_Capacidad();
                long millis = System.currentTimeMillis();
                Date fecha = new Date(millis);
                String idBateria = "";

                //Extrear informacion del HTML
                try (BufferedReader datosHtml = conversorHTML.extraerHTML(reporte.getAbsolutePath())) {
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
                catch (IOException e){
                    //Log
                }

                //Borrar luego
                /*System.out.println(laptop.getNombre_PC());
                System.out.println(bateria.getModelo() + " " + bateria.getManufacturador() + " " + bateria.getSerial() + " " + bateria.getCapacidad_carga_fabrica());
                for (Historial_Capacidad elem : capacidadList) {
                    System.out.println(elem.getCapacidad_carga_actual().toString() + " " + elem.getFecha() + " " + elem.getDesgaste());
                }*/
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
                        histAux.setCapacidad_carga_actual(bateria.getCapacidadActual());
                        histAux.setFecha(laptop.getFechaReporte());
                        histAux.setIdBateria(bateria.getIdBateria());
                        float desgaste = ((1-((float)bateria.getCapacidadActual()/bateria.getCapacidad_carga_fabrica())));
                        histAux.setDesgaste(desgaste);
                        histCapDAO.insertar(histAux);
                    }
                }

                reporte.renameTo(new File(rutaDestino+reporte.getName()));
                reporte.deleteOnExit();
            }
        } //Fin de los reportes

        DatabaseConnection.closeConnection();
    }//Fin del main
}
