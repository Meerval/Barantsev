package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.edge.EdgeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.remote.BrowserType;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;
import java.util.concurrent.TimeUnit;

public class ApplicationManager {
    private final Properties properties;
    private final String browser;
    private WebDriver driver;
    private RegistrationHelper registrationHelper;

    public ApplicationManager(String browser) {
        this.browser = browser;

        properties = new Properties();
    }

    public void init() throws IOException {
        String target = System.getProperty("target", "local");
        properties.load(new FileReader(String.format("src/test/resources" +
                "/%s.properties", target)));
    }

    public HttpSession newSession() {
        return new HttpSession(this);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

    public RegistrationHelper registration() {
        if (registrationHelper == null) {
            registrationHelper = new RegistrationHelper(this);
        }
        return registrationHelper;
    }

    public WebDriver getDriver() {
        if (driver == null) {
            switch (browser) {
                case BrowserType.GOOGLECHROME:
                    driver = new ChromeDriver();
                    break;
                case BrowserType.FIREFOX:
                    driver = new FirefoxDriver();
                    break;
                case BrowserType.EDGE:
                    System.setProperty("webdriver.edge.driver",
                            "C:/Tools/msedgedriver.exe");
                    driver = new EdgeDriver();
                    break;
            }
            driver.manage().timeouts().implicitlyWait(0, TimeUnit.SECONDS);
            driver.get(properties.getProperty("web.baseURL"));
            driver.manage().window().maximize();
        }
        return driver;
    }

    public void stop() {
        if (driver != null) {
            driver.quit();
        }
    }
}
