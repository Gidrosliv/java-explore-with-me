# Explore With Me (Сервис поиска мероприятий поблизости).
[![Explore With Me API Tests](https://github.com/Gidrosliv/java-explore-with-me/actions/workflows/api-tests.yml/badge.svg)](https://github.com/Gidrosliv/java-explore-with-me/actions/workflows/api-tests.yml)
 ![Java](https://img.shields.io/badge/-Java-green) ![Spring Boot](https://img.shields.io/badge/-Spring%20Boot-blue)![Postgres SQL](https://img.shields.io/badge/-Postgres%20SQL-brightgreen) ![REST](https://img.shields.io/badge/-REST-orange) ![Docker](https://badgen.net/badge/icon/docker?icon=docker&label) ![Git](https://badgen.net/badge/icon/github?icon=github&label)

### Описание:

Бэкенд сервиса, который позволяет находить интересные мероприятия вокруг себя, а также размещать свои.

Архитектура приложения представлена двумя частями, взаимодействующими между собой: основной сервис и сервис статистики. 
API основного сервиса разделён на три части:

Публичное API, доступна без регистрации любому пользователю сети:
* Сортировка списка событий по количеству просмотров либо по датам событий.
* Просмотр подробной информации о конкретном событии.
* Есть возможность получения всех имеющихся категорий и подборок событий (такие подборки могут составлять администраторы ресурса).
* Каждый публичный запрос для получения списка событий или полной информации о мероприятии фиксируется сервисом статистики.

Закрытое API, доступна только авторизованным пользователям:
* Пользователи могут добавлять в приложение новые мероприятия, редактировать их и просматривать после добавления.
* Могут подавать заявки на участие в интересующих мероприятиях.
* Создатель мероприятия имеет возможность подтверждать заявки, которые отправили другие пользователи сервиса.

Административное API, для администраторов сервиса:
* Добавление, изменение и удаление категорий для событий.
* Возможность добавлять, удалять и закреплять на главной странице подборки мероприятий.
* Модерацию событий, размещённых пользователями, — публикация или отклонение.
* Управление пользователями — добавление, просмотр и удаление.

Сервис статистики, собирает информацию о количестве обращений пользователей к спискам событий и о количестве запросов к подробной информации о событии.

### Спецификация

Спецификации [основного сервиса](https://github.com/devShurakov/java-Explore-With-Me/blob/main/ewm-main-service-spec.json) и [сервиса статистики](https://github.com/devShurakov/java-Explore-With-Me/blob/main/ewm-stats-service-spec.json) можно посмотреть с помощью [Swagger](https://editor-next.swagger.io) 

### Шаблоны проектирования

В приложении использован шаблон проектирования Data Transfer Object (DTO Pattern).

###### Пэт проект в рамках прохождения учебного курса Яндекс.
