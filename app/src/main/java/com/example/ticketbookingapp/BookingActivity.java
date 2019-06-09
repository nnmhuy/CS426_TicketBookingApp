package com.example.ticketbookingapp;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Arrays;
import java.util.List;

public class BookingActivity extends AppCompatActivity implements AdapterCallback {
    final List<Integer> seatStatus;
    int numberOfSelectedSeats = 0;

    public BookingActivity() {
        seatStatus = Arrays.asList(
                0, 0, 1, 0, 1, 1, 1, 1, 0, 1, 0, 0,
                0, 1, 1, 0, 1, 1, 2, 2, 0, 1, 1, 0,
                1, 1, 1, 0, 1, 1, 2, 2, 0, 1, 1, 1,
                2, 2, 2, 0, 1, 1, 2, 2, 0, 1, 2, 2,
                1, 1, 1, 0, 1, 1, 2, 2, 0, 1, 2, 2,
                0, 0, 0, 0, 2, 2, 1, 1, 0, 0, 0, 0,
                1, 1, 1, 0, 2, 2, 1, 1, 0, 1, 1, 1,
                1, 1, 2, 0, 1, 1, 2, 2, 0, 1, 1, 1,
                1, 1, 1, 0, 1, 1, 2, 2, 0, 2, 2, 1,
                0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0, 0,
                1, 1, 1, 0, 1, 1, 2, 2, 0, 1, 1, 1,
                2, 1, 1, 0, 1, 1, 1, 1, 0, 1, 1, 1);
    }

    private RecyclerView seatRecyclerView;
    private SeatAdapter seatAdapter;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        Intent intent = getIntent();
        String movie_title = intent.getStringExtra("movie_title");
        String showday = intent.getStringExtra("showday");
        String cinema_name = intent.getStringExtra("cinema_name");
        String showtime = intent.getStringExtra("showtime");

        setContentView(R.layout.booking_screen);

        TextView movieTitleView = findViewById(R.id.movie_title);
        movieTitleView.setText(movie_title);

        TextView showdayView = findViewById(R.id.showday);
        showdayView.setText(showday);

        TextView cinemaNameView = findViewById(R.id.cinema_name);
        cinemaNameView.setText(cinema_name);

        TextView showtimeView = findViewById(R.id.showtime);
        showtimeView.setText(showtime);

        ImageView backButton = findViewById(R.id.back_button);
        backButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", "back");
                setResult(Activity.RESULT_CANCELED, returnIntent);
                finish();
            }
        });

        ImageView fabButton = findViewById(R.id.fab);
        fabButton.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View v) {
                Toast.makeText(getBaseContext(), "You booked " + numberOfSelectedSeats + " tickets successfully.", Toast.LENGTH_SHORT).show();
                Intent returnIntent = new Intent();
                returnIntent.putExtra("result", "finish_booking");
                setResult(Activity.RESULT_OK, returnIntent);
                finish();
            }
        });

        seatRecyclerView = findViewById(R.id.seat_container);
        seatAdapter = new SeatAdapter(this, seatStatus);
        seatRecyclerView.setAdapter(seatAdapter);
        seatRecyclerView.setLayoutManager(new GridLayoutManager(this, 12));

    }

    @Override
    public void onMethodCallback(Integer numberOfSelectedSeats) {
        this.numberOfSelectedSeats = numberOfSelectedSeats;
        TextView NumberOfTicketView = findViewById(R.id.number_ticket);
        NumberOfTicketView.setText("x" + numberOfSelectedSeats);

        TextView TotalPaymentView = findViewById(R.id.total_payment);
        TotalPaymentView.setText("$" + (numberOfSelectedSeats * 5));
    }
}
