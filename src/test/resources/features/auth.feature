Feature: Authentication
  Test for user Login

  Scenario: User logs into application
    Given User opens Otus main page
    And User is on Otus main page
    When User opens Auth page
    And User type username
    And User type password
    And User clicks Login button
    Then User is on Otus main page

  Scenario: User logs with incorrect creds
    Given User opens Otus main page
    And User is on Otus main page
    When User opens Auth page
    And User type username with value "John Doe"
    And User type password with value "passJDoe"
    And User clicks Login button
    Then User checks login error msg

    Scenario Outline: User logs with different users
      Given User opens Otus main page
      And User is on Otus main page
      When User opens Auth page
      When User enters credentials: <username> and <password>
      And User clicks Login button
      Then User is on Otus main page - authorized
      Examples:
        | username | password |
        | gelero4909@adeata.com | gelero4909 |
        | balid61348@econeom.com | balid61348 |

      Scenario: User restores password with not existing email
        Given User opens Otus main page
        And User is on Otus main page
        When User opens Auth page
        And User clicks Forgot pass
        When User is on Restore Pass modal
        And User types email: qwerty@econeom.com
        And User clicks Restore button
        Then User checks forgot pass error msg

  Scenario: User restores password
    Given User opens Otus main page
    And User is on Otus main page
    When User opens Auth page
    And User clicks Forgot pass
    When User is on Restore Pass modal
    And User types email: balid61348@econeom.com
    And User clicks Restore button
    Then Assert email sent
