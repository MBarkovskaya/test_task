package my_task.selenium_cucumber.bdd;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/resources/bdd/account.feature",
        plugin = {"pretty", "json:build/cucumber-report/bdd/cucumber.json", "html:build/cucumber-report/cucumber.html"})

public class AccountRunner {
}
