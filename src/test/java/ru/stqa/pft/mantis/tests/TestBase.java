package ru.stqa.pft.mantis.tests;

import org.openqa.selenium.remote.BrowserType;
import org.testng.annotations.AfterSuite;
import org.testng.annotations.BeforeSuite;
import ru.stqa.pft.mantis.appmanager.ApplicationManager;


public class TestBase {

    protected static final ApplicationManager am =
            new ApplicationManager(System.getProperty("browser",
                    BrowserType.GOOGLECHROME));

    @BeforeSuite
    public void setUp() throws Exception {
        am.init();
    }

    @AfterSuite(alwaysRun = true)
    public void tearDown() {
        am.stop();
    }
}
