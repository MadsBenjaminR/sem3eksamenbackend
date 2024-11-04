Semester 3 Eksamen 04/11/2024

https://github.com/MadsBenjaminR/sem3eksamenbackend


3.3.3:

GET {{url}}/

[
  {
    "id": 1,
    "name": "Svanemoellen Strand",
    "price": 100.5,
    "start_time": "11:00",
    "end_time": "15:00",
    "start_position": "Svanemoellen station",
    "category": "beach",
    "guide_id": 1
  },
  {
    "id": 2,
    "name": "Amager Strandpark",
    "price": 200.5,
    "start_time": "10:00",
    "end_time": "16:00",
    "start_position": "Amager Strand station",
    "category": "beach",
    "guide_id": 1
  },
  {
    "id": 3,
    "name": "Hornbaek Strand",
    "price": 300.5,
    "start_time": "09:00",
    "end_time": "17:00",
    "start_position": "Hornbaek Strand station",
    "category": "beach",
    "guide_id": 1
  },
  {
    "id": 4,
    "name": "Copenhagen",
    "price": 100.5,
    "start_time": "11:00",
    "end_time": "15:00",
    "start_position": "Copenhagen station",
    "category": "city",
    "guide_id": 2
  },
  {
    "id": 5,
    "name": "Odense",
    "price": 300.5,
    "start_time": "09:00",
    "end_time": "17:00",
    "start_position": "Odense station",
    "category": "city",
    "guide_id": 2
  },
  {
    "id": 6,
    "name": "Aarhus",
    "price": 200.5,
    "start_time": "10:00",
    "end_time": "16:00",
    "start_position": "Aarhus station",
    "category": "city",
    "guide_id": 2
  },
  {
    "id": 7,
    "name": "Mols Bjerge",
    "price": 300.5,
    "start_time": "09:00",
    "end_time": "17:00",
    "start_position": "Mols Bjerge station",
    "category": "forest",
    "guide_id": 3
  },
  {
    "id": 8,
    "name": "Gribskov",
    "price": 200.5,
    "start_time": "10:00",
    "end_time": "16:00",
    "start_position": "Gribskov station",
    "category": "forest",
    "guide_id": 3
  },
  {
    "id": 9,
    "name": "Rold Skov",
    "price": 100.5,
    "start_time": "11:00",
    "end_time": "15:00",
    "start_position": "Rold Skov station",
    "category": "forest",
    "guide_id": 3
  },
  {
    "id": 10,
    "name": "Silkeborg Søerne",
    "price": 300.5,
    "start_time": "09:00",
    "end_time": "17:00",
    "start_position": "Silkeborg Søerne station",
    "category": "lake",
    "guide_id": 4
  },
  {
    "id": 11,
    "name": "Arresø",
    "price": 100.5,
    "start_time": "11:00",
    "end_time": "15:00",
    "start_position": "Arresø station",
    "category": "lake",
    "guide_id": 4
  },
  {
    "id": 12,
    "name": "Furesø",
    "price": 200.5,
    "start_time": "10:00",
    "end_time": "16:00",
    "start_position": "Furesø station",
    "category": "lake",
    "guide_id": 4
  },
  {
    "id": 13,
    "name": "Skagen",
    "price": 100.5,
    "start_time": "11:00",
    "end_time": "15:00",
    "start_position": "Skagen station",
    "category": "sea",
    "guide_id": 5
  },
  {
    "id": 14,
    "name": "Hirtshals",
    "price": 300.5,
    "start_time": "09:00",
    "end_time": "17:00",
    "start_position": "Hirtshals station",
    "category": "sea",
    "guide_id": 5
  },
  {
    "id": 15,
    "name": "Løkken",
    "price": 200.5,
    "start_time": "10:00",
    "end_time": "16:00",
    "start_position": "Løkken station",
    "category": "sea",
    "guide_id": 5
  },
  {
    "id": 16,
    "name": "HOFOR skibakken",
    "price": 300.5,
    "start_time": "09:00",
    "end_time": "17:00",
    "start_position": "Mols Bjerge station",
    "category": "snow",
    "guide_id": 6
  },
  {
    "id": 17,
    "name": "Toppen af Danmark",
    "price": 200.5,
    "start_time": "10:00",
    "end_time": "16:00",
    "start_position": "Gribskov station",
    "category": "snow",
    "guide_id": 6
  },
  {
    "id": 18,
    "name": "Himmelbjerget",
    "price": 100.5,
    "start_time": "11:00",
    "end_time": "15:00",
    "start_position": "Rold Skov station",
    "category": "snow",
    "guide_id": 6
  }
]


###

GET {{url}}/1

{
  "id": 1,
  "name": "Svanemoellen Strand",
  "price": 100.5,
  "start_time": "11:00",
  "end_time": "15:00",
  "start_position": "Svanemoellen station",
  "category": "beach",
  "guide_id": 1
}


###

DELETE {{url}}/1

<Response body is empty>Response code: 204 (No Content); Time: 34ms (34 ms); Content length: 0 bytes (0 B)

###


PUT {{url}}/1/guides/2

[
  {
    "id": 2,
    "name": "Amager Strandpark",
    "price": 200.5,
    "start_time": "10:00",
    "end_time": "16:00",
    "start_position": "Amager Strand station",
    "category": "beach",
    "guide_id": 1
  },
  {
    "id": 3,
    "name": "Hornbaek Strand",
    "price": 300.5,
    "start_time": "09:00",
    "end_time": "17:00",
    "start_position": "Hornbaek Strand station",
    "category": "beach",
    "guide_id": 1
  }
]

###


GET {{url}}/guides/1

[
  {
    "id": 2,
    "name": "Amager Strandpark",
    "price": 200.5,
    "start_time": "10:00",
    "end_time": "16:00",
    "start_position": "Amager Strand station",
    "category": "beach",
    "guide_id": 1
  },
  {
    "id": 3,
    "name": "Hornbaek Strand",
    "price": 300.5,
    "start_time": "09:00",
    "end_time": "17:00",
    "start_position": "Hornbaek Strand station",
    "category": "beach",
    "guide_id": 1
  }
]

###

POST {{url}}/populate

Response code: 201 (Created); Time: 99ms (99 ms); Content length: 31 bytes (31 B)



3.3.5

Fordi hvis man burger PUT vil du altid opdatere på det givne id og derved beholde de same relationer. Hvorimod hvis du burger POST vil du persistere en ny entitet i databasen hver gang du kalder methoden.

