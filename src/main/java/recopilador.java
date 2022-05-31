import entities.Bateria;
import entities.Historial_Capacidad;
import entities.Laptop;
import org.jsoup.nodes.Element;

import java.io.BufferedReader;
import java.io.IOException;
import java.util.List;

public class recopilador {
    //Ruta
    private static  final String ruta = "D:\\Familia\\battery-report.html";

    public static void main(String[] args) throws IOException {
        ConversorHTML conversorHTML = new ConversorHTML();

        BufferedReader datosHtml = conversorHTML.extraerHTML(ruta);

        Laptop laptop = conversorHTML.transformarDatosLaptop(datosHtml);
        Bateria bateria = conversorHTML.transformarDatosBateria(datosHtml);
        List<Historial_Capacidad> capacidadList = conversorHTML.transformarDatosHistoricoBateria(datosHtml);


        System.out.println(laptop.getNombre_PC());
        System.out.println(bateria.getModelo() + " " + bateria.getManufacturador() + " "  + bateria.getSerial() + " "  + bateria.getCapacidad_carga_fabrica());
        for (Historial_Capacidad elem : capacidadList) {
            System.out.println(elem.getCapacidad_carga_real()+" "+elem.getFecha());
        }
    }
}
