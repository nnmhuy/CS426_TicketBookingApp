package com.example.ticketbookingapp.data;

import com.example.ticketbookingapp.data.model.MovieInfo;
import com.example.ticketbookingapp.data.model.Showtime;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Date;
import java.util.List;

public class DummyCinemaShowtimeDataSource {

  private static DummyCinemaShowtimeDataSource instance;

  public static DummyCinemaShowtimeDataSource getInstance() {
    if (instance == null) {
      instance = new DummyCinemaShowtimeDataSource();
    }
    return instance;
  }

  public List<Date> getListDates(Date startDate, int nDate) {
    List<Date> listDates = new ArrayList<>();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(startDate);
    for (int i = 0; i < nDate; i++) {
      listDates.add(calendar.getTime());

      // increase 1 date
      calendar.add(Calendar.DATE, 1);
    }
    return listDates;
  }

  public List<Showtime> getListShowtimes(Date startTime, int nTime) {
    List<Showtime> listShowtimes = new ArrayList<>();
    Calendar calendar = Calendar.getInstance();
    calendar.setTime(startTime);
    boolean available;
    for (int i = 0; i < nTime; i++) {
      Date showtimeTemp = calendar.getTime();

      //    some showtimes not available
      if (showtimeTemp.getMinutes() <= 15) {
        available = false;
      } else {
        available = true;
      }
      listShowtimes.add(new Showtime(calendar.getTime(), available));

      //      increase 45 minutes
      calendar.add(Calendar.MINUTE, 45);
    }
    return listShowtimes;
  }

  public MovieInfo getMovieInfor() {
    return new MovieInfo(
        "Avengers: Endgame",
        49,
        111,
        "IMAX 3D",
        "After the devastating events of Avengers: Infinity War, the universe is in ruins due to the efforts of the Mad Titan, Thanos. With the help of remaining allies, the Avengers must assemble once more in order to undo Thanos' actions and restore order to the universe once and for all, no matter what consequences may be in store.",
        Arrays.asList("Adventure", "Science Fiction", "Action"));
  }
}
