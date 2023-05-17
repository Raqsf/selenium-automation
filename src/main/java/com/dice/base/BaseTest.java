package com.dice.base;

import org.apache.log4j.Logger;
import org.openqa.selenium.WebDriver;
import org.testng.ITestContext;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeClass;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;

public class BaseTest {

    protected WebDriver driver;
    protected Logger log;

    @BeforeClass(alwaysRun = true)
    protected void setUpContext(ITestContext ctx) {
        String testName = ctx.getCurrentXmlTest().getName();
        log = Logger.getLogger(testName);
    }

    @Parameters({ "browser" })
    @BeforeMethod(alwaysRun = true)
    protected void methodSetUp(String browser) {
        log.info("Method Set Up");
        driver = BrowserFactory.getDriver(browser);
    }

    @AfterMethod(alwaysRun = true)
    protected void methodTearDown() {
        log.info("Method Tear Down");
        driver.quit();
    }
}
