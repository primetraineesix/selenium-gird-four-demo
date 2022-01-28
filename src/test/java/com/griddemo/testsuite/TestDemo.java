package com.griddemo.testsuite;

import com.griddemo.testbase.TestBase;
import org.testng.annotations.Test;

/**
 * Created by Jay Vaghani
 */
public class TestDemo extends TestBase {

    @Test
    public void testOne() {
        driver.get("https://demo.nopcommerce.com/");
        System.out.println("Page title is: " + driver.getTitle());
    }
}
