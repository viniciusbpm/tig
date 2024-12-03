import org.apache.commons.codec.digest.DigestUtils;
import utils.FileCompressionUtils;
import utils.FileUtils;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.zip.DataFormatException;
import java.util.zip.Deflater;
import java.util.zip.Inflater;

public class Main {
    public static void main(String[] args) {
        try {


            byte[] decompressedContent = FileCompressionUtils.decompress(compressedContent);
            String originalContent = new String(decompressedContent, StandardCharsets.UTF_8);
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }
}
