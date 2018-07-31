package com.example.mayada.recyclerviewexample.views;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;

public class MyReceiver extends BroadcastReceiver {

    @Override
    public void onReceive(Context context, Intent intent) {

        String senttt= intent.getStringExtra("ImgPath");
        Log.i("fffff","reciever  "+senttt);
        Intent i = new Intent(context, InitialActivity.class);
        i.putExtra("imggg", senttt);
        context.startActivity(i);
    }
}
