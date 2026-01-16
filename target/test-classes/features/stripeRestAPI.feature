Feature: Stripe Customer API Management

  Scenario: Successfully retrieve the configured customer
    Given I have set base URI
    When I send a GET request to retrieve the customer
    Then the response status code should be 200
    And the response body should contain the id "customerID"