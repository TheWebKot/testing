Feature: Cities game
  
  Scenario: game
    Given I have my cities game
    
  Scenario: check city
    Given I have my cities game
    When I entered "Барнаул" as city name
    And I check does this city exist
    Then The result should be true