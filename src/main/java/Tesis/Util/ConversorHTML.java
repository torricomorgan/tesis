package Tesis.Util;

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
       while (true) {
           if (aux.equals(nombrePC)) {
               laptop.setNombre_PC(reader.readLine());
           }

           if(aux.equals("REPORT TIME")){
               aux = reader.readLine();
               if (aux.length()>11)
                   aux = aux.substring(0,aux.length()-13);

               Date fecha = Date.valueOf(aux);
               laptop.setFechaReporte(fecha);
               break;
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
        String capacidadActual = "FULL CHARGE CAPACITY";

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
            if (aux.equals(capacidadActual)) {
                String conv = reader.readLine().replace(" mWh","");
                conv = conv.replace(".","");
                bateria.setCapacidadActual(Integer.valueOf(conv));
            }
            aux = reader.readLine();
        }
        return bateria;
    }

    public List<Historial_Capacidad> transformarDatosHistoricoBateria(BufferedReader reader, Bateria bateria) throws IOException {
        //Variables
        Historial_Capacidad historialCapacidad;
        List<Historial_Capacidad> listaCapacidades = new LinkedList<>();
        String aux="";
        Float desgaste = 0f;


        while (!(aux.equals("DESIGN CAPACITY"))) {
            aux = reader.readLine();
        }

        while (true) {
            historialCapacidad = new Historial_Capacidad();
            historialCapacidad.setIdBateria(bateria.getIdBateria());

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
                desgaste = ((1-((float)historialCapacidad.getCapacidad_carga_actual()/bateria.getCapacidad_carga_fabrica())));
                historialCapacidad.setDesgaste(desgaste);
                listaCapacidades.add(historialCapacidad);
            }

            aux = reader.readLine();
        }
        return listaCapacidades;
    }

}
