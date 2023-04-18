package com.qulix.lab.openapifileserver.service;

//import org.eclipse.microprofile.config.Config;
//import org.eclipse.microprofile.config.ConfigProvider;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesService {
    private static final Properties properties;

    static {
        properties = new Properties();

        try {
//            ClassLoader classLoader = AppUtils.class.getClassLoader();
//            InputStream applicationPropertiesStream = classLoader.getResourceAsStream("application.properties");
//            applicationProperties.load(applicationPropertiesStream);
        } catch (Exception e) {
            // process the exception
        }
    }
    public String getRootPathWithUtilProp(){
        try(InputStream inputStream = new FileInputStream("src/main/resources/service.properties")){
            Properties properties = new Properties();
            properties.load(inputStream);
            return properties.getProperty("path.root");
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

    }
//    public String getRootPathWithRestEasyProp(){
//        Config config = ConfigProvider.getConfig();
//        return config.getValue("path.root", String.class);
//    }
//    public String getLogPathWithRestEasyProp(){
//        Config config = ConfigProvider.getConfig();
//        return config.getValue("path.log", String.class);
//    }
}
