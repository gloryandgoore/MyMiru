package com.example.mymiru;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.ItemTouchHelper;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;

import com.google.android.material.snackbar.Snackbar;

import java.util.ArrayList;
import java.util.Arrays;
//ignore

public class animeWishList extends Fragment implements WishAnimesRecyclerView.ItemClickListener, DBManager.DataBaseListener {

    RecyclerView wishAnimesList;
    WishAnimesRecyclerView adapter;
    ArrayList<WishAnimes> wishAnimesArrayList = new ArrayList<>();
    LinearLayoutManager linearLayoutManager;
    FrameLayout frameLayout;




    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_anime_wish_list, container, false);
        wishAnimesList = view.findViewById(R.id.wishanime_list);
        frameLayout = view.findViewById(R.id.frame_layout_wish);
        return view;
    }

    @Override
    public void onResume() {
        super.onResume();
        ((MyApp)getActivity().getApplication()).dbManager.listener=this;
        ((MyApp)getActivity().getApplication()).dbManager.getAnimeDB(getActivity().getApplicationContext());
        ((MyApp)getActivity().getApplication()).dbManager.getAllWishAnimes();
        adapter = new WishAnimesRecyclerView(wishAnimesArrayList,getActivity().getApplicationContext());
        adapter.listener = this;
        linearLayoutManager = new LinearLayoutManager(getActivity().getApplicationContext());
        wishAnimesList.setLayoutManager(linearLayoutManager);
        ItemTouchHelper itemTouchHelper = new ItemTouchHelper(callback);
        itemTouchHelper.attachToRecyclerView(wishAnimesList);
    }

    @Override
    public void onItemClick(int pos) {

    }

    @Override
    public void insertingAnimeCompleted() {

    }

    @Override
    public void gettingAnimeCompleted(WishAnimes[] list) {
        wishAnimesArrayList = new ArrayList<>(Arrays.asList(list));
        adapter.wishAnimesList = wishAnimesArrayList;
        adapter.notifyDataSetChanged();

    }

    @Override
    public void gettingWishAnimesTitleCompleted(WishAnimes[] list) {

    }

    @Override
    public void deleteWishAnimesCompleted() {
        ((MyApp)getActivity().getApplication()).dbManager.getAllWishAnimes();

    }

    @Override
    public void deleteWishAnimesWithAnimeIDCompleted() {

    }

    @Override
    public void deleteAllWishAnimesCompleted() {

    }

    ItemTouchHelper.SimpleCallback callback = new ItemTouchHelper.SimpleCallback(0, ItemTouchHelper.LEFT) {
        @Override
        public boolean onMove(@NonNull RecyclerView recyclerView, @NonNull RecyclerView.ViewHolder viewHolder, @NonNull RecyclerView.ViewHolder target) {
            return false;
        }

        @Override
        public void onSwiped(@NonNull RecyclerView.ViewHolder viewHolder, int direction) {
            Snackbar.make(frameLayout, "Wish Anime cleared", Snackbar.LENGTH_SHORT).show();
            ((MyApp)getActivity().getApplication()).dbManager.deleteWishAnimes(adapter.getWishAnimesPosition(viewHolder.getAdapterPosition()));

        }
    };
}