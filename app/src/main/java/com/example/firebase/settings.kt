package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.ImageView

class settings : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_settings)
        val settinsButton:ImageView = findViewById(R.id.settingsButton)
        transmission(settinsButton)

    }

    override fun onResume() {
        super.onResume()
    }

    fun goHome(view:View){
        val intent = Intent(this,homepage::class.java)
        startActivity(intent)
    }

    fun goAdd(view:View){
        val intent = Intent(this,addPage::class.java)
        startActivity(intent)
    }
    fun transmission(imageView: ImageView){
        imageView.animate().apply {
            duration=500
            rotation(45f)
        }

    }
}