package utils;

import config.ConfigReader;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Properties;

public class AllureEnvironmentWriter {

    public static void writeEnvironmentInfo() {

        Properties properties = new Properties();

        properties.setProperty("Tester", "Salem Amortegui");
        properties.setProperty("Browser", ConfigReader.get("browser"));
        properties.setProperty("Framework", "Selenium + TestNG");
        properties.setProperty("Project", "Tiendo POS Automation");
        properties.setProperty("Base URL", ConfigReader.get("base.url"));
        properties.setProperty("Environment", "STG");
        properties.setProperty("OS", System.getProperty("os.name"));
        properties.setProperty("Java Version", System.getProperty("java.version"));
        properties.setProperty("User", System.getProperty("user.name"));

        try {

            File resultsDir = new File("target/allure-results");

            if (!resultsDir.exists()) {
                resultsDir.mkdirs();
            }

            FileWriter writer = new FileWriter(resultsDir + "/environment.properties");
            properties.store(writer, "Allure Environment");
            writer.close();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}