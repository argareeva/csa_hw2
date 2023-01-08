import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

class FileUtils {

    private FileUtils() {
    }

    static String getTextFileContent(File file) throws Exception {
        StringBuilder sb = new StringBuilder();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while (br.ready()) {
                String line = br.readLine();
                sb.append(line);
                sb.append(System.lineSeparator());
            }
            return sb.toString();
        } catch (IOException e) {
            throw new Exception(e);
        }
    }

    static Node getNode(File file) throws Exception {
        List<String> dependencies = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            while (br.ready()) {
                String line = br.readLine();
                int firstIndexOf = line.indexOf("‘");
                if (firstIndexOf == -1) continue;
                String resultText = line.substring(firstIndexOf + 1, firstIndexOf + 18).replace("/", "\\");
                dependencies.add(resultText);
            }
        } catch (IOException e) {
            throw new Exception(e);
        }
        return new Node(file, dependencies);
    }
}
