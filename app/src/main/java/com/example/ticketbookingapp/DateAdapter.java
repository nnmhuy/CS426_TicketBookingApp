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

import java.util.Date;
import java.util.List;

public class DateAdapter extends RecyclerView.Adapter<DateAdapter.DateViewHolder> {
    private final List<Date> dateList;
    private LayoutInflater dateInflater;
    private String[] weekdayText = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT" };
    private int selectedDate = -1;

    public DateAdapter(Context context, List<Date> dateList) {
        dateInflater = LayoutInflater.from(context);
        this.dateList = dateList;
    }

    @NonNull
    @Override
    public DateAdapter.DateViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int i) {
        View DateView;

        DateView = dateInflater.inflate(R.layout.available_date, parent, false);

        return new DateViewHolder(DateView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull final DateAdapter.DateViewHolder holder, final int position) {
        Date mCurrent = dateList.get(position);
        holder.weekDayTextView.setText(weekdayText[mCurrent.getDay()]);
        holder.dayTextView.setText(String.valueOf(mCurrent.getDate()));
        if (position == selectedDate) {
            holder.dateContainer.setBackgroundResource(R.drawable.selected_border);
            holder.weekDayTextView.setTextColor(Color.parseColor("#212224"));
            holder.dayTextView.setTextColor(Color.parseColor("#212224"));
        } else {
            holder.dateContainer.setBackgroundResource(R.drawable.available_border);
            holder.weekDayTextView.setTextColor(Color.parseColor("#C4C9DF"));
            holder.dayTextView.setTextColor(Color.parseColor("#C4C9DF"));
        }
        holder.dateContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer clickedPosition = holder.getAdapterPosition();
                selectedDate = clickedPosition;
                notifyDataSetChanged();
            }
        });
    }

    @Override
    public int getItemCount() {
        return dateList.size();
    }

    public String getSelectedDate() {
        Date mCurrent = dateList.get(selectedDate);
        return (weekdayText[mCurrent.getDay()] + ", " + String.valueOf(mCurrent.getDate()));
    }

    public void setSelectedDate(Integer showdayId) {
        selectedDate = showdayId;
    }

    public Integer getSelectedDateId() {
        return selectedDate;
    }

    public class DateViewHolder extends  RecyclerView.ViewHolder {
        public final LinearLayout dateContainer;
        public final TextView weekDayTextView;
        public final TextView dayTextView;
        final DateAdapter dateAdapter;
        public DateViewHolder(@NonNull View itemView, DateAdapter dateAdapter) {
            super(itemView);
            dateContainer = itemView.findViewById(R.id.date);
            weekDayTextView = itemView.findViewById(R.id.weekday);
            dayTextView = itemView.findViewById(R.id.day);
            this.dateAdapter = dateAdapter;
        }

    }
}
