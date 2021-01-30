import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.util.List;
import java.util.stream.Collectors;

public class CustomFileReader {
    public static List<String> getLinesFromFile(String fileName) {
        List<String> linesList = null;
        File file = new File(fileName);
        BufferedReader bufferedReader;
        try (FileReader fileReader = new FileReader(file)) {
            bufferedReader = new BufferedReader(fileReader);
            linesList = bufferedReader.lines().collect(Collectors.toList());
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return linesList;
    }
}
