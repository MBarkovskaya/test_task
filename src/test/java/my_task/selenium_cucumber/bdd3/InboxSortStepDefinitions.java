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
    int cnt, result;

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
        cnt = app.inboxPage().dateList().size();
        for (int i = 0; i < cnt - 1; i++) {
            result = app.inboxPage().dateList().get(i).compareTo(app.inboxPage().dateList().get(i + 1));
            Assert.assertTrue("the previous email's date must be more than next email's date", result > 0);
        }
    }

    @After
    public static void stopAllBrowsers() {
        WebDriverPool.DEFAULT.dismissAll();
    }

}

