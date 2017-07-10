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
    private String error, errorExpl;

    @Before
    public void init() {
        app = new ApplicationManager(System.getProperty("browser", BrowserType.CHROME));
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
        app.inboxPage().composeEmail.click();
        app.inboxPage().typeRecipient(recipient);
        app.inboxPage().openVirtualKeyboard();
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

    @When("^I send new email with unrecognized recipient (.+)$")
    public void sendEmailWithUnrecognisedRecipient(String recipient) {
        app.inboxPage().composeEmail.click();
        app.inboxPage().typeUnrecRecipient(recipient);
        app.inboxPage().openVirtualKeyboard();
        long now = System.currentTimeMillis();
        String timeMillis = String.valueOf(now);
        app.inboxPage().typeSubject(timeMillis);
        app.inboxPage().typeMessage();
        error = app.inboxPage().sendEmailWithUnrecRecepient();
    }

    @Then("^I get an enMessage (.+) or ruMessage (.+) about error$")
    public void verifyUnrecognisedRecipient(String enMessage, String ruMessage) {
        Assert.assertTrue(enMessage.equals(error) ^ ruMessage.equals(error));
    }

    @After
    public static void stopAllBrowsers() {
        WebDriverPool.DEFAULT.dismissAll();
    }

}
