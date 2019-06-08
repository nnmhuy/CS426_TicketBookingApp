package com.example.ticketbookingapp;

import com.example.ticketbookingapp.data.model.Showtime;

import java.util.List;

class Cinema {
    public String cinemaName;
    public List<Showtime> showtimeList;
    Cinema(String name, List<Showtime> showtimeList) {
        this.cinemaName = name;
        this.showtimeList = showtimeList;
    }
}
