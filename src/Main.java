import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String NAME_OF_FOLDER = "1";

    public static void main(String[] args) {
        List<File> listOfAllFiles = new ArrayList<>();
        saveFilesFromDirectory(NAME_OF_FOLDER, listOfAllFiles);
        printListOfFiles(listOfAllFiles);
        printTextFromFiles(createListOfTextFiles(listOfAllFiles));
    }

    static void saveFilesFromDirectory(String directoryName, List<File> listOfAllFiles) {
        File directory = new File(directoryName);
        if (directory.exists()) {
            parseDirectory(directory.listFiles(), listOfAllFiles);
        } else {
            System.out.println("Sorry, wrong name of directory!");
        }
    }

    private static void parseDirectory(File[] filesInOneDirectory, List<File> listOfAllFiles) {
        if (filesInOneDirectory == null) {
            System.out.println("Folder is empty");
            return;
        }
        for (File file : filesInOneDirectory) {
            parseFilesOrMoveToNextDir(listOfAllFiles, file);
        }
    }

    private static void parseFilesOrMoveToNextDir(List<File> listOfAllFiles, File file) {
        if (file.isFile()) {
            listOfAllFiles.add(file);
        } else {
            saveFilesFromDirectory(file.getAbsolutePath(), listOfAllFiles);
        }
    }

    static List<File> createListOfTextFiles(List<File> files) {
        List<File> filteredFiles = new ArrayList<>();
        for (File f : files) {
            if (f.getName().endsWith(".txt")) {
                filteredFiles.add(f);
            }
        }
        return filteredFiles;
    }

    private static void printListOfFiles(List<File> listOfAllFiles) {
        System.out.println("List of all files in directory 1: ");
        for (File f : listOfAllFiles) {
            System.out.println(f.getName());
        }
    }

    static void printTextFromFiles(List<File> listToRead) {
        System.out.println("\nList of all texts in all text files: ");
        String[] texts;
        for (File file : listToRead) {
            System.out.println();
            try (Reader reader = new InputStreamReader(new FileInputStream(file))) {
                char[] array = new char[128];
                int count = reader.read(array);
                StringBuilder result = new StringBuilder();
                while (count > 0) {
                    result.append(new String(array, 0, count));
                    count = reader.read(array);
                }
                texts = result.toString().split(" /n");
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            for (String a : texts) {
                System.out.print(a);
            }
        }
    }
}