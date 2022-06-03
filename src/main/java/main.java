import Modelos.Bateria;
import Modelos.Historial_Capacidad;
import Modelos.Laptop;

import java.io.BufferedReader;
import java.io.IOException;
import java.sql.Connection;
import java.text.ParseException;
import java.util.List;

public class main {
    //Ruta
    private static  final String ruta = "D:\\Google Drive\\UPSA\\UPSA 2022\\Tesis I\\Mis documentos\\Reportes\\DESKTOP-3KRT5CS 02-06-2022.html";

    public static void main(String[] args) throws IOException, ParseException {
        ConversorHTML conversorHTML = new ConversorHTML();
        ConectorSQL conectorSQL = new ConectorSQL();
        Historial_Capacidad histAux = new Historial_Capacidad();


        BufferedReader datosHtml = conversorHTML.extraerHTML(ruta);

        Laptop laptop = conversorHTML.transformarDatosLaptop(datosHtml);
        Bateria bateria = conversorHTML.transformarDatosBateria(datosHtml);
        List<Historial_Capacidad> capacidadList = conversorHTML.transformarDatosHistoricoBateria(datosHtml);


        System.out.println(laptop.getNombre_PC());
        System.out.println(bateria.getModelo() + " " + bateria.getManufacturador() + " "  + bateria.getSerial() + " "  + bateria.getCapacidad_carga_fabrica());
        for (Historial_Capacidad elem : capacidadList) {
            System.out.println(elem.getCapacidad_carga_actual().toString()+" "+elem.getFecha());
        }

        Connection connection = conectorSQL.conectar();

        laptop.insertarEnBase(connection,laptop);
        bateria.insertarEnBase(connection,bateria);
        for (Historial_Capacidad hist: capacidadList) {
            histAux.insertarEnBase(connection,hist);
        }
    }
}
