package george;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.Properties;

public class FileInitializer {
    private static final String PATH_TO_PROPERTIES = "src/source/application.properties";
    private static final FileInitializer FILE_INITIALIZER = new FileInitializer();
    private final Properties PROPERTIES = new Properties();
    private File source;

    private FileInitializer(){
        try {
            PROPERTIES.load(new FileInputStream(PATH_TO_PROPERTIES));
            source = new File(PROPERTIES.getProperty("filepath"));
            System.out.println(source.getAbsolutePath());
        } catch (IOException e) {
            System.out.println("Error: The property file is not founded.");
        }
    }

    public static FileInitializer getFileInitializer(){
        return FILE_INITIALIZER;
    }

    public byte[] getSource() throws IOException {
        String s = "";

        try(FileReader reader = new FileReader(source))
        {
            int c;
            while((c=reader.read())!=-1){
                s +=(char) c;
            }
        }
        catch(IOException e){
            System.out.println(e.getMessage());
        }

        return s.getBytes(StandardCharsets.UTF_8);
    }
}
