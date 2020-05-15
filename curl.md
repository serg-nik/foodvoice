# CURL-запросы к API FoodVoice

Если по какой-то причине неудобно использовать документацию [Swagger](http://localhost:8080/swagger-ui.html), то можно 
воспользоваться curl-запросами.
 
Ниже примеры запросов к API FoodVoice. Они разделены на 3 группы по типу авторизации. Для выполнения запросов, 
где требуется авторизация необходимо заменять значение "[token]" на валидный токен пользователя с нужной ролью. 
Токен получается запросом ["Авторизация пользователя"](#Авторизация-пользователя).

## Запросы не авторизованных пользователей

* Просмотр списка ресторанов с их меню дня
```shell script
curl -X GET "http://localhost:8080/api/v1/restaurants/menus/actual/" -H "accept: application/json"
```

* Регистрация нового пользователя
```shell script
curl -X POST "http://localhost:8080/api/v1/register/" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"name\": \"New User\", \"email\": \"new_user@foodvoice.ru\", \"password\": \"password\"}"
```

* Авторизация пользователя
```shell script
curl -X POST "http://localhost:8080/api/v1/login/" -H "accept: application/json" -H "Content-Type: application/json" -d "{ \"email\": \"admin@foodvoice.ru\", \"password\": \"password\"}"
```

## Запросы авторизованные пользователей

* Выбор ресторан (его меню) для обеда
```shell script
curl -X POST "http://localhost:8080/api/v1/voices/" -H "accept: application/json" -H "Authorization: Bearer_[token]" -H "Content-Type: application/json" -d "{ \"menuId\": \"dd0e6ec6-1532-4219-bab4-2e04a31b6a01\"}"
```

* Изменение выбранного ресторана
```shell script
curl -X PUT "http://localhost:8080/api/v1/voices/f16eb5ea-14bc-42b0-809a-333639464730" -H "accept: application/json" -H "Authorization: Bearer_[token]" -H "Content-Type: application/json" -d "{ \"menuId\": \"dd0e6ec6-1532-4219-bab4-2e04a31b6a01\"}"
```

* Просмотр история своего голосования
```shell script
curl -X GET "http://localhost:8080/api/v1/voices/my" -H "accept: application/json" -H "Authorization: Bearer_[token]"
```

## Запросы авторизованных администраторов

* Просмотр списка ресторанов с их меню
```shell script
curl -X GET "http://localhost:8080/api/v1/restaurants/menus/" -H "accept: application/json" -H "Authorization: Bearer_[token]"
```

* Добавление ресторана с/без его меню
```shell script
curl -X POST "http://localhost:8080/api/v1/restaurants/" -H "accept: application/json" -H "Authorization: Bearer_[token]" -H "Content-Type: application/json" -d "{ \"address\": \"New address\", \"name\": \"name\", \"status\": 1, \"email\": \"new@foodvoice.ru\", \"menus\": [ { \"name\": \"name\", \"status\": 1, \"actual\": true, \"dishes\": [ { \"name\": \"name\", \"status\": 1, \"price\": 100000 } ] } ]}"
```

* Просмотр ресторана с его меню
```shell script
curl -X GET "http://localhost:8080/api/v1/restaurants/58b3d8b0-7d08-4274-8aa8-68976d0582ee" -H "accept: application/json" -H "Authorization: Bearer_[token]"
```

* Добавление актуального меню ресторана
```shell script
curl -X POST "http://localhost:8080/api/v1/restaurants/58b3d8b0-7d08-4274-8aa8-68976d0582ee/menus/actual/" -H "accept: application/json" -H "Authorization: Bearer_[token]" -H "Content-Type: application/json" -d "{ \"name\": \"name\", \"status\": 1, \"actual\": true, \"dishes\": [ { \"name\": \"name\", \"status\": 1, \"price\": 100000 } ]}"
```

* Просмотр меню ресторана
```shell script
curl -X GET "http://localhost:8080/api/v1/menus/fbb9b1a5-a599-4574-954c-27b52a95be2a" -H "accept: application/json" -H "Authorization: Bearer_[token]"
```

* Изменение/установка меню дня ресторана
```shell script
curl -X PUT "http://localhost:8080/api/v1/menus/fbb9b1a5-a599-4574-954c-27b52a95be2a?actual=true" -H "accept: application/json" -H "Authorization: Bearer_[token]"
```

* Изменение пользователя
```shell script
curl -X PUT "http://localhost:8080/api/v1/users/f16eb5ea-14bc-42b0-809a-333639464730" -H "accept: application/json" -H "Authorization: Bearer_[token]" -H "Content-Type: application/json" -d "{ \"name\": \"name\", \"status\": 1, \"email\": \"new@foodvoice.ru\", \"password\": \"password\", \"roles\": [ { \"name\": \"USER\" }, { \"name\": \"ADMIN\" } ]}"
```