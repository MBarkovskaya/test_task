package my_task.selenium_cucumber.bdd;

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

import static org.hamcrest.CoreMatchers.equalTo;
import static org.hamcrest.MatcherAssert.assertThat;

public class AccountStepDefinitions {

    private ApplicationManager app;

    @Before
    public void init() {
        app = new ApplicationManager(System.getProperty("browser", BrowserType.FIREFOX));
        app.init();
    }

    @Given("^I am on the Google search page$")
    public void visitGoogle() {
        app.googlePage().open();
    }

    @And("^I choose Gmail$")
    public void chooseGmail() {
        app.googlePage().chooseGmail();
    }

    @When("^I log in witn email (.+), password (.+)$")
    public void loginAccount(String email, String password) {
        app.gmailPage().typeEmail(email);
        app.gmailPage().nextToPassword();
        app.gmailPage().typePassword(password);
        app.gmailPage().nextToAccount();
    }

    @When("^I log in witn email (.+), incorrect password (.+)$")
    public void loginWithIncorrectPassword(String email, String password) {
        app.gmailPage().typeEmail(email);
        app.gmailPage().nextToPassword();
        app.gmailPage().typePassword(password);
    }

    @Then("^user's enter was successful, the page contains gmailButton (.+)$")
    public void verifyAccountLogin(String gmailButton) {
        String accountButton = app.inboxPage().usersAccount();
        // Verify that the Gmail button is on the opened page
        assertThat(gmailButton, equalTo(accountButton));
    }

    @Then("^user's enter was unsuccessful, the page contain message (.+)$")
    public void verifyAccountIncorrectLogin(String message) {
        String questionText = app.gmailPage().googleMessage();
        //Verify that after trying press button NEXT with incorrect password we"ll send Error message
        Assert.assertTrue(message.equals(questionText));
    }

    @After
    public static void stopAllBrowsers() {
        WebDriverPool.DEFAULT.dismissAll();
    }

}
