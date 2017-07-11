package my_task.selenium_cucumber.Pages;

import org.junit.Assert;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import static org.openqa.selenium.support.ui.ExpectedConditions.*;

public class GmailPage extends Page {
    private WebElement buttonNext;

    public GmailPage(WebDriver driver) {
        super(driver);
    }

    public void typeEmail(String email) {
        try {
            WebElement element = wait.until((WebDriver d) -> d.findElement(By.cssSelector("input#identifierId")));
            Assert.assertNotNull(element);
            element.sendKeys(email);
        } catch (Exception e) {
            Assert.fail("Unable to locate element");
        }
    }

    public void typePassword(String password) {
        try {
            WebElement element = wait.until((WebDriver d) -> d.findElement(By.cssSelector("input.whsOnd.zHQkBf")));
            Assert.assertNotNull(element);
            element.sendKeys(password);
        } catch (Exception e) {
            Assert.fail("Unable to locate element");
        }
    }

    public void nextToPassword() {
        buttonNext = wait.until(presenceOfElementLocated(By.cssSelector("content.CwaK9")));
        buttonNext.click();
        wait.until(stalenessOf(buttonNext));
        wait.until(visibilityOfElementLocated(By.cssSelector("div#profileIdentifier")));
    }

    public void nextToAccount() {
        buttonNext = wait.until(presenceOfElementLocated(By.cssSelector("content.CwaK9")));
        buttonNext.click();
        wait.until(stalenessOf(buttonNext));
        wait.until(visibilityOfElementLocated(By.cssSelector("div.T-I.J-J5-Ji.T-I-KE.L3")));

    }

    public String googleMessage() {
        return wait.until(presenceOfElementLocated(By.cssSelector("div.IMH1vc.lUHSR"))).getAttribute("textContent");
    }
}
