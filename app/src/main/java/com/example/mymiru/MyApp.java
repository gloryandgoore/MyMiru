package com.example.mymiru;

import android.app.Application;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class MyApp extends Application {

    public static ExecutorService executorService = Executors.newFixedThreadPool(6);
    NetworkingService networkingService = new NetworkingService();
    public DBManager dbManager = new DBManager();
    public int pos;
    JsonService jsonService = new JsonService();
    public String query = " ";
    public SearchAnimes searchAnimes = new SearchAnimes();
}
