Feature: Sign up
  Scenario: Click on signup button
    Given I open the browser and navigate to "https://x.com/ComunidadMadrid"
    When Google signup button appears and user click on it
    Then Signup popup is open
