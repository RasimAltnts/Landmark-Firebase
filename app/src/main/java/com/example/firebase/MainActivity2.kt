package com.example.firebase

import android.content.Intent
import android.graphics.Color
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import android.view.animation.Animation
import android.widget.Button
import android.widget.ImageView
import com.google.firebase.auth.FirebaseAuth

class MainActivity2 : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        auth = FirebaseAuth.getInstance()


    }

    override fun onResume() {
        val plus:ImageView = findViewById(R.id.plus)
        plus.setOnClickListener {
                 Animation(plus)
        }


        val settings:ImageView = findViewById(R.id.settings)
        settings.setOnClickListener {
            Animation(settings)
        }

        super.onResume()

    }

    fun logOut(){
        auth.signOut()
        val intent = Intent(applicationContext,MainActivity::class.java)
        startActivity(intent)
    }

    fun Animation(imageView: ImageView){
        imageView.animate().apply {
            duration = 1000
            rotation(45f)
        }

    }
}