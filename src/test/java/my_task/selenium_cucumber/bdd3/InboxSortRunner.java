package my_task.selenium_cucumber.bdd3;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:bdd3", plugin = {"pretty", "json:build/cucumber-report/cucumber.json",
        "html:build/cucumber-report/bdd3/cucumber.html"})
public class InboxSortRunner {
}
