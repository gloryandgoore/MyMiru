package com.example.mymiru;

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

public class WishAnimesRecyclerView extends RecyclerView.Adapter<WishAnimesRecyclerView.WishAnimesInfoViewHolder> {

    public WishAnimesRecyclerView(ArrayList<WishAnimes> wishAnimesList, Context context) {
        this.wishAnimesList = wishAnimesList;
        this.context = context;
    }

    public ArrayList<WishAnimes> wishAnimesList;
    Context context;
    public WishAnimesRecyclerView.ItemClickListener listener;

    public interface ItemClickListener{
        public void onItemClick(int pos);
    }

    @NonNull
    @Override
    public WishAnimesRecyclerView.WishAnimesInfoViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.anime_list,parent,false);
        return new WishAnimesRecyclerView.WishAnimesInfoViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull WishAnimesRecyclerView.WishAnimesInfoViewHolder holder, int position) {
        holder.animeTitle.setText(wishAnimesList.get(position).getTitle());
        Glide.with(context).load(wishAnimesList.get(position).getImage()).into(holder.img);

    }

    public WishAnimes getWishAnimesPosition(int position){
        return wishAnimesList.get(position);
    }

    @Override
    public int getItemCount() {
        return wishAnimesList.size();
    }

    public class WishAnimesInfoViewHolder extends RecyclerView.ViewHolder{
        ImageView img;
        TextView animeTitle;
        public WishAnimesInfoViewHolder(@NonNull View itemView){
            super(itemView);
            img = itemView.findViewById(R.id.animeImage);
            animeTitle = itemView.findViewById(R.id.animeTitle);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(getAdapterPosition());
                }
            });
        }
    }


}
