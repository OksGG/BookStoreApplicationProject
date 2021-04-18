Feature: BooksApiTest

  Scenario: ApiViewBooksList
    Given Открываем страницу Books
    When Проверяем, что список книг доступен без логина
    Then Успешное получение списка книг

  Scenario: ApiAddDeleteBook
    Given Добавляем книгу
    When Открываем свой профиль и проверяем, что добавленная книга там есть
    Then Успешно удаляем книгу



