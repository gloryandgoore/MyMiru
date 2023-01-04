package com.example.mymiru;


import android.os.Handler;
import android.os.Looper;

import java.io.IOException;
import java.net.URL;

import java.io.BufferedInputStream;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;

public class NetworkingService {

    NetworkingListener listener;
    Handler handler = new Handler(Looper.getMainLooper());
    interface NetworkingListener{
        void gettingJsonIsCompleted(String json);
    }

    String animeURL1 = "https://anime-db.p.rapidapi.com/anime?page=1&size=10&search=" ;
    String animeURL2 =  "&sortBy=ranking&sortOrder=asc";
    String animeURL3 = "https://anime-db.p.rapidapi.com/anime/by-id/";
    String headerAnimeURL1 = "https://anime-db.p.rapidapi.com/anime?page=1&size=10&genres=";
    String headerAnimeURL2 = "&sortBy=ranking&sortOrder=asc";
    String genreURL = "https://anime-db.p.rapidapi.com/genre";
    String surpriseByRankURL = "https://anime-db.p.rapidapi.com/anime/by-ranking/";

    String inputValue = " ";

    void getAllAnimes(String query) {
        String fullString = animeURL1 + query + animeURL2;
        MyApp.executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    int value = 0;
                    URL urlObj = new URL(fullString);
                    System.out.println(fullString);
                    HttpURLConnection urlConnection = (HttpURLConnection) urlObj.openConnection();
                    urlConnection.setRequestProperty("X-RapidAPI-Key", "c6d174dc42msh926955b2f690e00p1e37fdjsn6b6fedb2172d");
                    urlConnection.setRequestProperty("X-RapidAPI-Host", "anime-db.p.rapidapi.com");
                    InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                    StringBuffer buffer = new StringBuffer();


                    while ((value = inputStream.read()) !=-1){
                        inputValue = String.valueOf(buffer.append((char) value));
                    }
                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.gettingJsonIsCompleted(inputValue);
                        }
                    });
                } catch (MalformedURLException e){
                    e.printStackTrace();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }

    void getAllGenres(String genreURL) {
        MyApp.executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    int value = 0;
                    URL urlObj = new URL(genreURL);
                    System.out.println(genreURL);
                    HttpURLConnection urlConnection = (HttpURLConnection) urlObj.openConnection();
                    urlConnection.setRequestProperty("X-RapidAPI-Key", "c6d174dc42msh926955b2f690e00p1e37fdjsn6b6fedb2172d");
                    urlConnection.setRequestProperty("X-RapidAPI-Host", "anime-db.p.rapidapi.com");
                    InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                    StringBuffer buffer = new StringBuffer();


                    while ((value = inputStream.read()) !=-1){
                        inputValue = String.valueOf(buffer.append((char) value));
                    }

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.gettingJsonIsCompleted(inputValue);
                        }
                    });
                } catch (MalformedURLException e){
                    e.printStackTrace();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }

    public String randomRank(){
        int min = 1;
        int max = 5000;

        int rank = (int)(Math.random()*(max-min+1)+min);
        String randomGenRank = Integer.toString(rank);
        return randomGenRank;

    }

    void getSurpriseTitles() {
        String rank = randomRank();
        String fullString = surpriseByRankURL + rank;
        MyApp.executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    int value = 0;
                    URL urlObj = new URL(fullString);
                    HttpURLConnection urlConnection = (HttpURLConnection) urlObj.openConnection();
                    urlConnection.setRequestProperty("X-RapidAPI-Key", "c6d174dc42msh926955b2f690e00p1e37fdjsn6b6fedb2172d");
                    urlConnection.setRequestProperty("X-RapidAPI-Host", "anime-db.p.rapidapi.com");
                    InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                    StringBuffer buffer = new StringBuffer();
                    while ((value = inputStream.read()) !=-1){
                        inputValue = String.valueOf(buffer.append((char) value));
                    }

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.gettingJsonIsCompleted(inputValue);
                        }
                    });
                } catch (MalformedURLException e){
                    e.printStackTrace();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }


    public void getDetailsOfSelectedAnime (Anime selectedAnime){
            String fullString = animeURL3 + selectedAnime.get_id();
            System.out.println(fullString);
            MyApp.executorService.execute(new Runnable() {
                @Override
                public void run() {
                    try {
                        int value = 0;
                        URL urlObj = new URL(fullString);
                        HttpURLConnection urlConnection = (HttpURLConnection) urlObj.openConnection();
                        urlConnection.setRequestProperty("X-RapidAPI-Key", "c6d174dc42msh926955b2f690e00p1e37fdjsn6b6fedb2172d");
                        urlConnection.setRequestProperty("X-RapidAPI-Host", "anime-db.p.rapidapi.com");
                        InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                        StringBuffer buffer = new StringBuffer();
                        while ((value = inputStream.read()) !=-1){
                            inputValue = String.valueOf(buffer.append((char) value));
                        }

                        handler.post(new Runnable() {
                            @Override
                            public void run() {
                                listener.gettingJsonIsCompleted(inputValue);
                            }
                        });
                    } catch (MalformedURLException e){
                        e.printStackTrace();
                    } catch (IOException e){
                        e.printStackTrace();
                    }
                }
            });
        }

    void getHeaderAnimes(String genre) {
        String fullString = headerAnimeURL1 + genre + headerAnimeURL2;
        MyApp.executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    int value = 0;
                    URL urlObj = new URL(fullString);
                    HttpURLConnection urlConnection = (HttpURLConnection) urlObj.openConnection();
                    urlConnection.setRequestProperty("X-RapidAPI-Key", "c6d174dc42msh926955b2f690e00p1e37fdjsn6b6fedb2172d");
                    urlConnection.setRequestProperty("X-RapidAPI-Host", "anime-db.p.rapidapi.com");
                    InputStream inputStream = new BufferedInputStream(urlConnection.getInputStream());
                    StringBuffer buffer = new StringBuffer();
                    while ((value = inputStream.read()) !=-1){
                        inputValue = String.valueOf(buffer.append((char) value));
                    }

                    handler.post(new Runnable() {
                        @Override
                        public void run() {
                            listener.gettingJsonIsCompleted(inputValue);
                        }
                    });
                } catch (MalformedURLException e){
                    e.printStackTrace();
                } catch (IOException e){
                    e.printStackTrace();
                }
            }
        });
    }
}
