package config;

import java.io.InputStream;
import java.util.Properties;
import java.io.IOException;

public class ConfigReader {

    private static final Properties properties = new Properties();

    static {
        try {
            InputStream input = ConfigReader.class
                    .getClassLoader()
                    .getResourceAsStream("config.properties");

            properties.load(input);
        } catch (Exception e) {
            throw new RuntimeException("Failed to load config.properties file");
        }
    }

    public static String get(String key) {
        return properties.getProperty(key);
    }

    public static int getInt(String key) {
        return Integer.parseInt(properties.getProperty(key));
    }
}
