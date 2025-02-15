Feature: Validating add place API's

  Scenario Outline: Verify if place is being added successfully using AddPlaceAPI

    Given Add place paylod with "<name>" "<address>"  "<language>"
    When user calls "AddPlaceAPI" with "post" https request
    Then the API call is success with status code 200
    And "status" in response body is "OK"
    And "scope" in response body is "APP"
    And verify if place_id created maps to "<name>" using "getPlaceAPI"

    Examples:
      |    name      |     address          | language |
      |    shivam    |  World Trade Centre  |  Punjabi |
      | shivam setia |      Delhi           |  English |

