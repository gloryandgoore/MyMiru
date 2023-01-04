package com.example.mymiru;

import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;

@Dao
public interface AnimeDAO {
    @Insert
    public void insertNewWishAnime(WishAnimes animes);
    @Query ("Select * from WishAnimes")
    public WishAnimes[] getAllWishAnimes();
    @Query("Select id,animeid from WishAnimes")
    public WishAnimes[] getTitleWishAnimes();
    @Delete
    void deleteWishAnimes(WishAnimes animes);
    @Query("Delete from WishAnimes where animeid=:animeid")
    public void deleteWishAnimes(String animeid);
    @Query("DELETE FROM WishAnimes")
    public void resetWishAnimes();


}
