Feature: BooksTest

  Scenario: ViewBooksList
    Given Open Book page
    When Check logout
    Then Check list books
    And  Close browser

  Scenario: AddDeleteBook
    Given Open Book page
    When Login
    Then Add and delete book
    And  Close browser