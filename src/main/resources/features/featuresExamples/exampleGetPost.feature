@exampleGetPost
Feature: test example for GET and POST methods
  Scenario:
    Given GET response from google
    When Trying to get OK status from swapi.co
    Then Trying to get NOT FOUND status from swapi.co
    And Trying to get OK status from reqres.in POST
