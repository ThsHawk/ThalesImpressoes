package com.example.thalesimpressoes

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText

class ConfigContainer : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_config_container)

        val sharedPref = getSharedPreferences("appscriptID", Context.MODE_PRIVATE)
        val appscriptID = sharedPref.getString("ID", null)
        val id = appscriptID.toString()
        val editor = sharedPref.edit()

        val appscriptIDfield = findViewById<EditText>(R.id.appscripts_ID)
        if(appscriptID != null) appscriptIDfield.hint = id

        val confirmBtn = findViewById<Button>(R.id.appscripts_confirmButton)


        confirmBtn.setOnClickListener{

            val appscriptID = appscriptIDfield.text.toString()
            editor.apply{
                putString("ID", appscriptID)
                apply()
            }//editor

            val intent = Intent(applicationContext, MainActivity::class.java)
            startActivity(intent)

        }//confirmBtn

    }//onCreate
}//ConfigContainer