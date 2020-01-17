package com.example.httprequests

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.StrictMode
import android.util.Log
import android.widget.Button
import android.widget.Toast
import com.android.volley.Request
import com.android.volley.Response
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL

class MainActivity : AppCompatActivity(), CompletedListener {
    override fun downloadCompleted(result: String) {
        Log.d("download", result)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnValidate = findViewById<Button>(R.id.btnValidate)
        val btnHttpRequest = findViewById<Button>(R.id.btnHttpRequest)
        val btnVolley = findViewById<Button>(R.id.btnVolley)

        btnValidate.setOnClickListener{
            if (Network.ThereIsNetwork(this)){
                Toast.makeText(this, "You have internet", Toast.LENGTH_SHORT).show()
            }else{
                Toast.makeText(this, "Make sure there is an internet connection!", Toast.LENGTH_SHORT).show()
            }
        }

        btnHttpRequest.setOnClickListener{
            if (Network.ThereIsNetwork(this)){
              DownloadURL(this).execute("http://google.com")
            }else{
                Toast.makeText(this, "Make sure there is an internet connection!", Toast.LENGTH_SHORT).show()
            }
        }

        btnVolley.setOnClickListener{
            if (Network.ThereIsNetwork(this)){
                requestVolley("http://www.google.com")
            }else{
                Toast.makeText(this, "Make sure there is an internet connection!", Toast.LENGTH_SHORT).show()
            }
        }
    }

    //volley method
    private fun requestVolley(url:String){
        val queue = Volley.newRequestQueue(this)

        val request = StringRequest(Request.Method.GET, url, Response.Listener<String>{
            response ->
            try {
                Log.d("HttpVolley", response)
            }catch (e:IOException){

            }
        }, Response.ErrorListener {  })

        queue.add(request)

    }




}
