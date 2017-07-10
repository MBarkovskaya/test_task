Feature: account

  Scenario Outline: Account login
    Given I am on the Google search page
    And I choose Gmail
    When I login witn email <email>, password <password>
    Then user's enter was successful, the page contains gmailButton <gmailButton>

    Examples:
      | password                     | email                              | gmailButton |
      | petrsidorovextraordinary1974 | extraordinarypetrsidorov@gmail.com | Gmail       |

  Scenario Outline: Account incorrect login
    Given I am on the Google search page
    And I choose Gmail
    When I login witn email <email>, incorrect password <password>
    Then user's enter was unsuccessful, the page contain messageEn <messageEn> or messageRu <messageRu>

    Examples:
      | password                 | email                              | messageEn        | messageRu      |
      | petrsidorovextraordinary | extraordinarypetrsidorov@gmail.com | Forgot password? | Забыли пароль? |