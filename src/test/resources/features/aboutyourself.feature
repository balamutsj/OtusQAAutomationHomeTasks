Feature: About Yourself
  Test user personal data

  Scenario: User check field is editable: name
    When User logs in as student
    And User goes to Personal Area
    And User is at Personal Area
    And User goes to Personal Info tab
    And User is at Personal Info tab
    And User check name is editable

  Scenario: User check field is editable: lastname
    When User logs in as student
    And User goes to Personal Area
    And User is at Personal Area
    And User goes to Personal Info tab
    And User is at Personal Info tab
    And User check lastname is editable

  Scenario: User check field is editable: blogname
    When User logs in as student
    And User goes to Personal Area
    And User is at Personal Area
    And User goes to Personal Info tab
    And User is at Personal Info tab
    And User check blogName is editable