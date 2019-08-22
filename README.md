# Servlets (Filters, Session, Cookie, HTML, CSS, AJAX) + JUnit tests

  (editing and adding new features to the ServletsTask_Part1)
  
Исходное задание: 
-----------------
1) Добавить страницу JSP и Servlet регистрации. 

* a. POST /user/register – Servlet регистрации.

* b. GET /user/register – страница регистрации.

2. Добавить главную страницу. HTTP страницы login и home (на домашней странице должны быть кнопки перенаправления на страницу 
логина & регистрации).

3. Создать страницу JSP пользователя. На которой будет отображена информация пользователя – все, кроме пароля. 

  На эту страницу теперь перенаправлять вместо /user/login/success.
  URL для страницы - /user/home

4. Добавить к URL CRUD операция /user prefix /management

      * GET host/management/user
      
      * PUT host/management/user

      * POST host/management/user

      * DELETE host/management/user
      
      * GET host/management/users

5. Добавить Filter, который будет отвечать за проверку прав доступа

* a. Если у пользователя нет прав доступа, перенаправить его на /user/login
  
   *  Только пользователи с ролью ADMIN могут получить доступ к CRUD операциям для пользователя и к страницам
      администрирования. (/management/*, /admin/*)
   
   *  На этой странице сделать HTML form (мы их пока не учили, но
      гуглится это легко. В HELP есть пример)
 
* b. Запомнить URL, на который пользователь пытался получить доступ

6. Изменить поведение PUT /user/login. Теперь задача этого Endpoint положить в сессию пользовательскую информацию (без пароля).

7. Добавить Servlet PUT /user/logout, который будет инвалидировать сессию.

8. Не забывать про PRG паттерн.

9. Сделать страничку администрирования, где можно будет CRUD пользователей.

* a. Если у пользователя нет прав доступа, перенаправить его на /user/login
  
   *  Добавить кнопку `Create user`.
   
*  b. Ниже вывести список всех пользователей **AJAX** GET /management/users.
      Cделать таблицу из 5 столбцов: (Username|First name|Last name|Update (кнопка)|Delete (кнопка))
      
   *  Update. Поля формы заполнить данными, которые получить из GET /management/user
   
   *  Delete – после delete запись должна быть удалена из таблицы. НЕ выполнять повторный запрос на GET /management/users. 
   
10. Покрыть все тестами (не менее чем на 80%).
