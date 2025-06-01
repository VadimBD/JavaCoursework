package org.example.onlinestore.config;


import org.example.onlinestore.utils.ISettings;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ApplicationProperties implements ISettings {
    private Properties properties;
    public ApplicationProperties() {
        properties = new Properties();
        try (InputStream input = getClass().getClassLoader().getResourceAsStream("application.properties")) {
            if (input == null) {
                System.out.println("Sorry, unable to find application.properties");
                return;
            }
            // Load a properties file from class path, inside static method
            properties.load(input);
        } catch (IOException ex) {
            ex.printStackTrace();
        }
    }
    @Override
    public String getValue(String key) {
        return  properties.getProperty(key);
    }

}