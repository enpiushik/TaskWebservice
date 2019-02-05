package runners;

        import cucumber.api.CucumberOptions;
        import cucumber.api.junit.Cucumber;
        import org.junit.runner.RunWith;

@RunWith(Cucumber.class)
@CucumberOptions(
        features = "src/main/resources/features/",
        plugin = {"pretty",
        "html:target/Report"},
        tags = {"@task, @posts"},
        glue = {"stepDefinitions"}
)
public class CucumberRunner {
}
