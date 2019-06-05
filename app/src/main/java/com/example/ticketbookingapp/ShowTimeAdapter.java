package com.example.ticketbookingapp;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.example.ticketbookingapp.data.model.Showtime;

import java.util.List;

public class ShowTimeAdapter extends RecyclerView.Adapter<ShowTimeAdapter.TimeViewHolder> {
    private final List<Showtime> showTimeList;
    private LayoutInflater showTimeInflater;
    private int selectedTime = 0;

    public ShowTimeAdapter(Context context, List<Showtime> showTimeList) {
        showTimeInflater = LayoutInflater.from(context);
        this.showTimeList = showTimeList;
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
        if (mCurrent.isAvailable()) {
            if (position == selectedTime) {
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
                    if (showTimeList.get(clickedPosition).isAvailable()) {
                        selectedTime = clickedPosition;
                        Log.d("Clicked", String.valueOf(clickedPosition));
                        Log.d("Clicked", holder.timeItemView.getText().toString());
                        notifyDataSetChanged();
                    }
                }
            });
        } else {
            holder.timeContainer.setBackgroundResource(R.drawable.unavailble_border);
            holder.timeItemView.setTextColor(Color.parseColor("#AFB5BB"));
//            holder.timeContainer.setOnClickListener(new View.OnClickListener() {
//                @Override
//                public void onClick(View v) {
//                    return;
//                }
//            });
        }

    }

    @Override
    public int getItemCount() {
        return showTimeList.size();
    }

    public String getSelectedShowtime() {
        Showtime mCurrent = showTimeList.get(selectedTime);
        return String.valueOf(mCurrent.getTime().getHours()) + ":" + String.valueOf(mCurrent.getTime().getMinutes());
    }

    public Integer getSelectedShowtimeId() {
        return  selectedTime;
    }

    public void setSelectedShowtime(int showtimeId) {
        selectedTime = showtimeId;
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
