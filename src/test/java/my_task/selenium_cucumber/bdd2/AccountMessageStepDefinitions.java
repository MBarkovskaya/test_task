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
    private String error;

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
        app.gmailPage().typeEmail(login);
        app.gmailPage().nextToPassword();
        app.gmailPage().typePassword(password);
        app.gmailPage().nextToAccount();
    }

    @When("^I send new email to (.+)$")
    public void sendEmail(String recipient) {
        app.inboxPage().composeEmail.click();
        app.inboxPage().typeRecipient(recipient);
        app.inboxPage().openVirtualKeyboard();
        long now = System.currentTimeMillis();
        String timeMillis = String.valueOf(now);
        //We have unique subject name "test" with parameter timeMillis
        subjectName = app.inboxPage().typeSubject(timeMillis);
        app.inboxPage().typeMessage();
        app.inboxPage().sendEmail();
        app.inboxPage().closeVirtualKeyboard();
        //We get the email's subject name from the sent email
        sentSubjectName = app.inboxPage().sentMailSubject();
    }

    @Then("^the sent email's subject's name is equal to the entered email's subject's name$")
    public void verifySendLetter() {

        Assert.assertEquals(sentSubjectName, subjectName);
    }

    @When("^I send new email with unrecognized recipient (.+)$")
    public void sendEmailWithUnrecognisedRecipient(String recipient) {
        app.inboxPage().composeEmail.click();
        app.inboxPage().typeRecipient(recipient);
        app.inboxPage().openVirtualKeyboard();
        long now = System.currentTimeMillis();
        String timeMillis = String.valueOf(now);
        app.inboxPage().typeSubject(timeMillis);
        app.inboxPage().typeMessage();
        //We get the message's text about error
        error = app.inboxPage().sendEmailWithUnrecRecepient();
    }

    @Then("^I get an messageEn (.+) or messageRu (.+) about error$")
    public void verifyUnrecognisedRecipient(String messageEn, String messageRu) {
        //Verify that the messge's text contains word "Error"
        Assert.assertTrue(messageEn.equals(error) ^ messageRu.equals(error));
    }

    @After
    public static void stopAllBrowsers() {
        WebDriverPool.DEFAULT.dismissAll();
    }

}
