package com.example.ticketbookingapp.data.model;

import java.util.List;

public class MovieInfo {
  private String movieTitle;
  private Integer voteScore;
  private Integer runtime;
  private String movieFormat;
  private String synopsis;
  private List<String> listGenres;

  public MovieInfo(
      String movieTitle,
      Integer voteScore,
      Integer runtime,
      String movieFormat,
      String synopsis,
      List<String> listGenres) {
    this.movieTitle = movieTitle;
    this.voteScore = voteScore;
    this.runtime = runtime;
    this.movieFormat = movieFormat;
    this.synopsis = synopsis;
    this.listGenres = listGenres;
  }

  public String getMovieTitle() {
    return movieTitle;
  }

  public Integer getVoteScore() {
    return voteScore;
  }

  public Integer getRuntime() {
    return runtime;
  }

  public String getSynopsis() {
    return synopsis;
  }

  public List<String> getListGenres() {
    return listGenres;
  }

  public String getMovieFormat() {
    return movieFormat;
  }
}
