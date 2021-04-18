Feature: LoginApiTest

  Scenario: LoginApiSuccess
    Given Url open
    When Success request parameters
    Then Success api test


  Scenario: LoginApiError
    Given Url open
    When Error request parameters
    Then Error api test

