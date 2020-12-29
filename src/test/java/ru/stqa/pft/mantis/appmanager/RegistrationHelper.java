package ru.stqa.pft.mantis.appmanager;

import org.openqa.selenium.WebDriver;

public class RegistrationHelper {
    private final ApplicationManager am;
    private WebDriver driver;

    public RegistrationHelper(ApplicationManager am) {
        this.am = am;
        driver = am.getDriver();
    }

    public void start(String username, String email) {
        driver.get(am.getProperty("web.baseURL") + "/signup_page.php");
    }
}
