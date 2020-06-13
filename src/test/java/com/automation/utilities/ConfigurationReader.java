package com.automation.utilities;

import java.io.FileInputStream;
import java.util.Properties;

public class ConfigurationReader {

    private static Properties properties;

    static {
        try{
            //location of properties file
            String path = System.getProperty("user.dir")+"/configuration.properties";
            //get that file as a stream
            FileInputStream fileInputStream = new FileInputStream(path);
            //create object of Properties class
            properties = new Properties();
            //load properties file into Properties object
            properties.load(fileInputStream);
            //close the input s
            fileInputStream.close();

        }catch (Exception e ){
            e.printStackTrace();
//            throw new RuntimeException("Failed to load properties file!");

        }

    }

    /**
     * This method returns property value from configuration.properties file
     * @param key property name
     * @return property value
     */
    public static String getProperty(String key){
        return properties.getProperty(key);

    }
}
