@test
@posts
Feature: tests for posts with GET, POST, status codes 200, 201, 404

  Scenario Outline: testing webservice, browsing from one page to other page and ect
    Given I am opening localhost posts and get OK status
    When I am going to post with id one
    Then I am creating additional post with "<title>", "<author>" and get CREATED status
    And I am going to all posts
    Examples:
      | title | author |
      | abcd  | Abcd   |
#      | dcba  | Dcba   |