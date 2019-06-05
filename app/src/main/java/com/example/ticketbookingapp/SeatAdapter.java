package com.example.ticketbookingapp;

import android.content.Context;
import android.graphics.Color;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import java.util.List;

public class SeatAdapter extends RecyclerView.Adapter<SeatAdapter.SeatViewHolder>{
    private List<Integer> seatStatus;
    private LayoutInflater seatInflater;
    Integer selectedSeat = 0;
    AdapterCallback adapterCallback;

    public  SeatAdapter(Context context, List<Integer> seatStatus) {
        adapterCallback = (AdapterCallback) context;
        this.seatStatus = seatStatus;
        seatInflater = LayoutInflater.from(context);
    }

    @NonNull
    @Override
    public SeatViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View seatView;

        seatView = seatInflater.inflate(R.layout.seat, parent, false);

        return new SeatViewHolder(seatView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull final SeatViewHolder seatViewHolder, int position) {
        Integer mCurrent = seatStatus.get(position);
        if (mCurrent == 0) {
            seatViewHolder.seatView.setImageAlpha(0);
        } else if (mCurrent == 1) {
            seatViewHolder.seatView.setColorFilter(Color.parseColor("#EBECED"));
            seatViewHolder.seatView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer clickedPosition = seatViewHolder.getAdapterPosition();
                    seatStatus.set(clickedPosition, 3);
                    ++selectedSeat;
                    notifyDataSetChanged();
                    adapterCallback.onMethodCallback(selectedSeat);
                }
            });
        } else if (mCurrent == 2) {
            seatViewHolder.seatView.setColorFilter(Color.parseColor("#212224"));
        } else if (mCurrent == 3) {
            seatViewHolder.seatView.setColorFilter(Color.parseColor("#0C4CA8"));
            seatViewHolder.seatView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Integer clickedPosition = seatViewHolder.getAdapterPosition();
                    seatStatus.set(clickedPosition, 1);
                    --selectedSeat;
                    notifyDataSetChanged();
                    adapterCallback.onMethodCallback(selectedSeat);
                }
            });
        }
    }


    @Override
    public int getItemCount() {
        return seatStatus.size();
    }

    public class SeatViewHolder extends RecyclerView.ViewHolder {
        public final ImageView seatView;
        final SeatAdapter seatAdapter;

        public SeatViewHolder(@NonNull View itemView, SeatAdapter seatAdapter) {
            super(itemView);
            getAdapterPosition();
            seatView = itemView.findViewById(R.id.seat);
            this.seatAdapter = seatAdapter;
        }
    }
}
