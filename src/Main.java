import java.io.File;
import java.util.ArrayList;
import java.util.List;

public class Main {

    private static final String DEFAULT_FOLDER = "1";
    //TODO RESULT_FILE should be defined!
    private static final String RESULT_FILE = "/Users/Albina/Desktop/result.txt";

    public static void main(String[] args) {
        /**
         * Read text files in folder
         */
        FilesReader filesReader = new FilesReader(new File(DEFAULT_FOLDER), "txt");
        List<File> files = filesReader.getFiles();
        /**
         * For sorting we use Node class (because we need to define requires in each file)
         */
        List<Node> unsortedNodes = new ArrayList<>();
        try {
            for (File file : files) {
                unsortedNodes.add(FileUtils.getNode(file));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        /**
         * Sort Nodes
         */
        List<Node> sortedNodes = getSortedNodes(unsortedNodes);
        try {
            FilesWriter filesWriter = new FilesWriter(RESULT_FILE);
            for (Node node : sortedNodes) {
                filesWriter.writeToFile(FileUtils.getTextFileContent(node.getFile()));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        /**
         * To see result in console: printNodes(sortedNodes);
         */
    }

    /**
     * Method to see result in console
     */
    private static void printNodes(List<Node> nodes) {
        try {
            for (Node node : nodes) {
                System.out.println(FileUtils.getTextFileContent(node.getFile()));
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
    }


    private static List<Node> getSortedNodes(List<Node> unsortedNodes) {
        int size = unsortedNodes.size();
        for (int i = 0; i < size; i++) {
            for (int j = i + 1; j < size; j++) {
                if (unsortedNodes.get(i).getDependencies().size() > unsortedNodes.get(j).getDependencies().size()) {
                    Node temp = unsortedNodes.get(i);
                    Node temp2 = unsortedNodes.get(j);
                    unsortedNodes.remove(i);
                    unsortedNodes.add(i, temp2);
                    unsortedNodes.remove(j);
                    unsortedNodes.add(j, temp);
                }
            }
        }
        return unsortedNodes;
    }
}