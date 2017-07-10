package my_task.selenium_cucumber.bdd2;

import cucumber.api.java.After;
import cucumber.api.java.Before;
import cucumber.api.java.en.And;
import cucumber.api.java.en.Given;
import cucumber.api.java.en.Then;
import cucumber.api.java.en.When;
import my_task.selenium_cucumber.appmanager.ApplicationManager;
import org.junit.Assert;
import org.openqa.selenium.remote.BrowserType;
import ru.stqa.selenium.factory.WebDriverPool;

public class AccountMessageStepDefinitions {
    private ApplicationManager app;
    private String sentSubjectName, subjectName;

    @Before
    public void init() {
        app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));
        app.init();
    }

    @Given("^I am on the Google search page, choose Gmail reference$")
    public void goToGmail() {
        app.googlePage().open();
        app.googlePage().chooseGmail();
    }

    @And("^I enter user's login (.+), password (.+)$")
    public void loginAccount(String login, String password) {
        app.gmailPage().typeEmail("extraordinarypetrsidorov@gmail.com");
        app.gmailPage().nextToPassword();
        app.gmailPage().typePassword("petrsidorovextraordinary1974");
        app.gmailPage().nextToAccount();
    }

    @When("^I send new email to (.+)$")
    public void sendEmail(String recipient) {
        app.inboxPage().openVirtualKeyboard();
        app.inboxPage().composeEmail.click();
        app.inboxPage().typeRecipient(recipient);
        long now = System.currentTimeMillis();
        String timeMillis = String.valueOf(now);
        subjectName = app.inboxPage().typeSubject(timeMillis);
        app.inboxPage().typeMessage();
        app.inboxPage().sendEmail();
        app.inboxPage().closeVirtualKeyboard();
        sentSubjectName = app.inboxPage().sentMailSubject();
    }

    @Then("^the sent email's subject's name is equal to the entered email's subject's name$")
    public void verifySendLetter() {
        Assert.assertEquals(sentSubjectName, subjectName);
    }

    @When("^I send new letter to (.+)$")
    public void sendLetterWrong(String recipient) {

    }

    @Then("^I get a message (.+)$")
    public void verifyWrongLetter(String message) {

    }

    @After
    public static void stopAllBrowsers() {
        WebDriverPool.DEFAULT.dismissAll();
    }
}
