package testRunner;

import org.junit.runner.RunWith;
import io.cucumber.junit.CucumberOptions;
import io.cucumber.junit.Cucumber;



@RunWith(Cucumber.class)
@CucumberOptions
(
	features=".//Features/",
		glue={"stepDefinitions"},
		dryRun=false,
		monochrome=true,
		plugin = { "pretty","junit:target/JUnitReports/Report.xml",
						"json:target/JSONReports/report.json", "html:target/HtmlReports",
			 "html:test-output"},
	tags = "@sanity" 
	
)


public class TestRun {

}
