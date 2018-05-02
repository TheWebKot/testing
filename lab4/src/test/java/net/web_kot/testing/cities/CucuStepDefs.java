package net.web_kot.testing.cities;

import cucumber.api.PendingException;
import cucumber.api.java8.En;
import org.junit.jupiter.api.Assertions;

@SuppressWarnings("unused")
public class CucuStepDefs implements En {
    
    private Cities instance;
    private String cityName, otherName;
    private boolean result;
    
    private Exception exception = null;
    
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
                Assertions.assertEquals(Boolean.parseBoolean(result), this.result)
        );
        
        Then("^The last character should be ([^\"]*)$", (String chr) -> 
                Assertions.assertEquals(chr.charAt(0), instance.getLastCharacter(cityName))
        );
        
        When("^Other player entered \"([^\"]*)\" as city name$", (String name) -> 
                otherName = name
        );
        
        And("^I want to check does my answer valid$", () ->
                result = instance.isValidAnswerAfter(otherName, cityName)
        );

        When("^Current player answered \"([^\"]*)\"$", (String city) -> {
            exception = null;
            try {
                instance.answer(city);
            } catch(Exception e) {
                exception = e;
            }
        });
        
        Then("^Should be thrown exception with message contains \"([^\"]*)\"$", (String message) -> {
            Assertions.assertNotNull(exception);
            Assertions.assertTrue(exception.getMessage().contains(message));
        });
        
        Then("^Should not be thrown exception$", () -> {
            Assertions.assertNull(exception);
        });

    }
    
}
