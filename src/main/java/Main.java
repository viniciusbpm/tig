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

    public static void main(String[] args) {
        try {
            String fileContent = readFile();
            System.out.println(fileContent);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
