Feature: TestFeature for testing how Cucumber works

  @JustForTest
  Scenario: Checking simple scenario
    Given a Test data for VALUE
    When a Test data has VALUE
    Then a Test data removed

  @JustForTest
  Scenario Outline: Checking simple scenario outline
    Given a Test data for <values>
    When a Test data has <values>
    Then a Test data removed
    Examples:
      | values |
      | VALUE  |

