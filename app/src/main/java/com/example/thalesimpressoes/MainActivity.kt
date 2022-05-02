package com.example.thalesimpressoes

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import android.widget.TextView
import java.net.URL
import javax.net.ssl.HttpsURLConnection

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sendButton = findViewById<Button>(R.id.send_button)
        val configBtn = findViewById<ImageButton>(R.id.config_button)
        val okButton = findViewById<TextView>(R.id.okButton)

        sendButton.setOnClickListener{
            sendEntry()
        }//sendButton
        configBtn.setOnClickListener{
            val intent = Intent(applicationContext, ConfigContainer::class.java)
            startActivity(intent)
        }//configBtn
        okButton.setOnClickListener {
            val entry = findViewById<EditText>(R.id.data_entry)
            entry.text = null
            okButton.visibility = View.GONE
        }//okButton
    }//onCreate

    private fun sendEntry(){
        val entry = findViewById<EditText>(R.id.data_entry)
        val value = entry.text.toString()
        if(value.isEmpty()) return

        val sharedPref = getSharedPreferences("appscriptID", Context.MODE_PRIVATE)
        val appscriptID = sharedPref.getString("ID", null)

        Thread{
            val url = URL("https://script.google.com/macros/s/${appscriptID}/exec?entry=${"%.2".format(value.toDouble())}")
            val conn = url.openConnection() as HttpsURLConnection

            try {
                /*val data = conn.inputStream.bufferedReader().readText()
                if(data == "Entry Written on column C"){
                    val okButton = findViewById<TextView>(R.id.okButton)
                    okButton.visibility = View.VISIBLE
                }//if*/

            }finally {
                conn.disconnect()
            }//try

        }.start()//Thread
    }//sendEntry


}//MainActivity