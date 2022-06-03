import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/test/resources/features",
        plugin = {"pretty","html:target/cucumber-pretty",
                "json:targe/CucumberTestReport.json",
                "rerun:target/rerun.txt" },
        monochrome = true,
        glue = {
                "com.toptal.stepdefinitions"
        }
)
public class TestRunner {}

