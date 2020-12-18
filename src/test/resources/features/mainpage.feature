Feature: MainPage
  Test Data on Main Page

  Scenario: Check main header menu items
    Given User opens Otus main page
    And User is on Otus main page
    Then User checks header menu items
      | Курсы |
      | События |
      | Преподавателям |
      | Компаниям |
      | О нас |

  Scenario: Check main page blocks subtitles
    Given User opens Otus main page
    And User is on Otus main page
    Then User checks subtitles are present
      | Популярные курсы |
      | Специализации |