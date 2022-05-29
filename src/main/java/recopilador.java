import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.io.File;
import java.io.IOException;


public class recopilador {
    public static void main(String[] args) throws IOException {
        File input = new File("D:\\Familia\\battery-report.html");
        Document document = Jsoup.parse(input, "UTF-8");

        Elements elements = document.select("td");

        // A new String that will save all of the data take from html
        String trimReport = "";

        // Initialized trimReport with all of the data from BatteryReport.html
        for (Element e : elements) {
            trimReport = trimReport + e.ownText() + "\n";
        }
        System.out.println(trimReport);
    }
}
