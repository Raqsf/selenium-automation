package com.dice;

import com.dice.base.BaseTest;
import org.testng.annotations.Test;

public class FirstTest extends BaseTest {

    @Test
    public void firstTestMethod() {
        // Open dice.com
        driver.get("http://www.dice.com");
        System.out.println("Dice opened. Test passed");
    }
}
