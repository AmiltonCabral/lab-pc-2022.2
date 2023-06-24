# Laboratório Programação Concorrente 2022.2

Laboratório para implementação do CineLsd na linguagem Java, com foco na diferenciação da eficiencia entre o uso de threads sequenciais e concorrentes 
[Código base](https://github.com/pedrohenrique-ql/concorrente-lab-base).

## 🛠️ Construído com

- [Java 11+](https://www.oracle.com/br/java/technologies/javase/jdk11-archive-downloads.html) - Linguagem de implementação
- [Maven](https://maven.apache.org/) - Gerente de Dependência

## ✒️ Autores
*  **Amilton Cristian** - *Desenvolvedor especializado em programação sequencial.* - [AmiltonCabral](https://github.com/AmiltonCabral)
*  **Iago Silva** - *Desenvolvedor especializado em programação concorrente.* - [Iagohss](https://github.com/Iagohss)
*  **Joab Cesar** - *Responsável por testes, processamento e desenvolvimento auxiliar.* - [Joabcmp](https://github.com/joabcmp)
  embora o desenvolvimento do projeto foi dividido igualmente estando todos presentes em reuniões sincronas de forma online.

### 🍷🗿 Estrategias adotadas
- A estratégia principal de concorrência é o uso de threads para processar atores em paralelo. O programa cria um pool de threads executoras usando ExecutorService executor = Executors.newCachedThreadPool();. O programa utiliza um padrão de tarefa dividida em duas partes: ActorHandler e RankingHandler. A classe ActorHandler implementa a interface Runnable e é responsável por processar um ator específico. A classe RankingHandler implementa a interface Callable<String> e é responsável por calcular o ranking geral com base nas classificações dos atores processados.

- O programa utiliza uma fila compartilhada do tipo ConcurrentLinkedQueue<Actor> chamada actorsQueue para armazenar os atores processados pelos ActorHandlers.quando finaliza o processamento do ator o coloca em uma fila onde a thread rankingHandler intera, extraindo os dados para colocar no ranking, essa fila é thread-safe e permite que vários threads acessem e modifiquem a estrutura de dados sem causar condições de corrida.

- Para coordenar a execução dos threads e garantir que o programa principal aguarde a conclusão de todas as tarefas, é utilizado o CountDownLatch. O CountDownLatch latch é inicializado com o número de atores a serem processados (NUMBER_OF_ACTORS). Cada instância de ActorHandler chama o método latch.countDown() ao finalizar o processamento de um ator, indicando que a tarefa foi concluída. O programa principal chama latch.await() para aguardar até que todas as tarefas sejam concluídas antes de prosseguir.

- O programa também utiliza Callable<String> e Future<String> para lidar com a tarefa de cálculo do ranking. A instância de RankingHandler é submetida ao ExecutorService por meio do método executor.submit(rankingHandler), retornando um Future<String>. O Future<String> é usado para obter o resultado da tarefa de cálculo do ranking por meio do método ranking.get(). Essa abordagem permite que o programa principal aguarde a conclusão da tarefa de cálculo do ranking e obtenha seu resultado.

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
  
## ⚙️ Demonstração de possiveis resultados
*sequencial.java*

![image](https://github.com/AmiltonCabral/lab-pc-2022.2/assets/72311157/7d215ecd-9a49-41c0-a39c-3e40b9ab8231)

*Concorrent.java*

![WhatsApp Image 2023-06-23 at 20 46 01 (2)](https://github.com/AmiltonCabral/lab-pc-2022.2/assets/72311157/fbf83195-7ff8-4394-afc2-14fc7ded7466)
