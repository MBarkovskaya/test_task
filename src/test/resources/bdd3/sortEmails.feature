Feature: sort emails

  Background: log in operation
    Given I am on the Google search page, choose Gmail reference
    And I enter user's login extraordinarypetrsidorov@gmail.com, password petrsidorovextraordinary1974

  Scenario: sort emails and check results
    When I open Inbox sort emails by date desc
    And
    Then the new set of emails is equal to the old set with the added new one
