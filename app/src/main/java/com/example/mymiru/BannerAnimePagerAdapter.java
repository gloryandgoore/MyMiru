package com.example.mymiru;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.bumptech.glide.Glide;

import java.util.List;

public class BannerAnimePagerAdapter extends PagerAdapter {

    Context context;
    List<HeaderAnimes> headerAnimesList;


    public BannerAnimePagerAdapter(Context context, List<HeaderAnimes> headerAnimesList) {
        this.context = context;
        this.headerAnimesList = headerAnimesList;
    }

    @Override
    public int getCount() {
        return headerAnimesList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view == object;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, Object object) {
        container.removeView((View) object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {

        View view = LayoutInflater.from(context).inflate(R.layout.banner_anime_layout, null);
        ImageView bannerImage = view.findViewById(R.id.banner_image);
        Glide.with(context).load(headerAnimesList.get(position).getImageUrl()).into(bannerImage);
        container.addView(view);

        bannerImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, AnimeDetails.class);
                intent.putExtra("id",headerAnimesList.get(position).getId());
                intent.putExtra("name",headerAnimesList.get(position).getAnimeName());
                intent.putExtra("image",headerAnimesList.get(position).getImageUrl());
                intent.putExtra("synopsis",headerAnimesList.get(position).getSynopsis());
                context.startActivity(intent);

            }
        });
        return view;
    }
}
