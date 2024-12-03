import org.apache.commons.codec.digest.DigestUtils;
import utils.FileCompressionUtils;
import utils.FileUtils;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.nio.charset.StandardCharsets;

public class Commit {
    public String readFile() {
        String filePath ="/home/vini/Downloads/testfile.txt";
        StringBuilder fullContent = new StringBuilder();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(filePath));
            String currentLine = reader.readLine();

            while (currentLine != null) {
                fullContent.append(currentLine).append("\n");
                currentLine = reader.readLine();
            }
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        return fullContent.toString();
    }

    public String getFileHash(String fileContent){
        int fileContentByteSize = fileContent.getBytes().length;
        String header = "object " + fileContentByteSize + "\u0000";
        String headerAndContent =  header + fileContent;
        return DigestUtils.sha1Hex(headerAndContent);
    }

    public void doCommit() {
        String fileContent = readFile();
        String fileHash = getFileHash(fileContent);

        byte[] compressedContent = FileCompressionUtils.compress(fileContent.getBytes(StandardCharsets.UTF_8));
        String compressedContentString = new String(compressedContent, StandardCharsets.UTF_8);

        FileUtils.createFile(fileHash, compressedContentString);
    }
}
