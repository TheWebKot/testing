Feature: Cities game
  
  Scenario: game
    Given I have my cities game
    
  Scenario Outline: city exists
    Given I have my cities game
    When I entered "<name>" as city name
    And I check does this city exist
    Then The result should be <result>
    
    Examples:
      | name    | result |
      | Барнаул | true   |
      | Москва  | true   |
      | Фывыпро | false  |
      | НовоСибирск | true |
    
  Scenario Outline: last character
    Given I have my cities game
    When I entered "<name>" as city name
    Then The last character should be <char>
    
    Examples:
      | name | char |
      | Волгоград | д |
    
    