@test
@task
Feature: tests for posts, comments, profile with GET, POST, body, status codes 200, 201, 404
#  (cmd json-server --watch db.json)

  Scenario Outline: test for posts
    Given I am opening localhost posts and get OK status
    When I am opening localhost posts with not correct: "<data>" and get NOT FOUND status
    Then I am trying to test POST method in posts and get CREATED status
    And I am going on home page
    Examples:
      | data |
      | 1234 |
#      | 4321 |

  Scenario Outline: test for comments
    Given I am opening localhost comments and get OK status
    When I am opening localhost comments with not correct: "<data>" and get NOT FOUND status
    Then I am trying to test POST method in comments and get CREATED status
    And I am going on home page
    Examples:
      | data |
      | 5678 |
#      | 8765 |

  Scenario Outline: test for profile
    Given I am opening localhost profile and get OK status
    When I am opening localhost profile with not correct: "<data>" and get NOT FOUND status
    Then I am updating data and get CREATED status
    And I am going on home page
    Examples:
      | data |
      | 4567 |
#      | 7654 |