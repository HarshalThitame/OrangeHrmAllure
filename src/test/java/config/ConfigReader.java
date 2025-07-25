package config;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private final static Properties prop = new Properties();
    private static boolean isLoaded = false;


    public static void loadProperties(String env) {
        String PATH = "src/test/resources/config/" + env + ".properties";
        if (!isLoaded) {
            try {
                FileInputStream file = new FileInputStream(PATH);
                prop.load(file);
                isLoaded = true;
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
    }

    public static String get(String key) {
        return prop.getProperty(key);
    }

}
