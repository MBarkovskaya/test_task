Feature: send email

  Background: log in operation
    Given I am on the Google search page, choose Gmail reference
    And I enter user's login extraordinarypetrsidorov@gmail.com, password petrsidorovextraordinary1974

  Scenario Outline: send new email
    When I send new email to <recipient>
    Then the sent email's subject's name is equal to the entered email's subject's name
    Examples:
      | recipient                     |
      | improvementisalways@gmail.com |


  Scenario Outline: send new letter with wrong email
    When I send new email with unrecognized recipient <recipient>
    Then I get an message <message> about error
    Examples:
      | recipient | message |
      | me        | Error   |