package util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertyLoader {
    private static final String PROPERTIES_FILE = "app.properties";

    public static String loadProperty(String name){
        Properties properties = new Properties();
        try (InputStream inputStream = PropertyLoader.class.getClassLoader().getResourceAsStream(PROPERTIES_FILE)){
            properties.load(inputStream);

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return properties.getProperty(name);
    }
}
