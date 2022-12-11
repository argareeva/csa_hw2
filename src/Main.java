import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {
    static List<File> files;

    public static void main(String[] args) {
        listOfFiles("1", files);
    }
    public static void listOfFiles(String directoryName, List<File> files) {
        File directory = new File(directoryName);
        if(directory.exists()) {
            File[] fList = directory.listFiles();
            if (fList != null) {
                for (File file : fList) {
                    if (file.isFile()) {
                        if (files == null) {
                            files = new ArrayList<>();
                            files.add(file);
                        } else {
                            files.add(file);
                        }
                    } else if (file.isDirectory()) {
                        listOfFiles(file.getAbsolutePath(), files);
                    }
                }
            }
            assert fList != null;
            for (File f : fList) {
                System.out.println(f.getName());
            }
        } else {
            System.out.println("Sorry, wrong name of directory!");
        }
    }
}