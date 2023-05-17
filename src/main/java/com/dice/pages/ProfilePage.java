package com.dice.pages;

import com.dice.base.BasePageObject;
import org.apache.log4j.Logger;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class ProfilePage extends BasePageObject<ProfilePage> {

    private By editProfileLink = By.xpath("//a[@href='/dashboard' and text()='Edit']");
    private By profileLink = By.xpath("//a[@id='profileLink']");
    private By profileContactNameText = By.xpath("//h3[@class='personal-info name-section']");

    public ProfilePage(WebDriver driver, Logger log) {
        super(driver, log);
    }

    public void waitForProfilePageToLoad() {
        log.info("Waiting for profile page to load");
        waitForVisibilityOf(editProfileLink);
        waitForVisibilityOf(profileLink, 10);
    }

    public boolean isCorrectProfileLoaded(String profileName) {
        return getText(profileContactNameText).equals(profileName);
    }
}
