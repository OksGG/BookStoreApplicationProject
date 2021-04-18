Feature: BooksApiTest

  Scenario: ApiViewBooksList
    Given Open url
    When Response
    Then Assert

  Scenario: ApiAddDeleteBook
    Given Add book
    When Open profile
    Then Delete book



