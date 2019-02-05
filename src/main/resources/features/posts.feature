@posts
Feature: testing webservice, browsing from one page to other page and ect

  Scenario: test for posts
    Given I am opening localhost posts and get OK status
    When I am going to post with id one
    Then I am creating additional post and get CREATED status
    And I am going to all posts