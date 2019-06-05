package com.example.ticketbookingapp.data;

import com.example.ticketbookingapp.data.model.MovieInfo;
import com.example.ticketbookingapp.data.model.Showtime;

import java.util.Date;
import java.util.List;

public class CinemaShowtimeRepository {
  private static CinemaShowtimeRepository instance;
  private DummyCinemaShowtimeDataSource dataSource;

  public CinemaShowtimeRepository(DummyCinemaShowtimeDataSource dataSource) {
    this.dataSource = dataSource;
  }

  public static CinemaShowtimeRepository getInstance(DummyCinemaShowtimeDataSource dataSource) {
    if (instance == null) {
      instance = new CinemaShowtimeRepository(dataSource);
    }
    return instance;
  }

  public List<Date> getListDates(Date startDate) {
    return this.dataSource.getListDates(startDate, 10);
  }

  public List<Showtime> getListShowtimes(Date startTime) {
    return this.dataSource.getListShowtimes(startTime, 10);
  }

  public MovieInfo getMovieInfo() {
    return this.dataSource.getMovieInfor();
  }
}
