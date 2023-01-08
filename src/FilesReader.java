import java.io.File;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

class FilesReader {

    private final List<File> files;

    public FilesReader(File dir, String format) {
        files = loadFiles(dir, format);
    }

    private List<File> loadFiles(File dir, String format) {
        List<File> files = new ArrayList<>();
        return loadFiles(files, dir, format);
    }

    /**
     * Read only text files
     */
    private List<File> loadFiles(List<File> files, File dir, String format) {
        if (!dir.isDirectory()) {
            if (getFileExtension(dir).endsWith(format)) {
                files.add(dir);
            }
            return files;
        }
        for (File file : Objects.requireNonNull(dir.listFiles())) {
            loadFiles(files, file, format);
        }
        return files;
    }

    private String getFileExtension(File file) {
        String name = file.getName();
        int lastIndexOf = name.lastIndexOf(".");
        if (lastIndexOf == -1) return "";
        return name.substring(lastIndexOf);
    }

    public List<File> getFiles() {
        return files;
    }
}
