# translator
## ТЗ
Сделать rest-сервис с методом, который на вход принимает строку и переводит ее на другой язык. 

Необходимо реализовать контроллер для входных данных, и параметров перевода (например язык перевода en-ru/ru-en). 
Входную строку необходимо поделить на слова и переводить слова отдельно.

Приложение должно вести в БД следующие данные:

- Входные данные и выходные, время обращение, параметры для перевода, ip-адрес с которого сделан запрос
- Хранить перевод каждого слова, связка должна быть с id запроса 

В качестве сервиса для перевода можно использовать любой открытый api Yandex, google или любой другой.

## Требования к реализации
- Код вести в github, gitlab
- Java /kotlin
- spring boot
- rest-service, json на вход, json на выход
- inmemory database (h2 + console)/sqlLite
- вызов стороннего REST или SOAP-сервиса
## Дополнительные требования
- Плюсом будет использование многопоточности для перевода слов но не больше 10 потоков
- Плюсом будет размещение приложения на https://www.heroku.com

## Heroku link
https://konstanin-translator.herokuapp.com
## User Guid
Чтобы обратиться в сервису требуется передать `POST` запрос на следующий uri
https://konstanin-translator.herokuapp.com/translate

У запроса есть 2 параметра: 
- `from` - это язык с которого нужно перевести, он может быть только 1
- `to` - это языки на которые нужно перевести их может быть несколько

__Пример uri:__ `https://konstanin-translator.herokuapp.com/translate?from=en&to=ru,de,fr,it,ro`

Тело запроса должно содержать `JSON` следующего формата:
``` json
{
    "text":"Some text to? translate. With -two  4sentence_ . buy"
}
```
Поле `text` содержит строку которую требуется перевести

На выходе в теле запроса будет имется `JSON` массив переводов следующего вида:
``` json
[
    {
        "text": "Несколько СМС Кому? Перевести. С -Два  4предложение_ . покупать",
        "to": "ru"
    },
    {
        "text": "Einige Text An? Übersetzen. Mit -Zwei  4Satz_ . kaufen",
        "to": "de"
    },
    {
        "text": "Quelques SMS À? Traduire. Avec -Deux  4phrase_ . acheter",
        "to": "fr"
    },
    {
        "text": "Alcuni Testo A? Traduci. Con -Due  4frase_ . comprare",
        "to": "it"
    },
    {
        "text": "Niște Text spre? traduce. Cu -Doi  4propoziție_ . cumpăra",
        "to": "ro"
    }
]
```

- поле `text` содержит перевод
- поле `to` содержит язык на который был переведен входной текст

### Ошибки
При некоректых параметрах сервис вернет `JSON` следующего вида:
```json
{
    "error": {
        "code": 400036,
        "message": "The target language is not valid."
    }
}
```



