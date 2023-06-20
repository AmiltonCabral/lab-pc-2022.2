# Projeto Programação Concorrente 2022.2

Projeto Programação Concorrente, implementando um protocolo HTTP/2 em golang.

[Código base](https://github.com/pedrohenrique-ql/concorrente-lab-base).

## ⚙️ Requisitos

- [Java 11+](https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html)

## 🔍 Funções auxiliares

Esse código fornece funções auxiliares para facilitar o desenvolvimento do projeto:

- [readFile()](https://github.com/pedrohenrique-ql/concorrente-lab-base/blob/main/src/main/java/main/lab1/utils/FileIOUtil.java#L13): Lê o arquivo `.txt` que contém a lista de identificadores de atores e retorna uma determinada quantidade de atores definida:

  ```java
  // Chamada exemplo
  String ACTORS_DATA_PATH = "./data/actors.txt";
  int NUMBER_OF_ACTORS = 1000;

  FileIOUtil.readFile(ACTORS_DATA_PATH, NUMBER_OF_ACTORS);
  ```

- [csvWriter()](https://github.com/pedrohenrique-ql/concorrente-lab-base/blob/main/src/main/java/main/lab1/utils/FileIOUtil.java#L32): Escreve em um arquivo `.csv` o ranking de atores de acordo com seus scores;

  ```java
  // Chamada exemplo
  FileIOUtil.csvWriter(actorsRatingMap, "actors.csv");
  ```

- [requestActor()](https://github.com/pedrohenrique-ql/concorrente-lab-base/blob/main/src/main/java/main/lab1/services/CineLsdDatabaseService.java#L19): Realiza requisição de um ator através do seu identificador:

  ```java
  // Chamada exemplo
  CineLsdDatabaseService.requestActor("nm2095259");

  // Retorno
  {
    "id": "nm2095259",
    "name": "Bruce Lawrence",
    "movies": [
      "tt0276751",
      "tt2381249",
      "tt4480150",
      "tt0240510"
    ]
  }
  ```

- [requestMovie()](https://github.com/pedrohenrique-ql/concorrente-lab-base/blob/main/src/main/java/main/lab1/services/CineLsdDatabaseService.java#L40): Realiza requisição de um filme através do seu identificador:

  ```java
  // Chamada exemplo
  CineLsdDatabaseService.requestMovie("tt0276751");

  // Retorno
  {
    "id": "tt0276751",
    "title": "About a Boy",
    "averageRating": 7.1,
    "numberOfVotes": 184626,
    "startYear": 2002,
    "lengthInMinutes": 101,
    "genres": [
      "Comedy",
      "Drama",
      "Romance"
    ]
  }
  ```
