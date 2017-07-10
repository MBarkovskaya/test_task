package my_task.selenium_cucumber.Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

public class GooglePage extends Page {
    public GooglePage(WebDriver driver) {
        super(driver);
    }

    public void open() {
        driver.get("https://www.google.ru/?gws_rd=ssl");
    }

    public void chooseGmail() {
        try {
            WebElement element = wait.until((WebDriver d) -> d.findElement(By.cssSelector("a[href='https://mail.google.com/mail/?tab=wm']")));
            Assert.assertNotNull(element);
            element.click();
        } catch (Exception e) {
            Assert.fail("Unable to locate element");
        }
    }

}
