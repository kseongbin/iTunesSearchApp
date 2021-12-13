package com.example.itunessearchapp;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.ArrayList;
import java.util.List;

public class RecyclerViewAdapter extends RecyclerView.Adapter {

    Context context;
    List<ApiResult> results;

    public RecyclerViewAdapter(Context context, List<ApiResult> results) {
        this.context = context;
        this.results = results;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);

        View itemView = inflater.inflate(R.layout.item_recyclerview, parent, false);
        VH holder = new VH(itemView);

        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        VH vh = (VH)holder;
        ApiResult item = results.get(position);

        vh.name.setText(item.trackName);
        vh.time.setText(item.trackTimeMillis);
        vh.collection.setText(item.collectionName);
        vh.year.setText(item.releaseDate);
        vh.artist.setText(item.artistName);
        Glide.with(context).load(item.artworkUrl100).into(vh.iv);

    }

    @Override
    public int getItemCount() {
        return results.size();
    }

    class VH extends RecyclerView.ViewHolder {

        ImageView iv;
        TextView name,time,collection,year,artist;

        public VH(@NonNull View itemView) {
            super(itemView);

            iv= itemView.findViewById(R.id.iv);
            name= itemView.findViewById(R.id.name);
            time= itemView.findViewById(R.id.time);
            collection= itemView.findViewById(R.id.collection);
            year= itemView.findViewById(R.id.year);
            artist= itemView.findViewById(R.id.artist);
        }
    }

}
