import utils.FileUtils;

import java.nio.file.Path;
import java.util.List;

public class InitProject {
    private static final String BASE_PATH = "/home/vini/";
    private static final String FOLDER_NAME = ".tig";
    private static final List<String> SUBDIRECTORIES = List.of("hooks", "info", "objects", "refs");

    public void init() {
        Path main = Path.of(BASE_PATH, FOLDER_NAME);
        FileUtils.createDirectory(main);

        createSubdirectories(main);
    }

    private void createSubdirectories(Path parentPath) {
        for(String subdirectory : SUBDIRECTORIES) {
            Path subdirectoryPath = parentPath.resolve(subdirectory);
            FileUtils.createDirectory(subdirectoryPath);
        }
    }
}
