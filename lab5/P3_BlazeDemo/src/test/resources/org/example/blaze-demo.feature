Feature: Blaze Demo

  Scenario: Purchase a flight from "Paris" to "Buenos Aires" with "Lufthansa" airline
    Given I am on "https://blazedemo.com/" page
    And I choose "Paris" for departure city
    And I choose "Buenos Aires" for destination city
    And I click in "Find Flights"
    Then "Flights from Paris to Buenos Aires" should appear as a h3 title
    And I click in "Choose This Flight"
    Then "Your flight from TLV to SFO has been reserved." should appear as a h2 title
    And Fill some info by writing "Diogo Cunha" in your credit card name

    And I click in "Purchase Flight"
    Then "Thank you for your purchase today!" should appear as a h1 title