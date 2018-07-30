package com.example.mayada.recyclerviewexample;

/**
 * Created by Mayada on 7/26/2018.
 */

public class APIRetrofitUtils {

    public static final String BASE_URL = "http://api.themoviedb.org/3/discover/";

    public static RetrofitService getService() {

        return RetrofitCreation.getClient(BASE_URL).create(RetrofitService.class);
    }
}
