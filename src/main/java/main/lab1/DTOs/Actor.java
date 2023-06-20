package main.lab1.DTOs;

import java.util.ArrayList;

public class Actor {

  private String id;
  private String name;
  private ArrayList<String> movies;

  private float rating;

  public String getId() {
    return id; 
  }

  public String getName() {
    return name;
  }

  public ArrayList<String> getMovies() {
    return movies;
  }

  public float getRating() {
    return rating;
  }

  public void setRating(float ratting) {
    this.rating = ratting;
  }

  @Override
  public String toString() {
    return "Actor: { " +
            "id: '" + id + '\'' +
            ", name: '" + name + '\'' +
            ", movies: " + movies +
            " }";
  }
}
