1) Отримання всіх даних
GET
http://localhost:8082/api/v1.0/contacts
[
    {
        "id": 1,
        "name": "Alice",
        "phone": "+380258741369"
    },
    {
        "id": 2,
        "name": "Bob",
        "phone": "+380526741852"
    },
    {
        "id": 3,
        "name": "Lucy",
        "phone": "+380258963741"
    },
    {
        "id": 4,
        "name": "Tom",
        "phone": "+380258741963"
    }
]

2) Отримання даних за id
GET
http://localhost:8082/api/v1.0/contacts/2
{
    "id": 2,
    "name": "Bob",
    "phone": "+380526741852"
}

3) Створення даних
POST
http://localhost:8082/api/v1.0/contacts
{
    "id": 5,
    "name": "John",
    "phone": "+780684380687"
}

4)Оновлення даних за id
PUT
http://localhost:8082/api/v1.0/contacts/2
{
   "phone": "+9896541238"
}
5)Видалення даних за id
DELETE
http://localhost:8082/api/v1.0/contacts/4
