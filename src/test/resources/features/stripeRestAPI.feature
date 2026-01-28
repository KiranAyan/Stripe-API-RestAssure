Feature: Stripe Customer API Management

Background:
# Can write comman steps here like login 

@prod
  Scenario: Successfully retrieve the configured customer
    Given I have set base URI
    When I send a GET request to retrieve the customer
    Then the response status code should be 200
    And the response body should contain the id "customerID"
    And the response body should contain id "<customerID>"
    
    Examples:
 | customerID |
 |cus_TnIl1ND819CVCv|
    
    
   @Stagging
    Scenario: Successfully retrieve the configured customer
    Given I have set base URI
    When I send a GET request to retrieve the customer
    Then the response status code should be 200
    And the response body should contain id "cus_TnIl1ND819CVCv"
   