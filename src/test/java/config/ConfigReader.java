package config;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigReader {

    private static final Properties prop = new Properties();

    public static Properties initProperties() {
        try {
            FileInputStream fis = new FileInputStream("src/test/resources/config.properties");
            prop.load(fis);

        } catch (Exception e) {
            e.printStackTrace();
        }
        return prop;
    }
}
