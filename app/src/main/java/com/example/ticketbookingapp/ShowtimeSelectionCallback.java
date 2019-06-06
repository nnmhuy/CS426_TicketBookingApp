package com.example.ticketbookingapp;

public interface SeatSelectionCallback {
    void seatSelectionCallback(Integer cinemaId, Integer showtimeId);
    Integer getSelectedCinemaId();
    Integer getSelectedShowtimeId();
}
