import org.apache.commons.codec.digest.DigestUtils;

import java.io.BufferedReader;
import java.io.ByteArrayOutputStream;
import java.io.FileReader;
import java.io.IOException;
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

    public static byte[] compress(byte[] input) {
        Deflater deflater = new Deflater();
        deflater.setInput(input);
        deflater.finish();

        ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
        byte[] buffer = new byte[1024];

        while (!deflater.finished()) {
            int compressedSize = deflater.deflate(buffer);
            outputStream.write(buffer, 0, compressedSize);
        }

        return outputStream.toByteArray();
    }

    public static void main(String[] args) {
        try {
            String fileContent = readFile();
            String fileHash = getFileHash(fileContent);
            byte[] compressedContent = compress(fileContent.getBytes(StandardCharsets.UTF_8));

            String str = new String(compressedContent, StandardCharsets.UTF_8);

            System.out.println(str);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
