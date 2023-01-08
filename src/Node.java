import java.io.File;
import java.util.List;

class Node {

    private final File file;
    /**
     * Each file has list of its dependencies - for sorting
     */
    private final List<String> dependencies;

    public Node(File file, List<String> dependencies) {
        this.file = file;
        this.dependencies = dependencies;
    }

    public File getFile() {
        return file;
    }

    public List<String> getDependencies() {
        return dependencies;
    }
}
