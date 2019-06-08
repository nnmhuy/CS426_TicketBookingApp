package com.example.ticketbookingapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.ticketbookingapp.data.model.Showtime;

import java.util.List;

public class CinemaListAdapter extends RecyclerView.Adapter<CinemaListAdapter.CinemaViewHolder> implements ShowtimeSelectionCallback {
    private final List<Cinema> cinemaList;
    private LayoutInflater cinemaListInflater;
    private Integer lastSelectedCinema = -1;
    private Integer selectedCinema = 0;
    private Integer selectedShowtime = 0;
    Context context;

    CinemaListAdapter(Context context, List<Cinema> cinemaList) {
        this.cinemaList = cinemaList;
        cinemaListInflater = LayoutInflater.from(context);
        this.context = context;
    }

    @NonNull
    @Override
    public CinemaViewHolder onCreateViewHolder(ViewGroup viewGroup, int i) {
        View cinemaView = cinemaListInflater.inflate(R.layout.cinema_time, viewGroup, false);
        return new CinemaViewHolder(cinemaView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull CinemaViewHolder cinemaViewHolder, int position) {
        Cinema mCurrent = cinemaList.get(position);
        cinemaViewHolder.cinemaNameView.setText(mCurrent.cinemaName);

        if (cinemaViewHolder.showTimeAdapter == null) {
            cinemaViewHolder.showTimeAdapter = new ShowTimeAdapter(context, mCurrent.showtimeList, this, position);
            cinemaViewHolder.cinemaItemView.setAdapter(cinemaViewHolder.showTimeAdapter);
            cinemaViewHolder.cinemaItemView.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        }

        if (position != cinemaViewHolder.showTimeAdapter.cinemaId){
            cinemaViewHolder.showTimeAdapter.passParams(mCurrent.showtimeList, position);
        }

        Log.d("Bind: ", String.valueOf(position));
        Log.d("InsideID: ", String.valueOf(cinemaViewHolder.showTimeAdapter.cinemaId));
        cinemaViewHolder.showTimeAdapter.notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return cinemaList.size();
    }

    @Override
    public void seatSelectionCallback(Integer cinemaId, Integer showtimeId) {
        lastSelectedCinema = selectedCinema;
        selectedCinema = cinemaId;
        selectedShowtime = showtimeId;
        notifyDataSetChanged();
    }

    @Override
    public Integer getSelectedCinemaId() {
        return selectedCinema;
    }

    @Override
    public Integer getSelectedShowtimeId() {
        return selectedShowtime;
    }

    public String getSelectedShowtime() {
        Showtime mCurrent = cinemaList.get(selectedCinema).showtimeList.get(selectedShowtime);
        return String.valueOf(mCurrent.getTime().getHours()) + ":" + String.valueOf(mCurrent.getTime().getMinutes());
    }

    public String getSelectedCinema() {
        return cinemaList.get(selectedCinema).cinemaName;
    }

    public class CinemaViewHolder extends RecyclerView.ViewHolder {
        public final RecyclerView cinemaItemView;
        public final TextView cinemaNameView;
        public ShowTimeAdapter showTimeAdapter;
        final public CinemaListAdapter cinemaListAdapter;


        public CinemaViewHolder(@NonNull View itemView, CinemaListAdapter cinemaListAdapter) {
            super(itemView);
            cinemaItemView = itemView.findViewById(R.id.time_selection);
            cinemaNameView = itemView.findViewById(R.id.cinema_name);
            this.cinemaListAdapter = cinemaListAdapter;
        }
    }
}
