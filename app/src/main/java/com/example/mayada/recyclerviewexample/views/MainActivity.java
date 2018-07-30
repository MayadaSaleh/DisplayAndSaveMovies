package com.example.mayada.recyclerviewexample.views;

import android.app.Activity;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;

import com.example.mayada.recyclerviewexample.ModelClass;
import com.example.mayada.recyclerviewexample.R;
import com.example.mayada.recyclerviewexample.data.control.APIRetrofitUtils;
import com.example.mayada.recyclerviewexample.database.sqlite.DatabaseAdapter;
import com.example.mayada.recyclerviewexample.pojos.OuterPojo;

import java.sql.SQLException;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends Activity {

    private ModelClass modelClass;
    String []moviesNames;
    String [] moviesPosters;

    RecyclerView  recyclerView;
    private DataAdapter adapter;
    private DatabaseAdapter mySQLiteAdapter;
    private Cursor c;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //SQLITE Database
        mySQLiteAdapter = new DatabaseAdapter(this);
        try {
            mySQLiteAdapter.open();
        } catch (SQLException e) {
            e.printStackTrace();
        }

        recyclerView = (RecyclerView) findViewById(R.id.recyclerView);


        final LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerView.setLayoutManager(layoutManager);
//
//       modelClass  = new ModelClass();
//       modelClass.getPhotos();
//       modelClass.movieNames();


        c = mySQLiteAdapter.fetch();
        c.moveToFirst();
        if (c != null && c.moveToFirst() == true) {
            fetchDataFromSQLite();
        } else {
            callingAPI();
        }
    }

    private void callingAPI(){
            Log.i("testMemo","From Web API");

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
                            mySQLiteAdapter.insert(responseReturn.getResults()[i].getTitle(),responseReturn.getResults()[i].getPoster_path());
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

    private void fetchDataFromSQLite(){
        Log.i("testMemo","From SQLite "+c.getCount());
        moviesPosters = new String[c.getCount()];
        moviesNames = new String[c.getCount()];
        int counter=0;
        do {
            moviesNames[counter]=(c.getString(0));
            moviesPosters[counter]=(c.getString(1));
            counter++;
        } while (c.moveToNext());
        adapter = new DataAdapter(this, moviesPosters, moviesNames);
        recyclerView.setAdapter(adapter);
    }
}
