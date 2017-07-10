package my_task.selenium_cucumber.keyboard;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.support.PageFactory;

public class Keys {
    public Keys(WebDriver webDriver) {
        PageFactory.initElements(webDriver, this);
    }

    @FindBy(css = "button#K84")
    public WebElement t;

    @FindBy(css = "button#K69")
    public WebElement e;

    @FindBy(css = "button#K83")
    public WebElement s;

    @FindBy(css = "button#K16")
    public WebElement shift;

    @FindBy(css = "button#K50")
    public WebElement two;

    @FindBy(css = "button#K190")
    public WebElement dot;


}
