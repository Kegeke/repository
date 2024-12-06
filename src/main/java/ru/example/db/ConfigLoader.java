package ru.example.db;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ConfigLoader {
    private final Properties properties;

    public ConfigLoader() {
        var resourcePath = "db.properties";
        properties = new Properties();

        try (InputStream input = getClass().getClassLoader().getResourceAsStream(resourcePath)) {
            if (input == null) {
                throw new RuntimeException("Configuration file not found: " + resourcePath);
            }
            properties.load(input);
        } catch (IOException e) {
            throw new RuntimeException("Failed to load configuration file: " + resourcePath, e);
        }
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }
}
