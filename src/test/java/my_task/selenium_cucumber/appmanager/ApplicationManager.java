package my_task.selenium_cucumber.appmanager;

import my_task.selenium_cucumber.Pages.GmailPage;
import my_task.selenium_cucumber.Pages.GooglePage;
import my_task.selenium_cucumber.Pages.InboxPage;
import org.junit.Assert;
import org.openqa.selenium.Platform;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeOptions;
import org.openqa.selenium.firefox.FirefoxOptions;
import org.openqa.selenium.remote.DesiredCapabilities;
import org.openqa.selenium.support.ui.WebDriverWait;
import ru.stqa.selenium.factory.WebDriverPool;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.MalformedURLException;
import java.net.URL;
import java.util.Properties;

import static org.openqa.selenium.remote.BrowserType.CHROME;
import static org.openqa.selenium.remote.BrowserType.FIREFOX;

public class ApplicationManager {
    public WebDriver driver;
    private String browser;
    protected final Properties properties;
    private GooglePage googlePage;
    private GmailPage gmailPage;
    private InboxPage inboxPage;

    public ApplicationManager(String browser) {
        this.browser = browser;
        properties = new Properties();
    }

    public GooglePage googlePage() {
        return googlePage;
    }

    public GmailPage gmailPage() {
        return gmailPage;
    }
    public InboxPage inboxPage() {
        return inboxPage;
    }

    public WebDriverWait createWait(int timeoutInSeconds) {
        return new WebDriverWait(driver, timeoutInSeconds);
    }

    public void init() {
        String target = System.getProperty("target", "local");
        try {
            properties.load(new FileReader(new File(String.format("src/test/resources/%s.properties", target))));
        } catch (IOException e) {
            Assert.fail("Unable to read properties: " + e);
        }

        if ("".equals(properties.getProperty("selenium.server"))) {
            switch (browser) {
                case FIREFOX:
                    FirefoxOptions firefoxOptions = new FirefoxOptions();
                    firefoxOptions.setLegacy(false);
                    firefoxOptions.setBinary("c:\\Program Files (x86)\\Mozilla Firefox\\firefox.exe");
                    DesiredCapabilities firefoxCaps = DesiredCapabilities.firefox();
                    firefoxCaps.setCapability(FirefoxOptions.FIREFOX_OPTIONS, firefoxOptions);
                    driver = WebDriverPool.DEFAULT.getDriver(firefoxCaps);
                    break;
                case CHROME:
                    ChromeOptions options = new ChromeOptions();
                    options.setBinary("C:\\Program Files (x86)\\Google\\Chrome\\Application\\chrome.exe");
                    options.addArguments("start-maximized");
                    DesiredCapabilities chromeCapabilities = DesiredCapabilities.chrome();
                    chromeCapabilities.setCapability(ChromeOptions.CAPABILITY, options);
                    driver = WebDriverPool.DEFAULT.getDriver(chromeCapabilities);
                    break;
            }
        } else {
            DesiredCapabilities capabilities = new DesiredCapabilities();
            capabilities.setBrowserName(browser);
            capabilities.setPlatform(Platform.fromString(System.getProperty("platform", "win8")));
            try {
                driver = WebDriverPool.DEFAULT.getDriver(new URL(properties.getProperty("selenium.server")), capabilities);
            } catch (MalformedURLException e) {
                Assert.fail("Unable to read property for selenium.server: " + e);
            }
        }

        googlePage = new GooglePage(driver);
        gmailPage = new GmailPage(driver);
        inboxPage = new InboxPage(driver);
    }

    public String getProperty(String key) {
        return properties.getProperty(key);
    }

}
