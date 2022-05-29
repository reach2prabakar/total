@ui @buyproduct
Feature: Jira-Feature# purchasing the product end to end flow
  As a user
  I will search for the product with 3 criteria and add that to my cart
  So that I can purchase the product

  @searchforaproduct
  Scenario Outline: search the product with search criteria and adding it to the cart

    Given user navigated to the <maintab> menu option


    Examples:
      | maintab |
      | WOMEN   |

