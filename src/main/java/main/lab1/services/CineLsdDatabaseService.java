package main.lab1.services;

import com.google.gson.Gson;
import main.lab1.DTOs.Actor;
import main.lab1.DTOs.Movie;

import java.io.*;
import java.net.URI;
import java.net.URISyntaxException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;

public class CineLsdDatabaseService {
  private static String BASE_URL = "http://150.165.15.91:8001";
  private static String ACTORS_PREFIX = "/actors";
  private static String MOVIES_PREFIX = "/movies";
  
  public static Actor requestActor(String actorId) {
    try {
      String url = BASE_URL + ACTORS_PREFIX + "/" + actorId;
      HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).build();
      HttpClient httpClient = HttpClient.newHttpClient();
      HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());

      Gson gson = new Gson();
      Actor actor = gson.fromJson(response.body(), Actor.class);

      return actor;
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new RuntimeException(e);
    }
  }

  public static Movie requestMovie(String movieId) {
    try {
      String url = BASE_URL + MOVIES_PREFIX + "/" + movieId;
      HttpRequest request = HttpRequest.newBuilder().uri(new URI(url)).build();
      HttpClient httpClient = HttpClient.newHttpClient();
      HttpResponse<String> response = httpClient.send(request, HttpResponse.BodyHandlers.ofString());
  
      Gson gson = new Gson();
      Movie movie = gson.fromJson(response.body(), Movie.class);
  
      return movie;
    } catch (URISyntaxException e) {
      throw new RuntimeException(e);
    } catch (IOException e) {
      throw new RuntimeException(e);
    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      throw new RuntimeException(e);
    }
  }
}
