Feature: Validating add place API's

Scenario: Verify if place is being added successfully using AddPlaceAPI

  Given Add place paylod
  When user calls "AddPlaceAPI" with post https request
  Then the API call is success with status code 200
  And "status" in response body is "OK"
  And "scope" in response body is "APP"