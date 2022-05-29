import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.BufferedReader;
import java.io.File;
import java.io.IOException;
import java.io.StringReader;
import java.util.LinkedHashMap;
import java.util.Map;

public class ConversorHTML{

    private static final String fechaRegistro = "REPORT TIME";

    //Informacion LapTop
    private static final String nombrePC = "COMPUTER NAME";

    //Informacion bateria
    private static final String modelo = "NAME";
    private static final String manufacturador = "MANUFACTURE";
    private static final String serial = "SERIAL NUMBER";
    private static final String capacidadFabrica = "DESIGN CAPACITY";

    //Historico Bateria
    private static Map<String, String> historicoBateria = new LinkedHashMap<>();

    public void leerHTML() throws IOException {
        File input = new File("D:\\Familia\\battery-report.html");
        Document document = Jsoup.parse(input);

        Elements elements = document.select("td,span.date");

        // A new String that will save all of the data take from html
        String trimReport = "";

        // Initialized trimReport with all of the data from BatteryReport.html
        for (Element e : elements) {
            trimReport = trimReport + e.ownText() + "\n";
        }
        trimReport = trimReport.replaceAll("(?m)^[ \t]*\r?\n", "");
        System.out.println(trimReport);

        //BufferedReader reader = new BufferedReader(new StringReader(trimReport));
    }
}
