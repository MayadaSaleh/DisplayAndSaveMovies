package com.example.mayada.recyclerviewexample.data.control;

import com.example.mayada.recyclerviewexample.pojos.OuterPojo;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Headers;

/**
 * Created by Mayada on 7/26/2018.
 */

public interface RetrofitService {


    @GET("movie?sort_by=popularity.desc&api_key=c2b9dbfa899bff8fe2668a514bca8ea6")
    @Headers({"Accept: application/json","Content-Type: application/json"})
    Call<OuterPojo> getPosters ();

    @GET("movie?sort_by=popularity.desc&api_key=c2b9dbfa899bff8fe2668a514bca8ea6")
    @Headers({"Accept: application/json","Content-Type: application/json"})
    Call<OuterPojo> getMovieName ();
}
