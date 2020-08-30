package com.example.myapplication

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.R.*
import okhttp3.*
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)
        findViewById<EditText>(id.editTextTextPersonName).setOnClickListener { view ->
            view.setBackgroundColor(Color.RED)
        };

        findViewById<Button>(id.editPersonButton).setOnClickListener { view ->
            findViewById<EditText>(id.editTextTextPersonName).setBackgroundColor(Color.GREEN)
        }

        findViewById<Button>(id.editPersonButton2).setOnClickListener { view ->
            val infoActivity = Intent(this, InfoActivity::class.java)
            infoActivity.putExtra("username", "Rsoda")
            infoActivity.putExtra("Password", "password")
            startActivity(infoActivity)
        }

//        setSupportActionBar(findViewById(R.id.toolbar))
//
//        findViewById<FloatingActionButton>(R.id.fab).setOnClickListener { view ->
//            Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
//                .setAction("Action", null).show()
//        }
    }

    override fun onStart() {
        super.onStart()
        Log.i("INSIDE START METHOD", "on start");
    }

    override fun onResume() {
        super.onResume()
        Log.i("INSIDE RESUME METHOD", "on resume");
    }

    override fun onPause() {
        super.onPause()
        Log.i("INSIDE onPause METHOD", "on onPause");
    }

    override fun onStop() {
        super.onStop()
        Log.i("INSIDE onStop METHOD", "on onStop")
    }

    override fun onDestroy() {
        super.onDestroy()
        Log.i("INSIDE onDestroy METHOD", "on onDestroy")
    }

    fun startService(view: View) {

        var httpClient = OkHttpClient();
        val request = Request.Builder()
            .url("http://api.letsbuildthatapp.com/youtube/course_detail?id=1")
            .build()

        Log.i("My", "calling http://api.letsbuildthatapp.com/youtube/course_detail?id=1")

        httpClient.newCall(request).enqueue(object : Callback {
            override fun onFailure(call: Call, e: IOException) {
                Log.i("My", "error")
                e.printStackTrace()
            }

            override fun onResponse(call: Call, response: Response) {
                response.use {
                    Log.i("My", "response $response")
                    if (!response.isSuccessful) throw IOException("Unexpected code $response")

                    var x = response.headers();
                    Log.i("My", "$x")
                    var respBody = response.body();



                    Log.i("Tab", "$respBody")
                }
            }
        })

//        startService(Intent(baseContext, HelloService::class.java));
    }

    fun stopService(view: View) {
        stopService(Intent(baseContext, HelloService::class.java));
    }

    fun broadcaseAction(view: View) {
        var intent: Intent = Intent(baseContext, MainActivity::class.java);
        Log.i("main activity", "firing action")
        intent.setAction("com.myapp.CUSTOM_ACTION")
        sendBroadcast(intent);
    }


}