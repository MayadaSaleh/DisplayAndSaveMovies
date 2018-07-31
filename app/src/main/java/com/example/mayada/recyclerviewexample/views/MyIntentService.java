package com.example.mayada.recyclerviewexample.views;

import android.app.IntentService;
import android.content.Intent;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Environment;
import android.util.Log;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;


public class MyIntentService extends IntentService {

    ByteArrayOutputStream bytearrayoutputstream;
    FileOutputStream fileoutputstream;
    File file;

    public MyIntentService() {
        super("MyIntentService");
    }



    @Override
    protected void onHandleIntent(Intent intent) {
        if (intent != null) {


            Bitmap b = null;
            URL url;
            HttpURLConnection con;
            InputStream is;
            bytearrayoutputstream = new ByteArrayOutputStream();

            try {
                String res = intent.getStringExtra("imgURLL");
                url = new URL(res);
                con = (HttpURLConnection) url.openConnection();
                is = con.getInputStream();
                b = BitmapFactory.decodeStream(is);

                b.compress(Bitmap.CompressFormat.PNG, 60, bytearrayoutputstream);

                file = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOWNLOADS), "welcomePhoto.png");
                try {
                    //file.createNewFile();
                    fileoutputstream = new FileOutputStream(file);
                    fileoutputstream.write(bytearrayoutputstream.toByteArray());
                    fileoutputstream.close();

                    Intent intent2 = new Intent(this, MyReceiver.class);
                    intent2.setAction("com.example.SendBroadCast");
                    intent2.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
                    intent2.putExtra("ImgPath", file.getAbsolutePath());
                    sendBroadcast(intent2);
                } catch (Exception e) {
                    e.printStackTrace();
                }
                ///
            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        //  return b;
    }
}
