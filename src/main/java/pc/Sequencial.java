package pc;

import java.util.ArrayList;

import pc.DTOs.Actor;
import pc.DTOs.Movie;
import pc.services.CineLsdDatabaseService;
import pc.utils.ActorUtil;
import pc.utils.FileIOUtil;

public class Sequencial {
  private static String ACTORS_DATA_PATH = "./data/actors.txt";
  private static int NUMBER_OF_ACTORS = 10000;

  public static void main(String[] args) throws InterruptedException {
    long startTime = System.nanoTime();
    ArrayList<String> actorIds = FileIOUtil.readFile(ACTORS_DATA_PATH, NUMBER_OF_ACTORS);
    Actor[] actorsArr = new Actor[10];
    int actorsCount = 0;

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
        ActorUtil.sortInsertion(actorsArr, actor);
      }

      System.out.println("Actors processed: " + ++actorsCount + " / " + NUMBER_OF_ACTORS);
    }

    System.out.println("\n--------------------------------------------------\n");
    for (Actor actor : actorsArr) {
      System.out.println(actor);
    }

    System.out.println("\n--------------------------------------------------\n" +
        "\nTotal execution time in millis: " + ((System.nanoTime() - startTime) / 1000000));
  }
}
