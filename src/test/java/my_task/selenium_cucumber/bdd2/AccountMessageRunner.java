package my_task.selenium_cucumber.bdd2;

import cucumber.api.CucumberOptions;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "classpath:bdd2", plugin = {"pretty", "json:build/cucumber-report/cucumber.json",
        "html:build/cucumber-report/cucumber.html"})
public class AccountMessageRunner {
}

