# Task Tracker Backend

Основной язык: Java

Task Tracker Backend - это backend-приложение для управления задачами и отслеживания их состояния.

Проект разработан с использованием следующих технологий:
- Spring Boot
- Spring Data JPA
- Maven
- PostgreSQL

Для тестирования использовались:
- Postman
- pgAdmin 4

## Возможности приложения

- создание новой задачи
- получение списка всех задач
- получение задачи по ID
- фильтрация задач по `status` и `title`
- сортировка задач по выбранному полю
- обновление задачи по ID
- обновление всех задач
- удаление задачи по ID
- удаление всех задач

## Структура задачи

Каждая задача содержит:
- `id` - идентификатор
- `title` - название
- `description` - описание
- `deadline` - крайний срок выполнения
- `status` - статус задачи

## Планируется добавить

- фильтрацию по большему количеству полей
- web-интерфейс




## Запуск проекта

Для запуска проекта потребуется:
- Java 17
- Maven
- PostgreSQL

Перед запуском необходимо:
1. создать базу данных PostgreSQL
2. указать параметры подключения в `application.properties`
3. убедиться, что PostgreSQL запущен

Пример параметров подключения:
```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/task_tracker
spring.datasource.username=postgres
spring.datasource.password=your_password
spring.jpa.hibernate.ddl-auto=update
spring.datasource.driver-class-name=org.postgresql.Driver
```


Для запуска приложения используйте:

`mvn-spring-boot:run`

## API endpoints

- `GET /tasks` - получить список задач
- `GET /tasks/{id}` - получить задачу по ID
- `POST /tasks` - создать новую задачу
- `PUT /tasks/{id}` - обновить задачу по ID
- `PUT /tasks` - обновить все задачи
- `DELETE /tasks/{id}` - удалить задачу по ID
- `DELETE /tasks` - удалить все задачи

### Query parameters for GET /tasks

- `status` - фильтрация по статусу
- `title` - фильтрация по названию
- `sortBy` - поле для сортировки
- `direction` - направление сортировки (`asc` / `desc`)