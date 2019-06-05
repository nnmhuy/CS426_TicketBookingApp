package com.example.ticketbookingapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class GenreAdapter extends RecyclerView.Adapter<GenreAdapter.GenreViewHolder> {
    private final List<String> listGenre;
    private LayoutInflater genreInflater;

    public  GenreAdapter(Context context, List<String> listGenre) {
        genreInflater = LayoutInflater.from(context);
        this.listGenre = listGenre;
    }

    @NonNull
    @Override
    public GenreViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View GenreView = genreInflater.inflate(R.layout.genres_item, viewGroup, false);

        return new GenreViewHolder(GenreView, this);
    }

    @Override
    public void onBindViewHolder(@NonNull GenreViewHolder holder, int i) {
        String mCurrent = listGenre.get(i);
        holder.genreItemView.setText(mCurrent);
    }

    @Override
    public int getItemCount() {
        return listGenre.size();
    }

    public class GenreViewHolder extends RecyclerView.ViewHolder {
        public final TextView genreItemView;
        final GenreAdapter genreAdapter;
        public GenreViewHolder(@NonNull View itemView, GenreAdapter genreAdapter) {
            super(itemView);
            genreItemView = itemView.findViewById(R.id.genre_item);
            this.genreAdapter = genreAdapter;
        }
    }
}
