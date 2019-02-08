@test
@headers
Feature: tests for headers from HTTP response with response validation

  Scenario: test for header
    Given I am getting all headers and check status code
    And I am getting single header Content-Type, checking status code, validation response
