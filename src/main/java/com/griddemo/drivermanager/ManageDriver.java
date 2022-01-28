package com.griddemo.drivermanager;

import com.griddemo.propertyreader.PropertyReader;
import io.github.bonigarcia.wdm.WebDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.ie.InternetExplorerDriver;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.Duration;
import java.util.HashMap;
import java.util.Map;

import static org.openqa.selenium.remote.BrowserType.CHROME;

public class ManageDriver {

    public static WebDriver driver;
    public String baseUrl = PropertyReader.getInstance().getProperty("baseUrl");
    public String runOn = PropertyReader.getInstance().getProperty("runOn");
    public String sauceUrl = PropertyReader.getInstance().getProperty("sauceUrl");
    public String gridUrl = PropertyReader.getInstance().getProperty("gridUrl");
    public String platformName = PropertyReader.getInstance().getProperty("platformName");
    public String browserVersion = PropertyReader.getInstance().getProperty("browserVersion");

    public void selectBrowser(String browser) {
        DesiredCapabilities dc = null;
        Map<String, Object> sauceOptions = new HashMap<>();
        sauceOptions.put("username", PropertyReader.getInstance().getProperty("sauce.username"));
        sauceOptions.put("accessKey", PropertyReader.getInstance().getProperty("sauce.accessKey"));
        if (browser.equalsIgnoreCase("chrome")) {
            if (runOn.equalsIgnoreCase("remote")) {
                dc = new DesiredCapabilities();
                dc.setBrowserName(CHROME);
                System.out.println("#########TEST STARTED ON GRID ==> On The Browser is " + browser);
                driver = getRemoteDriver(gridUrl, dc);
            } else if (runOn.equalsIgnoreCase("sauceLab")) {
                ChromeOptions options = new ChromeOptions();
                options.setPlatformName(platformName);
                options.setBrowserVersion(browserVersion);
                options.setCapability("sauce:options", sauceOptions);
                System.out.println("#########TEST STARTED ON SAUCE LAB ==> On The Browser is " + browser);
                driver = getRemoteDriverForSauceLab(sauceUrl, options);
            } else {
                WebDriverManager.chromedriver().setup();
                System.out.println("#########TEST STARTED ON LOCAL ==> On The Browser is " + browser);
                driver = new ChromeDriver();
            }
        } else if (browser.equalsIgnoreCase("firefox")) {
            WebDriverManager.firefoxdriver().setup();
            driver = new FirefoxDriver();
        } else if (browser.equalsIgnoreCase("ie")) {
            WebDriverManager.iedriver().setup();
            driver = new InternetExplorerDriver();
        } else {
            System.out.println("Wrong browser name");
        }
        driver.manage().window().maximize();
        driver.manage().timeouts().implicitlyWait(Duration.ofSeconds(Integer.parseInt(PropertyReader.getInstance()
                .getProperty("implicitlyWait"))));
        driver.get(baseUrl);
    }


    public void closeBrowser() {
        if (driver != null) {
            driver.quit();
        }
    }


    public WebDriver getRemoteDriver(String url, DesiredCapabilities dc) {
        try {
            return new RemoteWebDriver(new URL(url), dc);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public WebDriver getRemoteDriverForSauceLab(String url, ChromeOptions options) {
        try {
            return new RemoteWebDriver(new URL(url), options);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        return null;
    }
}
