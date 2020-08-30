package com.example.myapplication;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.util.Log;
import android.view.textclassifier.TextLinks;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import com.squareup.okhttp.OkHttpClient;
import com.squareup.okhttp.Request;
import com.squareup.okhttp.Response;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import static android.util.Log.println;

public class HelloService extends Service {
    @Nullable
    @Override
    public IBinder onBind(Intent intent) {
        return null;
    }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {

        String url = "http://api.letsbuildthatapp.com/youtube/course_detail?id=1";
        Response response ;
        try {
            OkHttpClient client = new OkHttpClient();
            Request request = new Request.Builder().url(url).build();
            response = client.newCall(request).execute();
            JSONObject jsonResp = new JSONObject(response.body().string());

            Log.i("My", "JsonRepon:"+jsonResp);
        } catch (@NonNull IOException | JSONException e) {
            Log.e("My", "" + e.getLocalizedMessage());
        }
        Toast.makeText(this,"Service started",Toast.LENGTH_LONG).show();
        return START_STICKY;
        // return super.onStartCommand(intent, flags, startId);
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        Toast.makeText(this, "Service Destroyed", Toast.LENGTH_LONG).show();
    }
}
