package com.example.ticketbookingapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import com.example.ticketbookingapp.data.CinemaShowtimeRepository;
import com.example.ticketbookingapp.data.DummyCinemaShowtimeDataSource;
import com.example.ticketbookingapp.data.model.MovieInfo;
import com.example.ticketbookingapp.data.model.Showtime;

import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {

    private final String TAG = this.getClass().getSimpleName();
    private MovieInfo movieInfo;
    private List<Date> listDates;
    private List<Showtime> listTimes;

    private RecyclerView genreRecyclerView;
    private GenreAdapter genreAdapter;

    private RecyclerView dateRecyclerView;
    private DateAdapter dateAdapter;

    private RecyclerView showTimeRecyclerView;
    private ShowTimeAdapter showTimeAdapter;

    DummyCinemaShowtimeDataSource dataSource;
    CinemaShowtimeRepository dataRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (this.getResources().getConfiguration().orientation == OrientationHelper.VERTICAL) {
            setContentView(R.layout.activity_main);
        } else {
            setContentView(R.layout.activity_main_horizontal);
        }

        loadData();

        TextView movieTitleView = findViewById(R.id.movie_title);
        movieTitleView.setText(movieInfo.getMovieTitle());

        TextView votingScoreView = findViewById(R.id.voting_score);
        votingScoreView.setText(movieInfo.getVoteScore().toString());

        TextView runtimeView = findViewById(R.id.runtime);
        runtimeView.setText(movieInfo.getRuntime().toString());

        TextView synopsisView = findViewById(R.id.synopsis);
        synopsisView.setText(movieInfo.getSynopsis());

        genreRecyclerView = findViewById(R.id.genres_list_view);
        genreAdapter = new GenreAdapter(this, movieInfo.getListGenres());
        genreRecyclerView.setAdapter(genreAdapter);
        genreRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));


        dateRecyclerView = findViewById(R.id.date_selection);
        dateAdapter = new DateAdapter(this, listDates);
        dateRecyclerView.setAdapter(dateAdapter);
        dateRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));

        showTimeRecyclerView = findViewById(R.id.time_selection);
        showTimeAdapter = new ShowTimeAdapter(this, listTimes);
        showTimeRecyclerView.setAdapter(showTimeAdapter);
        showTimeRecyclerView.setLayoutManager(new LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false));
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        Integer selectedDate = dateAdapter.getSelectedDateId();
        Integer selectedTime = showTimeAdapter.getSelectedShowtimeId();

        outState.putInt("showday", selectedDate);
        outState.putInt("showtime", selectedTime);
        super.onSaveInstanceState(outState);
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if (savedInstanceState != null) {
            Log.d("here", "yeah");
            dateAdapter.setSelectedDate(savedInstanceState.getInt("showday"));
            showTimeAdapter.setSelectedShowtime(savedInstanceState.getInt("showtime"));
        }
    }

    private void loadData() {
        dataSource = DummyCinemaShowtimeDataSource.getInstance();
        dataRepository = CinemaShowtimeRepository.getInstance(dataSource);

        //    get the sample movie information data from data repository
        movieInfo = dataRepository.getMovieInfo();
        Log.d(TAG, String.format("Movie title: %s", movieInfo.getMovieTitle()));

        //    get list of date from data repository with start date. It will get 10 dates from dummy
        // data source
        Date startDate = new Date(2019, 05, 28);
        listDates = dataRepository.getListDates(startDate);
        for (Date date : listDates) {
            Log.d(
                    TAG,
                    String.format("d/M/y = %d / %d / %d ;", date.getDate(), date.getMonth(), date.getYear()));
        }

        //    get list of showtimes from data repository with starting time. It will get 10 showtimes
        // from dummy data source
        Date startTime = new Date(0, 0, 0, 10, 30);
        listTimes = dataRepository.getListShowtimes(startTime);
        for (Showtime time : listTimes) {
            Log.d(
                    TAG,
                    String.format(
                            "h:m = %d : %d; is available = %b",
                            time.getTime().getHours(), time.getTime().getMinutes(), time.isAvailable()));
        }
    }

    public void goNextPage(View view) {
        String selectedDate = dateAdapter.getSelectedDate();
        String selectedTime = showTimeAdapter.getSelectedShowtime();

        Intent intent = new Intent(this, BookingActivity.class);
        intent.putExtra("movie_title", movieInfo.getMovieTitle());
        intent.putExtra("showday", selectedDate);
        intent.putExtra("showtime", selectedTime);
        startActivity(intent);
    }
}
