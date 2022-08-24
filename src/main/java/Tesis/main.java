package Tesis;

import Tesis.Modelos.Bateria;
import Tesis.Modelos.Historial_Capacidad;
import Tesis.Modelos.Laptop;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

public class main {
    //private static Logger logger = LogManager.getLogger(main.class);

    //Ruta
    private static final String ruta = "D:\\Google Drive\\UPSA\\UPSA 2022\\Tesis I\\Mis documentos\\Reportes\\DESKTOP-3KRT5CS 02-06-2022.html";

    //Objetos
    private static Laptop laptop = new Laptop();
    private static Bateria bateria = new Bateria();
    private static List<Historial_Capacidad> capacidadList = new ArrayList<>();
    private static ConversorHTML conversorHTML = new ConversorHTML();
    private static SQL conectorSQL = new SQL();

    public static void main(String[] args) throws IOException, ParseException, SQLException {
        Historial_Capacidad histAux = new Historial_Capacidad();

        //Extrear informacion del HTML
        try(BufferedReader datosHtml = conversorHTML.extraerHTML(ruta)){
            laptop = conversorHTML.transformarDatosLaptop(datosHtml);
            bateria = conversorHTML.transformarDatosBateria(datosHtml);
            capacidadList = conversorHTML.transformarDatosHistoricoBateria(datosHtml);
        } //Fin del try del BufferReader

        //Borrar luego
        System.out.println(laptop.getNombre_PC());
        System.out.println(bateria.getModelo() + " " + bateria.getManufacturador() + " "  + bateria.getSerial() + " "  + bateria.getCapacidad_carga_fabrica());
        for (Historial_Capacidad elem : capacidadList) {
            System.out.println(elem.getCapacidad_carga_actual().toString()+" "+elem.getFecha());
        }
        //Hasta aqui

        try(Connection connection = conectorSQL.conectar()){
            Boolean laptopExiste = laptop.existe(connection, laptop.getNombre_PC());
            //Si la laptop no existe, se registran todos los datos
            if (!laptopExiste){
                laptop.insertarEnBase(connection,laptop);
                bateria.insertarEnBase(connection,bateria);
                histAux.insertarListaEnBase(connection, capacidadList);
            }
            //La laptop ya existe
            else{
                //Si la laptop ya existe pero la bateria no, se da de baja la bateria antigua y se inserta la nueva.
                Boolean bateriaExiste = bateria.existe(connection, bateria.getIdBateria());
                if(!bateriaExiste){
                    bateria.insertarEnBase(connection,bateria);
                    String idBat = bateria.buscarBateriaLaptop(connection, laptop.getNombre_PC());
                    bateria.darDeBaja(connection, idBat);
                    histAux.insertarListaEnBase(connection, capacidadList);
                }
                //Si la bateria ya existe solo se inserta el valor actual al historial
                else{
                    histAux = new Historial_Capacidad();
                    histAux.insertarEnBase(connection, histAux);
                }
            }
        }
    }
}
