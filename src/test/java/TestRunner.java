import driver.LoadProperties;
import io.cucumber.junit.Cucumber;
import io.cucumber.junit.CucumberOptions;
import org.junit.BeforeClass;
import org.junit.runner.RunWith;

/**
 * Unit test for simple App.
 */

@RunWith(Cucumber.class)
@CucumberOptions(
        features = {"src/test/java/features"},
        plugin = {"json:target/reports/Cucumber.json", "html:target/reports/Cucumber.html"},
        glue = {"steps"},
        publish = true
)
public class TestRunner {
}
