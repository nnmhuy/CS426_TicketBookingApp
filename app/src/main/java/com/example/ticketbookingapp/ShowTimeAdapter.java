package com.example.ticketbookingapp;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ticketbookingapp.data.model.Showtime;

import java.util.List;

public class ShowTimeAdapter extends RecyclerView.Adapter<ShowTimeAdapter.TimeViewHolder> {
    private List<Showtime> showTimeList;
    private LayoutInflater showTimeInflater;
    public int cinemaId;
    private ShowtimeSelectionCallback showtimeSelectionCallback;

    public ShowTimeAdapter(Context context, List<Showtime> showTimeList, ShowtimeSelectionCallback showtimeSelectionCallback, Integer cinemaId) {
        showTimeInflater = LayoutInflater.from(context);
        this.showTimeList = showTimeList;
        this.showtimeSelectionCallback = showtimeSelectionCallback;
        this.cinemaId = cinemaId;
    }

    public ShowTimeAdapter(Context context, ShowtimeSelectionCallback showtimeSelectionCallback) {
        this.showTimeInflater = LayoutInflater.from(context);
        this.showtimeSelectionCallback = showtimeSelectionCallback;
        this.cinemaId = -1;

    }

    public void passParams(List<Showtime> showtimeList, int cinemaId) {
        this.showTimeList = showtimeList;
        this.cinemaId = cinemaId;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShowTimeAdapter.TimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View showTimeView = showTimeInflater.inflate(R.layout.available_time, parent, false);

        return new TimeViewHolder(showTimeView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull final ShowTimeAdapter.TimeViewHolder holder, int position) {
        Showtime mCurrent = showTimeList.get(position);
        holder.timeItemView.setText(String.valueOf(mCurrent.getTime().getHours()) + ":" + String.valueOf(mCurrent.getTime().getMinutes()) );

        int selectedCinema = showtimeSelectionCallback.getSelectedCinemaId();
        int selectedTime = showtimeSelectionCallback.getSelectedShowtimeId();
        if (mCurrent.isAvailable()) {
            if (position == selectedTime && selectedCinema == cinemaId) {
                holder.timeContainer.setBackgroundResource(R.drawable.selected_border);
                holder.timeItemView.setTextColor(Color.parseColor("#212224"));
            }
            else {
                holder.timeContainer.setBackgroundResource(R.drawable.available_border);
                holder.timeItemView.setTextColor(Color.parseColor("#5E636A"));
            }
            holder.timeContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer clickedPosition = holder.getAdapterPosition();
                    showtimeSelectionCallback.seatSelectionCallback(cinemaId, clickedPosition);
                }
            });
        } else {
            holder.timeContainer.setBackgroundResource(R.drawable.unavailble_border);
            holder.timeItemView.setTextColor(Color.parseColor("#AFB5BB"));
            holder.timeContainer.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    return;
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return showTimeList != null ? showTimeList.size() : 0;
    }

    public class TimeViewHolder extends  RecyclerView.ViewHolder {
        public final TextView timeItemView;
        public final LinearLayout timeContainer;
        final  ShowTimeAdapter timeAdapter;
        public TimeViewHolder(View itemView, ShowTimeAdapter adapter) {
            super(itemView);
            timeItemView = itemView.findViewById(R.id.time);
            timeContainer = itemView.findViewById(R.id.time_container);
            this.timeAdapter = adapter;
        }
    }
}
