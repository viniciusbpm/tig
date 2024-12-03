import org.apache.commons.codec.digest.DigestUtils;
import utils.FileCompressionUtils;
import utils.FileUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

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
        String header = "object " + fileContentByteSize + "\u0000";
        String headerAndContent =  header + fileContent;
        return DigestUtils.sha1Hex(headerAndContent);
    }

    public static void main(String[] args) {
        try {
            String fileContent = readFile();
            String fileHash = getFileHash(fileContent);

            byte[] compressedContent = FileCompressionUtils.compress(fileContent.getBytes(StandardCharsets.UTF_8));
            String compressedContentString = new String(compressedContent, StandardCharsets.UTF_8);

            // FileUtils.createFile(fileHash, compressedContentString);
            System.out.println(compressedContentString);

            byte[] decompressedContent = FileCompressionUtils.decompress(compressedContent);
            String originalContent = new String(decompressedContent, StandardCharsets.UTF_8);
            System.out.println(originalContent);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
