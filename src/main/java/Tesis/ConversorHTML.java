package Tesis;

import Tesis.Modelos.Bateria;
import Tesis.Modelos.Historial_Capacidad;
import Tesis.Modelos.Laptop;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.sql.Date;
import java.util.LinkedList;
import java.util.List;

public class ConversorHTML{

    public BufferedReader extraerHTML(String ruta) throws IOException {
        File input = new File(ruta);
        Document documento = Jsoup.parse(input);
        Elements elementos = documento.select("td,span.date,span.label");

        StringBuilder sb = new StringBuilder();
        for (Element elem : elementos) {
            sb.append(elem.ownText()).append("\n");
        }

        String reporte="";
        reporte=sb.toString();
        reporte = reporte.replaceAll("(?m)^[ \t]*\r?\n", "");
        //System.out.println(reporte);

        BufferedReader reader = new BufferedReader(new StringReader(reporte));
        return reader;
    }

    public Laptop transformarDatosLaptop(BufferedReader reader) throws IOException {
        //Informacion LapTop
        String nombrePC = "COMPUTER NAME";

        //Variables
        Laptop laptop = new Laptop();
        String aux="";

       aux = reader.readLine();
       while (!(aux.equals("SYSTEM PRODUCT NAME"))) {
           if (aux.equals(nombrePC)) {
               laptop.setNombre_PC(reader.readLine());
           }
            aux = reader.readLine();
        }
       return laptop;
    }

    public Bateria transformarDatosBateria(BufferedReader reader) throws IOException {
        //Informacion bateria
        String modelo = "NAME";
        String manufacturador = "MANUFACTURER";
        String serial = "SERIAL NUMBER";
        String capacidadFabrica = "DESIGN CAPACITY";

        //Variables
        Bateria bateria = new Bateria();
        String aux="";

        while (!(aux.equals("CYCLE COUNT"))) {
            if (aux.equals(modelo)) {
                bateria.setModelo(reader.readLine());
            }
            if (aux.equals(manufacturador)) {
                bateria.setManufacturador(reader.readLine());
            }
            if (aux.equals(serial)) {
                bateria.setSerial(reader.readLine());
            }
            if (aux.equals(capacidadFabrica)) {
                String conv = reader.readLine().substring(0,6);
                conv = conv.replace(".","");
                bateria.setCapacidad_carga_fabrica(Integer.valueOf(conv));
            }
            aux = reader.readLine();
        }
        return bateria;
    }

    public List<Historial_Capacidad> transformarDatosHistoricoBateria(BufferedReader reader) throws IOException {
        //Variables
        Historial_Capacidad historialCapacidad;
        List<Historial_Capacidad> listaCapacidades = new LinkedList<>();
        String aux="";


        while (!(aux.equals("DESIGN CAPACITY"))) {
            aux = reader.readLine();
        }

        while (true) {
            historialCapacidad = new Historial_Capacidad();

            if ((aux = reader.readLine()).equals("AT FULL CHARGE")) {
                break;
            }
            if (aux.length()>11)
                aux = aux.substring(0,aux.length()-13);

            Date fecha = Date.valueOf(aux);
            historialCapacidad.setFecha(fecha);

            aux = reader.readLine();
            if (aux.length()>=2){
                aux = aux.substring(0,aux.length()-4);
                aux = aux.replace(".","");
                historialCapacidad.setCapacidad_carga_actual(Integer.valueOf(aux));
                listaCapacidades.add(historialCapacidad);
            }
            aux = reader.readLine();
        }
        return listaCapacidades;
    }

}
