package main.lab1;

import java.util.ArrayList;

import main.lab1.DTOs.Actor;
import main.lab1.DTOs.Movie;
import main.lab1.services.CineLsdDatabaseService;
import main.lab1.utils.FileIOUtil;

public class App {
  private static String ACTORS_DATA_PATH = "./data/actors.txt";
  private static int NUMBER_OF_ACTORS = 10;

  public static void main(String[] args) throws InterruptedException {
    long startTime = System.nanoTime();
    ArrayList<String> actorIds = FileIOUtil.readFile(ACTORS_DATA_PATH, NUMBER_OF_ACTORS);

    for (String actorId : actorIds) {
      Actor actor = CineLsdDatabaseService.requestActor(actorId);
      if (actor == null || actor.getMovies().size() <= 0) {
        continue;
      }

      int totalRating = 0;

      for (String movieId : actor.getMovies()) {
        Movie movie = CineLsdDatabaseService.requestMovie(movieId);

        if (movie != null) {
          totalRating += movie.getAverageRating();
        }
      }

      actor.setRating(totalRating / actor.getMovies().size());
    }

    System.out.println(actorIds.size());

    System.out.println("\n--------------------------------------------------" +
        "\nTotal execution time in millis: " + ((System.nanoTime() - startTime) / 1000000));
  }
}
