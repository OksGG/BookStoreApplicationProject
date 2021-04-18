Feature: LoginTest

  Scenario: LoginSuccess
    Given Открываем страницу
    When Вводим корректные логин и пароль
    Then Проверяем, что залогинились
    And  Закрываем браузер

  Scenario: LoginError
    Given Открываем страницу
    When Вводим некорректные логин и пароль
    Then Получаем ошибку при входе
    And  Закрываем браузер