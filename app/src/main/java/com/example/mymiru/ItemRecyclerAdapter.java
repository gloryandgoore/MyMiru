package com.example.mymiru;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;


import java.util.List;

public class ItemRecyclerAdapter extends RecyclerView.Adapter<ItemRecyclerAdapter.ItemViewHolder> {
    Context context;
    List<GenreItem> genreItemList;


    public ItemRecyclerAdapter(Context context, List<GenreItem> genreItemList) {
        this.context = context;
        this.genreItemList = genreItemList;
    }

    @NonNull
    @Override
    public ItemViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new ItemViewHolder(LayoutInflater.from(context).inflate(R.layout.genre_recycler_row_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull ItemViewHolder holder, int position) {

        Glide.with(context).load(genreItemList.get(position).getImageUrl()).into(holder.itemImage);

        holder.itemImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AnimeDetails.class);
                intent.putExtra("animeId",genreItemList.get(position).getId());
                intent.putExtra("animeName",genreItemList.get(position).getAnimeName());
                intent.putExtra("animeImageUrl",genreItemList.get(position).getImageUrl());
                intent.putExtra("animeSynopsis",genreItemList.get(position).getSynopsis());
                context.startActivity(intent);
            }
        });

    }

    @Override
    public int getItemCount() {
        return genreItemList.size();
    }

    public static final class ItemViewHolder extends RecyclerView.ViewHolder{

        ImageView itemImage;

        public ItemViewHolder(@NonNull View itemView) {
            super(itemView);

            itemImage = itemView.findViewById(R.id.item_image);

        }
    }
}
