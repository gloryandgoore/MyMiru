package com.example.mymiru;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;



import java.util.List;

public class MainRecyclerAdapter extends RecyclerView.Adapter<MainRecyclerAdapter.MainViewHolder> {

    Context context;
    List<AllGenres> AllGenresList;

    public MainRecyclerAdapter(Context context, List<AllGenres> allGenresList) {
        this.context = context;
        AllGenresList = allGenresList;
    }

    @NonNull
    @Override
    public MainViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
       View v = LayoutInflater.from(context).inflate(R.layout.main_recycler_row_item, parent, false);
        return new MainViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MainViewHolder holder, int position) {
        holder.genreName.setText(AllGenresList.get(position).getGenreId());
        holder.setItemRecycler(holder.recyclerView,AllGenresList.get(position).getGenreItemList());

    }

    @Override
    public int getItemCount() {
        return AllGenresList.size();
    }


    public final class MainViewHolder extends RecyclerView.ViewHolder{

        TextView genreName;
        RecyclerView recyclerView;

        public MainViewHolder(@NonNull View itemView) {
            super(itemView);

            genreName = itemView.findViewById(R.id.item_category);
            recyclerView = itemView.findViewById(R.id.item_recycler);
        }
        public void setItemRecycler(RecyclerView recyclerView, List<GenreItem> genreItemList){
            ItemRecyclerAdapter itemRecyclerAdapter = new ItemRecyclerAdapter(context, genreItemList);
            recyclerView.setLayoutManager(new LinearLayoutManager(recyclerView.getContext(), LinearLayoutManager.HORIZONTAL, false));
            recyclerView.setAdapter(itemRecyclerAdapter);
    }


    }
}
