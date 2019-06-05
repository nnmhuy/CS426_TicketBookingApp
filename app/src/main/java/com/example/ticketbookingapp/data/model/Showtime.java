package com.example.ticketbookingapp.data.model;

import java.util.Date;

public class Showtime {
  private Date time;
  private boolean available;

  public Showtime(Date time, boolean available) {
    this.time = time;
    this.available = available;
  }

  public Date getTime() {
    return time;
  }

  public boolean isAvailable() {
    return available;
  }
}
