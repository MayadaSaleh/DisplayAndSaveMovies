package com.example.mayada.recyclerviewexample.views;

import android.Manifest;
import android.app.Activity;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.os.Bundle;
import android.support.v4.app.ActivityCompat;
import android.support.v4.content.ContextCompat;

import com.example.mayada.recyclerviewexample.R;

public class LoadWelcomingImgActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_load_welcoming_img);

        if (ContextCompat.checkSelfPermission(LoadWelcomingImgActivity.this, Manifest.permission.WRITE_EXTERNAL_STORAGE) != PackageManager.PERMISSION_GRANTED) {
            ActivityCompat.requestPermissions(LoadWelcomingImgActivity.this,
                    new String[]{Manifest.permission.WRITE_EXTERNAL_STORAGE},0);
        }

        String url = "http://www.pngmart.com/files/3/Welcome-PNG-Transparent.png";
        Intent intent = new Intent(LoadWelcomingImgActivity.this,MyIntentService.class);
        intent.putExtra("imgURLL",url);
        startService(intent);
    }
}
