# Тестовое задание

Использованы технологии:

* ЯП: Java 11
* Сборка и Управление зависимостями: Maven
* Фреймворк для разработки: Spring
* Веб-сервер: Apache Tomcat (Порт по-умолчанию:8080)
* Инициализация проекта: Spring Boot 2.5.4 (https://start.spring.io/)

* Структура приложения: RESTful API
* Формат обмена данными: JSON

* Аутентификация и авторизация: Spring Security
* Алгоритм хеширования паролей: BCrypt

* Логирование: SLF4J
* Валидация: Java Bean Validation API

* База данных: MS SQL SERVER 2019 (Порт по-умолчанию:1433) ~~(User:sa;Password:sa)~~ (User:USER1;Password:USER1qwerty)  
* Работа с базой данных: Spring Data JPA
* Миграции баз данных: Flyway

* Представление : Swagger UI (http://localhost:8080/swagger-ui/#/)

* Управление контейнеризацией: Docker

application.properties:
<pre>
spring.datasource.username=USER1
spring.datasource.password=USER1qwerty
spring.datasource.url=jdbc:sqlserver://localhost:1433;database=emercom_db;trustServerCertificate=true;
spring.datasource.driver-class-name=com.microsoft.sqlserver.jdbc.SQLServerDriver

spring.flyway.clean-on-validation-error=true
spring.flyway.locations=classpath:/db/migration
spring.flyway.baseline-on-migrate=true
spring.jpa.database-platform=org.hibernate.dialect.SQLServerDialect

spring.mvc.throw-exception-if-no-handler-found = true
</pre>  

# Security Endpoints:

Залогиниться (создается сессия и сохраняет состояние пользователя на сервере) -  http://localhost:8080/login  (login:login,password:password)  

Сделать logout (Сессия пользователя удаляется) - http://localhost:8080/logout ,после чего будет редирект на форму логина.  

Зайти на Swagger-ui - http://localhost:8080/swagger-ui/#/

**GET http://localhost:8080/registration**  
Описание: Регистрация пользователя и присвоение ему роли User
Пример тела запроса:  
{
"login": "SunKing1638",
"password": "sunpassword1234",
"name": "Людовик",
"surname": "Бурбон",
"email": "ludovik1638@example.com",
"country": "Франция"
}  
Коды ответа:  
204 No content - Если поля прошли валидацию,а пользователь успешно зарегистрирован    
409 Not Found - Если поля  не прошли валидацию 



# User Endpoints:

**GET http://localhost:8080/user**     
Описание: Получить список всех пользователей
Параметры: Нет  
Коды ответа:  
200 OK - Возвращает список всех пользователей  
404 Not Found - Если список пользователей пуст

**GET http://localhost:8080/user/{id}**   
Описание: Получить информацию о пользователе по ID    
Параметры: ID - идентификатор пользователя 
Коды ответа:   
200 OK - Возвращает информацию о пользователе  
404 Not Found - Если статья не найдена

**GET http://localhost:8080/user/sort/{params}**  
Описание: Получить все поля в базе данных по выбранному параметру и значению
Выбрать один из трех параметров по которому будет выборка (Country, Name, Surname) передать его в самом теле URL запроса. 
Дальше передать значение по которому нужно искать совпадения.  
Пример:http://localhost:8080/Country/{Беларусь}   
Коды ответа:  
200 OK - Если фильтрация успешно пройдена  
404 Not Found - Если значения не найдены

**PUT http://localhost:8080/user/update**  
Описание: Обновить информацию о Пользователе
Пример тела запроса:  
{
"id": "6"
"login": "NoviyLogin88",
"password": "sunpassword1234",
"name": "Людовик",
"surname": "Бурбон",
"email": "ludovik1638@example.com",
"country": "Франция"
}   
Коды ответа:  
204 No Content - Если информация о пользователе успешно обновлена  
409 Conflict - Если при валидации пользователя произошла ошибка

**DELETE http://localhost:8080/user/{id}**  
Описание: Удалить пользователя по ID   
Параметры: ID  
Коды ответа:  
204 No Content - Если пользователь успешно удален  
404 Not Found - Если пользователь не найден


Образ приложения лежит на DockerHub:  
docker run -t asil88/emercomapp:v1 .
