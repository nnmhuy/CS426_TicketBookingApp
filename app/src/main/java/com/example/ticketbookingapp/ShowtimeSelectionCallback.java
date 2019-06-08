package com.example.ticketbookingapp;

public interface ShowtimeSelectionCallback {
    void seatSelectionCallback(Integer cinemaId, Integer showtimeId);
    Integer getSelectedCinemaId();
    Integer getSelectedShowtimeId();
}
