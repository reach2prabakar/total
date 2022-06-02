package com.toptal.library;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;


public class PropertyReader {
    Logger logger = LoggerFactory.getLogger(PropertyReader.class);
    private final Properties prop = new Properties();

    public PropertyReader() {
        String propFileName = System.getProperty("env")!=null? System.getProperty("env") : "config";
        ClassLoader loader = Thread.currentThread().getContextClassLoader();
        InputStream resourceStream = loader.getResourceAsStream( propFileName+".properties");
        loadProperties(resourceStream);
        logger.debug(propFileName+".properties - >Propertyfile is loaded correctly ");
    }

    private void loadProperties(InputStream stream) {
        try {
            if (stream != null) {
                prop.load(stream);
            }
        } catch (IOException e) {
            logger.error("Could not load user properties file", e);
        }
    }

    public String getProperty(String name) {
        String systemProperty = System.getProperty(name);
        if (!StringUtils.isEmpty(systemProperty)) {
            return systemProperty;
        } else {
            return prop.getProperty(name);
        }

    }
}
