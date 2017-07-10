package my_task.selenium_cucumber.bdd3;

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

public class InboxSortStepDefinitions {

    private ApplicationManager app;

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

    @And("^I sort emails by UnreadFirst$")
    public void SortUnreadFirstEmai() {
       app.inboxPage().chooseSortUnreadFirstEmai();
    }

    @When("^I sort Inbox emails by date desc$")
    public void defoltSortInbox() {
        app.inboxPage().chooseDefoltSortInbox();
    }

    @Then("^the previous email's date more than next email's date$")
    public void verifyDateList() {

    }

    @After
    public static void stopAllBrowsers() {
        WebDriverPool.DEFAULT.dismissAll();
    }

}

