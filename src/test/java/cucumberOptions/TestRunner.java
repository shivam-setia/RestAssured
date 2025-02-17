package cucumberOptions;

import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(features = "src/test/java/FeatureFile",plugin="json:target/jsonReports/cucumber-reports.json",glue = {"StepDefinition"},tags="@deletePlace")
public class TestRunner {
//,tags= "@deletePlace"
}
