package my_task.selenium_cucumber.keyboard;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

import java.time.Duration;

public class VirtualKeyboard extends Keys {
    private WebDriver webDriver;

    public VirtualKeyboard(WebDriver webDriver) {
        super(webDriver);
        this.webDriver = webDriver;
    }

    public VirtualKeyboard clickT() {
        t.click();
        return this;
    }

    public VirtualKeyboard clickE() {
        e.click();
        return this;
    }

    public VirtualKeyboard clickS() {
        s.click();
        return this;
    }

    public VirtualKeyboard clickDot() {
        dot.click();
        return this;
    }

    public VirtualKeyboard clickAt() {
        shift.click();

        new Actions(webDriver).moveToElement(shift).pause(Duration.ofMillis(100)).click()
                .moveToElement(two).pause(Duration.ofMillis(100)).click().build().perform();
        return this;
    }

    private Actions clickKey(WebElement key) {
        return new Actions(webDriver).moveToElement(key).pause(Duration.ofMillis(100)).click();
    }
}
