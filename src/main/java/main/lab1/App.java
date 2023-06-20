package main.lab1;

import java.util.ArrayList;

import main.lab1.DTOs.Actor;
import main.lab1.DTOs.Movie;
import main.lab1.services.CineLsdDatabaseService;
import main.lab1.utils.FileIOUtil;

public class App {
  private static String ACTORS_DATA_PATH = "./data/actors.txt";
  private static int NUMBER_OF_ACTORS = 1000;
  
  public static void main(String[] args) throws InterruptedException {
    long startTime = System.nanoTime();
    
    Actor actor = CineLsdDatabaseService.requestActor("nm1179580");
    System.out.println(actor);
    
    Movie movie = CineLsdDatabaseService.requestMovie("tt7825208");
    System.out.println(movie);
    
    ArrayList<String> actorIds = FileIOUtil.readFile(ACTORS_DATA_PATH, NUMBER_OF_ACTORS);
    System.out.println(actorIds);
    
    System.out.println("\n--------------------------------------------------" +
      "\nTotal execution time in millis: " + ((System.nanoTime() - startTime)/1000000));
    }
}

