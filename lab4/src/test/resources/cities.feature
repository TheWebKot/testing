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
      | ЯлтА      | а |
      | Тюмень    | н |
      | Агайры    | р |
      | Ажбай     | а |
    
  Scenario Outline: follow
    Given I have my cities game
    When Other player entered "<other>" as city name
    And I entered "<name>" as city name
    And I want to check does my answer valid 
    Then The result should be <result>
    
    Examples:
      | other | name | result |
      | Новосибирск | Кемерово | true |
      | Барнаул     | Москва   | false |
      | Тюмень      | Нижневартовск | true |
    
  Scenario Outline: already used
    Given I have my cities game
    When Current player answered "<name>"
    Then Should not be thrown exception
    And Current player answered "<other>"
    Then Should not be thrown exception
    And Current player answered "<check>"
    Then Should be thrown exception with message contains "answered"
    
    Examples:
      | name | other | check |
      | Барнаул | Бийск | Барнаул |
      | Москва  | Новосибирск | Новосибирск |
    
  Scenario: valid sequence
    Given I have my cities game
    When Current player answered "Барнаул"
    Then Should not be thrown exception
    And Current player answered "Лосево"
    Then Should not be thrown exception
    And Current player answered "Омск"
    Then Should not be thrown exception
    
    