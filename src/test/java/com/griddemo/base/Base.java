package com.griddemo.base;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;

import static org.openqa.selenium.remote.BrowserType.*;

/**
 * Created by Jay Vaghani
 */
public class Base {

    public WebDriver initializeBrowser(String browserName) {
        WebDriver driver = null;
        DesiredCapabilities dc = new DesiredCapabilities();
        if (browserName.equalsIgnoreCase("chrome")) {
            dc.setBrowserName(CHROME);
            System.out.println("#########TEST CASE EXECUTION STARTED ON ==> " + browserName);
        } else if (browserName.equalsIgnoreCase("firefox")) {
            dc.setBrowserName(FIREFOX);
            System.out.println("#########TEST CASE EXECUTION STARTED ON ==> " + browserName);
        } else if (browserName.equalsIgnoreCase("edge")) {
            dc.setBrowserName(EDGE);
            System.out.println("#########TEST CASE EXECUTION STARTED ON ==> " + browserName);
        } else if (browserName.equalsIgnoreCase("ie")) {
            dc.setBrowserName(IE);
            System.out.println("#########TEST CASE EXECUTION STARTED ON ==> " + browserName);
        } else if (browserName.equalsIgnoreCase("opera")) {
            dc.setBrowserName(OPERA);
            System.out.println("#########TEST CASE EXECUTION STARTED ON ==> " + browserName);
        } else if (browserName.equalsIgnoreCase("safari")) {
            dc.setBrowserName(SAFARI);
            System.out.println("#########TEST CASE EXECUTION STARTED ON ==> " + browserName);
        }
        try {
            return new RemoteWebDriver(new URL("http://localhost:4445"), dc);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return driver;
    }
}
