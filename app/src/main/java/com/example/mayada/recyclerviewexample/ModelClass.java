package com.example.mayada.recyclerviewexample;

import android.util.Log;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * Created by Mayada on 7/26/2018.
 */

public class ModelClass {
//
//private String[] posterPaths ;
//private String[] movieeNames ;
//
//private DataAdapter adapter;
//
//
//
//    public void getPhotos() {
//        if (posterPaths == null) {
//            Call<OuterPojo> responseCall = APIRetrofitUtils.getService().getPosters();
//            responseCall.enqueue(new Callback<OuterPojo>() {
//                @Override
//                public void onResponse(Call<OuterPojo> call, Response<OuterPojo> response) {
//                    OuterPojo responseReturn = response.body();
//
//                    posterPaths = new String[responseReturn.getResults().length];
//                    for (int i = 0; i < responseReturn.getResults().length; i++) {
//                        posterPaths[i] = responseReturn.getResults()[i].getPoster_path();
//                    }
//              //      adapter.setMovieListPosters(posterPaths);
//                }
//
//
//                @Override
//                public void onFailure(Call<OuterPojo> call, Throwable t) {
//                    Log.i("failure", t.getMessage());
//                }
//            });
//        }
//    }
//
//    public void movieNames() {
//        if (movieeNames == null) {
//
//            Call<OuterPojo> responseCall = APIRetrofitUtils.getService().getMovieName();
//            responseCall.enqueue(new Callback<OuterPojo>() {
//                @Override
//                public void onResponse(Call<OuterPojo> call, Response<OuterPojo> response) {
//                    OuterPojo responseReturn = response.body();
//                    movieeNames = new String[responseReturn.getResults().length];
//                    for (int i = 0; i < responseReturn.getResults().length; i++) {
//                        Log.i("memooooo", "kkkkkkkkkk " + responseReturn.getResults()[i].getTitle());
//                        movieeNames[i] = responseReturn.getResults()[i].getTitle();
//                    }
//
//               //     adapter.setMovieListNames(movieeNames);
//                }
//
//                @Override
//                public void onFailure(Call<OuterPojo> call, Throwable t) {
//                    Log.i("failure", t.getMessage());
//                    Log.i("memooooo", "kkkkkkkkkk");
//                }
//            });
//
//        }
//    }
//

}
