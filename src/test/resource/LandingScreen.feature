Feature: Validate the Landing screen on Web browser

  Scenario Outline: View the Application landing screen on Web browser
    Given open web browser
    When If Start Now button are visible
    Then Click on Start Now Button
    Examples: