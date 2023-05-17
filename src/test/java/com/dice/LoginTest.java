package com.dice;

import com.dice.base.BaseTest;
import com.dice.base.CsvDataProvider;
import com.dice.pages.LoginPage;
import com.dice.pages.ProfilePage;
import java.util.Map;
import org.testng.Assert;
import org.testng.annotations.Test;

public class LoginTest extends BaseTest {

    @Test(priority = 1, groups = { "positive" })
    public void positiveLoginTest() {
        LoginPage loginPage = new LoginPage(driver, log);
        String expectedPageTitle = "Dashboard Home Feed | Dice.com";
        String correctProfileName = "Dmitry Shyshkin";

        // Open dice login page - https//:www.dice.com/dashboard/login
        loginPage.openLoginPage();

        // Fill up email and password
        loginPage.fillUpEmailAndPassword("dmitrysqa@gmail.com", "Automation2017");

        // Push Sign In button and wait for page profile to load
        ProfilePage profilePage = loginPage.pushSignInButton();
        profilePage.waitForProfilePageToLoad();

        // Verification
        //      Verify title of the page is correct - Dashboard Home Feed | Dice.com
        log.info("Verifications");
        String actualTitle = profilePage.getTitle();
        Assert.assertTrue(expectedPageTitle.equals(actualTitle),
                "Page title is not expected.\nExpected: " + expectedPageTitle + "\nActual: " + actualTitle);

        //      Verify correct name on profile page
        Assert.assertTrue(profilePage.isCorrectProfileLoaded(correctProfileName),"Profile name is not expected");
    }

    @Test(dataProvider = "CsvDataProvider", dataProviderClass = CsvDataProvider.class, priority = 2, groups = { "negative", "broken" })
    public void negativeLoginTest(Map<String, String> testData) {
        LoginPage loginPage = new LoginPage(driver, log);
        String testNumber = testData.get("no");
        String email = testData.get("email");
        String password = testData.get("password");
        String description = testData.get("description");
        String expectedErrorMessage = "Email and/or password incorrect";

        log.info("Test No #" + testNumber + " for " + description
                + " Where\nEmail: " + email + "\nPassowrd: " + password);

        // Open dice login page - https//:www.dice.com/dashboard/login
        loginPage.openLoginPage();

        // Fill up email and password
        loginPage.fillUpEmailAndPassword(email, password);

        // Push Sign In button
        loginPage.pushSignInButton();

        String errorMessage = loginPage.getLoginErrorMessage();

        Assert.assertTrue(errorMessage.contains(expectedErrorMessage),
                "Error message is not expected.\nExpected: " + expectedErrorMessage + "\nActual: " + errorMessage);

    }
}
