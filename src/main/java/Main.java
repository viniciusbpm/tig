import org.apache.commons.codec.digest.DigestUtils;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class Main {

    public static String readFile() throws IOException {
        String filePath ="/home/vini/Downloads/testfile.txt";

        BufferedReader reader = new BufferedReader(new FileReader(filePath));
        StringBuilder fullContent = new StringBuilder();
        String currentLine = reader.readLine();

        while (currentLine != null) {
            fullContent.append(currentLine).append("\n");
            currentLine = reader.readLine();
        }

        return fullContent.toString();
    }

    public static String getFileHash(String fileContent){
        int fileContentByteSize = fileContent.getBytes().length;
        String headerAndContent = "commit " + fileContentByteSize + "\u0000" + fileContent;
        return DigestUtils.sha1Hex(headerAndContent);
    }

    public static void main(String[] args) {
        try {
            String fileContent = readFile();
            String fileHash = getFileHash(fileContent);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
