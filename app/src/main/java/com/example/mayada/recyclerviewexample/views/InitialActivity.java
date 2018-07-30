package com.example.mayada.recyclerviewexample.views;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.EditText;

import com.example.mayada.recyclerviewexample.R;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InitialActivity extends Activity {

    @BindView(R.id.enterdName)
    EditText enterdName;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
        ButterKnife.bind(this);
        SharedPreferences sharedPreferences = getSharedPreferences("PersonName",0);
        String savedName = sharedPreferences.getString("name","Name Not Found");

        if(savedName != null && savedName != "Name Not Found") {
            Intent intent = new Intent(InitialActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }


    @OnClick(R.id.showMoviesBtn)
     void showBtn(){
        if(enterdName != null){
            SharedPreferences data=getSharedPreferences("PersonName", 0);
            SharedPreferences.Editor editor=data.edit();
            editor.putString("name",enterdName.getText().toString().trim());
            editor.commit();
            Intent intent = new Intent(InitialActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
