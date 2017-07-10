package my_task.selenium_cucumber.Pages;

import org.junit.Assert;
import org.openqa.selenium.*;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;

import java.time.Duration;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.openqa.selenium.support.ui.ExpectedConditions.alertIsPresent;
import static org.openqa.selenium.support.ui.ExpectedConditions.presenceOfAllElementsLocatedBy;
import static org.openqa.selenium.support.ui.ExpectedConditions.visibilityOfElementLocated;

public class InboxPage extends Page {

    public InboxPage(WebDriver driver) {
        super(driver);
    }

    @FindBy(css = "div.T-I.J-J5-Ji.T-I-KE.L3")
    public WebElement composeEmail;

    public String usersAccount() {
        String textContent = "";
        try {
            WebElement element = wait.until((WebDriver d) -> d.findElement(By.cssSelector("div.akh.J-J5-Ji.J-JN-I")));
            Assert.assertNotNull(element);
            textContent = element.getAttribute("textContent");
        } catch (Exception e) {
            Assert.fail("Unable to locate element");
        }
        return textContent;
    }

    public void sendEmail() {
        wait.until(visibilityOfElementLocated(By.xpath(".//td[@class='gU Up']"))).click();
    }

    public String sendEmailWithUnrecRecepient() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        wait.until(visibilityOfElementLocated(By.xpath(".//td[@class='gU Up']"))).click();
        try {
            Thread.sleep(3000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        WebElement error = wait.until(visibilityOfElementLocated(By.cssSelector("div.Kj-JD span.Kj-JD-K7-K0")));
        return error.getAttribute("textContent");
    }

    public void typeRecipient(String recipient) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }

        try {
            WebElement element = wait.until((WebDriver d) -> d.findElement(By.name("to")));
            Assert.assertNotNull(element);
            element.click();
            element.sendKeys(recipient, Keys.ENTER, Keys.TAB, Keys.TAB, Keys.TAB);
        } catch (Exception e) {
            Assert.fail("Unable to locate element");
        }
    }

    public String typeSubject(String timeMillis) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        driver.findElement(By.name("subjectbox")).click();
        Actions actions = new Actions(driver);
        actions.pause(Duration.ofMillis(100)).moveToElement(driver.findElement(By.cssSelector("button#K84"))).click()
                .moveToElement(driver.findElement(By.cssSelector("button#K69"))).click()
                .moveToElement(driver.findElement(By.cssSelector("button#K83"))).click()
                .moveToElement(driver.findElement(By.cssSelector("button#K84"))).click()
                .sendKeys(timeMillis).sendKeys(Keys.TAB).build().perform();

        return "test" + timeMillis;
    }

    public void typeMessage() {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.cssSelector("button#K84"))).click()
                .moveToElement(driver.findElement(By.cssSelector("button#K69"))).click()
                .moveToElement(driver.findElement(By.cssSelector("button#K83"))).click()
                .moveToElement(driver.findElement(By.cssSelector("button#K84"))).click().build().perform();
    }

    public void openVirtualKeyboard() {
        wait.until(visibilityOfElementLocated(By.cssSelector("#itamenu a.d-Na-JX-I.d-Na-M7-JX.d-Na-axY.d-Na-Gs"))).click();
    }

    public void closeVirtualKeyboard() {
        driver.findElement(By.cssSelector("#kbd > div:nth-child(1) > div.RK-QJ-aLq > div.RK-QJ-Jk-Pl.RK-Qq-UT")).click();
    }

    public String sentMailSubject() {
        // new comment
        Actions actions = new Actions(driver);
        actions.moveToElement(driver.findElement(By.cssSelector("span.nU a.J-Ke"))).sendKeys(Keys.LEFT, Keys.DOWN, Keys.DOWN, Keys.ENTER)
                .release().perform();
        List<WebElement> listTableEmails =
                wait.until(presenceOfAllElementsLocatedBy(By.cssSelector("div.BltHke.nH.oy8Mbf div.Cp:first-child table")));
        WebElement tablesFirstEmail = listTableEmails.get(0).findElements(By.cssSelector("tr")).get(0);
        tablesFirstEmail.click();
        WebElement sentEmailSubject = wait.until(visibilityOfElementLocated(By.cssSelector("h2.hP")));
        return  sentEmailSubject.getAttribute("textContent");
    }


    public void typeUnrecRecipient(String recipient) {
        try {
            Thread.sleep(2000);
        } catch (InterruptedException ex) {
            ex.printStackTrace();
        }
        try {
            WebElement element = wait.until((WebDriver d) -> d.findElement(By.name("to")));
            Assert.assertNotNull(element);
            element.click();
            element.sendKeys(recipient, Keys.ENTER, Keys.TAB, Keys.TAB, Keys.TAB);
        } catch (Exception e) {
            Assert.fail("Unable to locate element");
        }
    }

}


//    $("a[href='https://mail.google.com/mail/u/0/?shva=1&zx=hz60tsku5x5g#inbox']") inbox
//    $("img.afM") кнопка
