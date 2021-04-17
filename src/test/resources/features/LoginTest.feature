Feature: LoginTest

  Scenario: LoginSuccess
    Given Open page
    When Data input correct
    Then Get success
    And  Browser close

  Scenario: LoginError
    Given Open page
    When Data input incorrect
    Then Get login error
    And  Browser close