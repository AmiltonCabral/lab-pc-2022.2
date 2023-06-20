package main.lab1.DTOs;

import java.util.ArrayList;

public class Movie {
  private String id;
  private String title;
  private float averageRating;
  private int numberOfVotes;
  private int startYear;
  private int lengthInMinutes;
  private ArrayList<String> genres;

  public String getId() {
    return id;
  }
  
  public String getTitle() {
    return title;
  }

  public float getAverageRating() {
    return averageRating;
  }

  public int getNumberOfVotes() {
    return numberOfVotes;
  }

  public int getStartYear() {
    return startYear;
  }

  public int getLengthInMinutes() {
    return lengthInMinutes;
  }

  public ArrayList<String> getGenres() {
    return genres;
  }

  @Override
  public String toString() {
    return "Movie: { " +
            "id: '" + id + '\'' +
            ", title: '" + title + '\'' +
            ", averageRating: " + averageRating +
            ", numberOfVotes: " + numberOfVotes +
            ", startYear: " + startYear +
            ", lengthInMinutes: " + lengthInMinutes +
            ", genres: " + genres +
            " }";
  }
}
