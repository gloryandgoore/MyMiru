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

public class AnimeDetailsRecyclerView extends
        RecyclerView.Adapter<AnimeDetailsRecyclerView.AnimeDetailsViewHolder> {


    public AnimeDetailsRecyclerView(ArrayList<Anime> animeArrayList, Context context) {
        this.animeArrayList = animeArrayList;
        this.context = context;
    }

    public ArrayList<Anime> animeArrayList;
    Context context;
    public ItemClickListener listener;

    public interface ItemClickListener{
        public void onItemClick(int pos);

        void gettingJsonIsCompleted(String jsonString);
    }
    @NonNull
    @Override
    public AnimeDetailsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v= LayoutInflater.from(context).inflate(R.layout.anime_list,parent,false);
        return new AnimeDetailsViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeDetailsViewHolder holder, int position) {
        holder.animeTitle.setText(animeArrayList.get(position).getTitle());
        Glide.with(context).load(animeArrayList.get(position).getImage()).into(holder.img);

    }

    @Override
    public int getItemCount() {
        return animeArrayList.size();
    }

    public class AnimeDetailsViewHolder extends RecyclerView.ViewHolder{
        TextView animeTitle;
        ImageView img;
        public AnimeDetailsViewHolder(@NonNull View itemView) {
            super(itemView);
            animeTitle = itemView.findViewById(R.id.animeTitle);
            img = itemView.findViewById(R.id.animeImage);
            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    listener.onItemClick(getAdapterPosition());
                }
            });

        }
    }
}
