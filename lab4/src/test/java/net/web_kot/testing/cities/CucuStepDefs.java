package net.web_kot.testing.cities;

import cucumber.api.java8.En;

@SuppressWarnings("unused")
public class CucuStepDefs implements En {
    
    public CucuStepDefs() {
        Given("^I have my cities game$", Cities::new);
    }
    
}
