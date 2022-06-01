@api @location
Feature: JiraId-feature location services via mapbox.com
  As a test user
  I want to send the location information to the map service
  So that I can see the exact location

  @getusedcarcount @api
  Scenario Outline: Get list of used cars and check if the search result returns any results

    Given Business details information for tradeMe
    When the user searches for the data in <apiName>

    Examples:
      | apiName   |
      | geocoding |
