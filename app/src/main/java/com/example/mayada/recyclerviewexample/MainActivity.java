package com.example.mayada.recyclerviewexample;

import android.app.Activity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {

    private ModelClass modelClass;
    String []moviesNames;
    String [] moviesPosters;

    RecyclerView  recyclerView;
    DataAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

          recyclerView  = (RecyclerView)findViewById(R.id.recyclerView);

        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
       // layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
//
//       modelClass  = new ModelClass();
//       modelClass.getPhotos();
//       modelClass.movieNames();

        adapter = new DataAdapter(this, moviesPosters, moviesNames);
        recyclerView.setAdapter(adapter);

        Call<OuterPojo> responseCall = APIRetrofitUtils.getService().getPosters();
        responseCall.enqueue(new Callback<OuterPojo>() {
            @Override
            public void onResponse(Call<OuterPojo> call, Response<OuterPojo> response) {
                OuterPojo responseReturn = response.body();

                moviesPosters = new String[responseReturn.getResults().length];
                moviesNames = new String[responseReturn.getResults().length];

                for (int i = 0; i < responseReturn.getResults().length; i++) {
                    moviesPosters[i] = responseReturn.getResults()[i].getPoster_path();
                    moviesNames[i] = responseReturn.getResults()[i].getTitle();
                }
                adapter = new DataAdapter(getApplicationContext(), moviesPosters, moviesNames);
                recyclerView.setAdapter(adapter);
            }
            @Override
            public void onFailure(Call<OuterPojo> call, Throwable t) {
                Log.i("failure", t.getMessage());
            }
        });
    }
}
