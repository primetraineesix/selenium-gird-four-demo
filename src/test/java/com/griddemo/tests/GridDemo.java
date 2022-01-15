package com.griddemo.tests;

import com.griddemo.base.Base;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterMethod;
import org.testng.annotations.BeforeMethod;
import org.testng.annotations.Parameters;
import org.testng.annotations.Test;

/**
 * Created by Jay Vaghani
 */
public class GridDemo extends Base {

    public WebDriver driver;

    @BeforeMethod
    @Parameters("browser")
    public void setUp(String browser) {
        driver = initializeBrowser(browser);
    }

    @Test
    public void testOne() {
        driver.get("https://demo.nopcommerce.com/");
        System.out.println("Page title is: " + driver.getTitle());
    }

    @AfterMethod
    public void tearDown() {
        driver.quit();
    }
}
