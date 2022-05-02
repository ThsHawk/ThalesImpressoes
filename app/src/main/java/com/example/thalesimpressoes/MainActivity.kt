package com.example.thalesimpressoes

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.ImageButton
import java.net.URL

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val sendButton = findViewById<Button>(R.id.send_button)
        val configBtn = findViewById<ImageButton>(R.id.config_button)
        val okButton = findViewById<Button>(R.id.okButton)

        sendButton.setOnClickListener{
            sendEntry()
        }//sendButton

        configBtn.setOnClickListener{
            val intent = Intent(applicationContext, ConfigContainer::class.java)
            startActivity(intent)
        }//configBtn

        okButton.setOnClickListener {
            okButton.visibility = View.GONE
        }//okButton

    }//onCreate

    private fun sendEntry(){
        val okButton = findViewById<Button>(R.id.okButton)
        val entry = findViewById<EditText>(R.id.data_entry)
        val value = entry.text.toString()
        if(value.isEmpty()) return

        val sharedPref = getSharedPreferences("appscriptID", Context.MODE_PRIVATE)
        val appscriptID = sharedPref.getString("ID", null)
        val url = URL("https://script.google.com/macros/s/${appscriptID}/exec?entry=${value}")

        Thread{
            val response = url.run {openConnection().run {inputStream.bufferedReader().readText()}}
            if(response == "Entry Written on column C"){
                runOnUiThread {
                    okButton.visibility = View.VISIBLE
                    entry.text = null
                }//runOnUiThread
            }//if
        }.start()

    }//sendEntry

}//MainActivity