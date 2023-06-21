package main.lab1;

import java.util.ArrayList;

import main.lab1.DTOs.Actor;
import main.lab1.DTOs.Movie;
import main.lab1.services.CineLsdDatabaseService;
import main.lab1.utils.FileIOUtil;

public class App {
  private static String ACTORS_DATA_PATH = "./data/actors.txt";
  private static int NUMBER_OF_ACTORS = 20;

  public static void main(String[] args) throws InterruptedException {
    long startTime = System.nanoTime();
    ArrayList<String> actorIds = FileIOUtil.readFile(ACTORS_DATA_PATH, NUMBER_OF_ACTORS);
    Actor[] actorsArr = new Actor[10];

    for (String actorId : actorIds) {
      Actor actor = CineLsdDatabaseService.requestActor(actorId);
      if (actor == null || actor.getMovies().size() <= 0) {
        continue;
      }

      float totalRating = 0;

      for (String movieId : actor.getMovies()) {
        Movie movie = CineLsdDatabaseService.requestMovie(movieId);

        if (movie != null) {
          totalRating += movie.getAverageRating();
        }
      }

      actor.setRating(totalRating / actor.getMovies().size());

      if (actorsArr[9] == null || actor.getRating() > actorsArr[9].getRating()) {
        sortInsertion(actorsArr, actor);
      }
    }

    for (Actor actor : actorsArr) {
      if (actor != null) {
        System.out.println(actor);
      }
    }

    System.out.println("\n--------------------------------------------------" +
        "\nTotal execution time in millis: " + ((System.nanoTime() - startTime) / 1000000));
  }

  public static void sortInsertion(Actor[] Actors, Actor newActor) {
    Actors[9] = newActor;

    int j = Actors.length - 1;

    while (j > 0 && (Actors[j - 1] == null || Actors[j].getRating() > Actors[j - 1].getRating())) {
      Actor aux = Actors[j];
      Actors[j] = Actors[j - 1];
      Actors[j - 1] = aux;
      j -= 1;
    }
  }

}
