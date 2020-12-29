package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;

import java.io.File;
import java.io.IOException;


public class TestBase {

    protected static final ApplicationManager am =
            new ApplicationManager(System.getProperty("browser",
                    BrowserType.GOOGLECHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        am.init();
        am.ftp().upload(new File("src/test/resources/config_inc.php"),
                "config_inc.php", "config_inc.php.bak");
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() throws IOException {
        am.ftp().restore("config_inc.php.bak", "config_inc.php");
        am.stop();
    }
}
