import cucumber.api.CucumberOptions;
import cucumber.api.SnippetType;
import cucumber.api.junit.Cucumber;
import org.junit.runner.RunWith;

@RunWith(Cucumber.class)

@CucumberOptions(features = {"classpath:features"},
        glue = {"classpath:tests"},
        tags = "@withdrawal",
        snippets = SnippetType.CAMELCASE,
        plugin = "pretty"
)
public class Runner {
}