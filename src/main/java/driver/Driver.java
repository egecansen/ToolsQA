package driver;

import org.openqa.selenium.Dimension;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.safari.SafariDriver;

import java.io.File;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class Driver {

    public enum BrowserType {
        chrome, safari, firefox
    }
    public static WebDriver driver;

    public static void setup(BrowserType browserType) {
        Properties properties = LoadProperties.initialize();
        boolean headless = Boolean.parseBoolean(properties.getProperty("headless","false"));
        int frameWidth = Integer.parseInt(properties.getProperty("frame-width"));
        int frameHeight = Integer.parseInt(properties.getProperty("frame-height"));
        boolean maximise = Boolean.parseBoolean(properties.getProperty("maximise", "false"));

        String downloadsDirectoryPath = new File("src/test/resources/files/downloads").getAbsolutePath();
        String downloadsDirectory = properties.getProperty("downloadsPath", downloadsDirectoryPath);

            switch (browserType) {
                case chrome:
                    ChromeOptions options = new ChromeOptions();
                    Map<String, Object> prefs = new HashMap<>();
                    if (headless)
                        options.addArguments("--headless=new");
                    prefs.put("download.default_directory", downloadsDirectory);
                    options.setExperimentalOption("prefs", prefs);

                    driver = new ChromeDriver(options);
                    break;
                case firefox:
                    driver = new FirefoxDriver();
                    break;
                case safari:
                    driver = new SafariDriver();
                    break;

            }
            driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(30));
            driver.manage().window().setSize(new Dimension(frameWidth, frameHeight));
            if (maximise) driver.manage().window().maximize();
        }

        public static void quitDriver() {
            driver.quit();

    }
}
