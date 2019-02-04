@task
Feature: testing webservice task (with https://itnext.io/using-json-server-to-create-a-restful-server-in-5-seconds-78b85ccf832b
  web site help) json-server --watch db.json

  Scenario Outline: test for posts
    Given I am opening localhost posts and get OK status
    When I am opening localhost posts with not correct: "<data>" and get NOT FOUND status
    Then I am trying to test POST method in posts and get CREATED status
#    And
  Examples:
    |data|
    |1234|

  Scenario Outline: test for comments
    Given I am opening localhost comments and get OK status
    When I am opening localhost comments with not correct: "<data>" and get NOT FOUND status
    Then I am trying to test POST method in comments and get CREATED status
#    And
  Examples:
    |data|
    |5678|

  Scenario Outline: test for profile
    Given I am opening localhost profile and get OK status
    When I am opening localhost profile with not correct: "<data>" and get NOT FOUND status
    Then I am trying to create one more variable and get CREATED status
#    And
  Examples:
    |data|
    |4321|