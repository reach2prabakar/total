@ui @buyproduct @regression
Feature: Jira-Feature# purchasing the product end to end flow
  As a user
  I will search for the product with 3 criteria and add that to my cart
  So that I can purchase the product

  @searchforaproduct
  Scenario Outline: search the product with search criteria and adding it to the cart

   Given user navigated to the <maintab> menu option
    When user search for the product with following search criteria
      | Categories |
      | Dresses    |
    And user search for the product with following search criteria
      | Size | Compositions |
      | M    | Polyester    |
    And user adds the number of product to the cart on hover
      | 1 |
    Then user views the cart and does the checkout

   Examples:
      | maintab |
      | WOMEN   |

