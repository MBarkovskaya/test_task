Feature: sort emails

  Background: log in operation
    Given I am on the Google search page, choose Gmail reference
    And I enter user's login extraordinarypetrsidorov@gmail.com, password petrsidorovextraordinary1974
    And I sort emails by UnreadFirst

  Scenario: sort emails and check results
    When I sort Inbox emails by date desc
    Then the previous email's date more than next email's date

