package com.example.myapplication;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.widget.Toast;

public class HelloBroadcastReceiver extends BroadcastReceiver {
    @Override
    public void onReceive(Context context, Intent intent) {
        Log.i("", "Inside onReceive handler : Intent"+intent);
        Toast.makeText(context, "broadcast event fired", Toast.LENGTH_LONG).show();
    }
}
