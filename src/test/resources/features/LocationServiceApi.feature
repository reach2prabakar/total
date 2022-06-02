@api @location @datamap @regression
Feature: JiraId-feature location services via mapbox.com
  As a test user
  I want to send the location information to the map service
  So that I can see the exact location

  @locationdataset
  Scenario Outline: Get the dataset created,updated,deleted

    Given Data information for mapbox
    Then the user searches for the data in <apiName>

    Examples:
      | apiName             |
      | createDataset       |
      | updateDataset       |
      | deleteDataset       |
      | getDataset          |
      | createDatasetUnauth |

  @sendlocationdetails
  Scenario Outline: send the valid , invalid location details

    Given Data information for mapbox
    Then the user searches for the data in <apiName>

    Examples:
      | apiName                  |
      | geocoding                |
      | geocodingInvalid         |
      | geocodingInvalidLocation |
