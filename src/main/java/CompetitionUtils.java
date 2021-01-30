import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.transform.OutputKeys;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.File;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class CompetitionUtils {

    public static List<Competitor> getCompetitorsFromFile() {
        List<Competitor> competitorList = new ArrayList<>();
        List<String> linesList = CustomFileReader.getLinesFromFile("results.csv");
        for (String s : linesList) {
            Competitor competitor = new Competitor(s.split(";"));
            competitorList.add(competitor);
        }
        return competitorList;
    }

    public static int getTotalPoints(Competitor competitor) {
        int totalPoint = 0;
        List<String> linesList = CustomFileReader.getLinesFromFile("coeficients.csv");
        int i = 0;
        for (String s : linesList) {
            float a = Float.parseFloat(s.split(";")[0]);
            float b = Float.parseFloat(s.split(";")[1]);
            float c = Float.parseFloat(s.split(";")[2]);
            float[] arr = {a, b, c};
            totalPoint += getOneDisciplinePoint(competitor.getResultByIndex(i), arr);
            i++;
        }
        return totalPoint;
    }

    private static int getOneDisciplinePoint(float result, float[] arr) {
        int point = (int) (arr[0] * (Math.pow(Math.abs(result - arr[1]), arr[2])));
        return point;
    }

    public static void exportCompetitionResult() {
        DocumentBuilderFactory dbFactory = DocumentBuilderFactory.newInstance();
        DocumentBuilder dBuilder;
        try {
            dBuilder = dbFactory.newDocumentBuilder();
            Document doc = dBuilder.newDocument();
            //add elements to Document
            Element rootElement =
                    doc.createElementNS("", "Competitors");
            //append root element to document
            doc.appendChild(rootElement);

            //append first child element to root element
            List<Competitor> competitors = getCompetitorsFromFile();
            Collections.sort(competitors);
            Collections.reverse(competitors);
            calculateCompetitorsPlace(competitors);
            for (Competitor competitor : competitors) {
                rootElement.appendChild(getCompetitor(doc, competitor));
            }

            //for output to file, console
            TransformerFactory transformerFactory = TransformerFactory.newInstance();
            Transformer transformer = transformerFactory.newTransformer();
            //for pretty print
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");
            DOMSource source = new DOMSource(doc);

            //write to console or file
            StreamResult console = new StreamResult(System.out);
            StreamResult file = new StreamResult(new File("competitors.xml"));

            //write data
//            transformer.transform(source, console);
            transformer.transform(source, file);

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void calculateCompetitorsPlace(List<Competitor> competitors) {
        if (competitors.size() == 0) return;
        int start = 0;
        for (int i = 1; i < competitors.size(); i++) {
            start = i;
            while (start < competitors.size() && competitors.get(start).getTotalPoint() == competitors.get(start - 1).getTotalPoint()) {
                start++;
            }
            if (start == i) {
                competitors.get(i - 1).setPlace(String.valueOf(i));
            } else {
                for (int j = i - 1; j < start; j++) {
                    competitors.get(j).setPlace((i) + "-" + (start));
                }
                i = start;
            }
        }
        if (competitors.get(competitors.size() - 1).getPlace().equals("UNDEFINED")) {
            competitors.get(competitors.size() - 1).setPlace(String.valueOf(start + 1));
        }
    }

    private static Node getCompetitor(Document doc, Competitor competitor) {
        Element node = doc.createElement("competitor");
        node.appendChild(doc.createTextNode(competitor.toString()));
        return node;
    }
}