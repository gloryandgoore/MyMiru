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

public class AnimeAdapter extends
        RecyclerView.Adapter<AnimeAdapter.AnimeViewHolder> {

    interface ItemListener{
        void onClicked(int post);
    }

    Context context;
    ArrayList<WishAnimes> list;
    ItemListener listener;

    public AnimeAdapter(Context context, ArrayList<WishAnimes> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public AnimeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(context).inflate(R.layout.wish_list_row_items, parent,false);
        return new AnimeViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull AnimeViewHolder holder, int position) {
        holder.title.setText(list.get(position).getTitle());
        Glide.with(context).load(list.get(position).getImage()).into(holder.image);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class AnimeViewHolder extends RecyclerView.ViewHolder
            implements View.OnClickListener
    {
        ImageView image;
        TextView title;

        public AnimeViewHolder(@NonNull View itemView) {
            super(itemView);
            image =  itemView.findViewById(R.id.item_wish_image);
            title =  itemView.findViewById(R.id.item_text_wish);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            listener.onClicked( getAdapterPosition());

        }
    }


}
