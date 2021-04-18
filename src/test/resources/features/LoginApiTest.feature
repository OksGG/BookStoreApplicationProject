Feature: LoginApiTest

  Scenario: LoginApiSuccess
    Given Входим на страницу Login
    When Передаем параметры для успешного входа
    Then Проверяем, что залогинились, код 200


  Scenario: LoginApiError
    Given Входим на страницу Login
    When Передаем некорректные параметры
    Then Получаем ошибку 404

