package com.example.mayada.recyclerviewexample.views;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.os.Environment;
import android.util.Log;
import android.widget.EditText;
import android.widget.ImageView;

import com.example.mayada.recyclerviewexample.R;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class InitialActivity extends Activity {

    @BindView(R.id.enterdName)
    EditText enterdName;
    @BindView(R.id.enteredStory)
    EditText enteredStory;
    @BindView(R.id.welcomeImg)
    ImageView welcomingImg;
    String retrievedStory = null;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_initial);
        ButterKnife.bind(this);

        Intent intent =getIntent();
        String imgpath = intent.getStringExtra("imggg");
        Log.i("ffffffff", "rrrrrrrrrrr    "+imgpath);

//        loadWelcomingPhoto();

        Bitmap myBitmap = BitmapFactory.decodeFile(imgpath);
        welcomingImg.setImageBitmap(myBitmap);

        //Retrieve from shared preference
        SharedPreferences sharedPreferences = getSharedPreferences("PersonName", 0);
        String savedName = sharedPreferences.getString("name", "Name Not Found");

        // Retrieve from Internal Storage
        try {
            FileInputStream fileInputStream = openFileInput("Saved Stories");
            byte[] byteArray = new byte[fileInputStream.available()];
            fileInputStream.read(byteArray);
            retrievedStory = new String(byteArray);
            fileInputStream.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (savedName != null && savedName != "Name Not Found" && retrievedStory != null ) {
            Intent intent3 = new Intent(InitialActivity.this, MainActivity.class);
            startActivity(intent3);
        }
    }

    @OnClick(R.id.showMoviesBtn)
     void showBtn(){
        if(enterdName != null && enteredStory != null){

            // Save to shared preference
            SharedPreferences data=getSharedPreferences("PersonName", 0);
            SharedPreferences.Editor editor=data.edit();
            editor.putString("name",enterdName.getText().toString());
            editor.commit();

            //Save to Internal Storage
            try{
            FileOutputStream fosmsg = openFileOutput("Saved Stories",Context.MODE_PRIVATE);
            fosmsg.write(enteredStory.getText().toString().trim().getBytes());
            fosmsg.close();
            } catch (FileNotFoundException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }

            Intent intent = new Intent(InitialActivity.this, MainActivity.class);
            startActivity(intent);
        }
    }
}
