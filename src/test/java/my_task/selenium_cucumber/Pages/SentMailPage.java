package my_task.selenium_cucumber.Pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.interactions.Actions;

public class SentMailPage extends Page {
    public SentMailPage(WebDriver driver) {
        super(driver);
    }

//    public void open() {
//        driver.get("https://mail.google.com/mail/u/0/?shva=1&zx=hz60tsku5x5g#sent");
//    }

    public void goToInbox() {
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.cssSelector("span.nU a.J-Ke")))
                .sendKeys(Keys.LEFT, Keys.UP, Keys.UP, Keys.ENTER).perform();
    }
}
