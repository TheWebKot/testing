package net.web_kot.testing.cities;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import org.junit.jupiter.api.Assertions;

@SuppressWarnings("unused")
public class CucuStepDefs implements En {
    
    private Cities instance;
    private String cityName;
    private boolean result;
    
    public CucuStepDefs() {
        Given("^I have my cities game$", () -> 
                instance = new Cities()
        );
        
        When("^I entered \"([^\"]*)\" as city name$", (String name) -> 
                cityName = name
        );
        
        And("^I check does this city exist$", () -> 
                result = instance.cityExists(cityName)
        );
        
        Then("^The result should be ([^\"]*)$", (String result) -> 
                Assertions.assertEquals(this.result, Boolean.parseBoolean(result))
        );
        
        
    }
    
}
