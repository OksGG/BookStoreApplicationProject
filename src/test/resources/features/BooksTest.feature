Feature: BooksTest

  Scenario: ViewBooksList
    Given Открываем страницу с книгами
    When Проверяем, что залогинились
    Then Проверяем список книг
    And Закрываем окно браузера

  Scenario: AddDeleteBook
    Given Открываем страницу с книгами
    When Логинимся
    Then Добавляем и удаляем книгу
    And  Закрываем окно браузера