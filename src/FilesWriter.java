import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

class FilesWriter {

    private final String filePath;

    public FilesWriter(String filePath) {
        this.filePath = filePath;
    }

    public void writeToFile(String content) throws Exception {
        /**
         * Write content in result file
         */
        File file = new File(filePath);
        file.createNewFile();
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(file, true))) {
            bufferedWriter.write(content);
            bufferedWriter.write(System.lineSeparator());
            bufferedWriter.flush();
        } catch (IOException e) {
            throw new Exception(e);
        }
    }
}
