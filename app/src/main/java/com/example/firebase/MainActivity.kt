package com.example.firebase

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.FirebaseAuth


class MainActivity : AppCompatActivity() {
    private lateinit var auth:FirebaseAuth
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        actionBar?.hide()
        auth = FirebaseAuth.getInstance()

        var currentUser = auth.currentUser
        if(currentUser!=null){
            val intent=Intent(this,MainActivity2::class.java)
            startActivity(intent)
        }

    }

    override fun onResume() {
        var createAccountTextView:TextView = findViewById(R.id.createUserTextView)
        var twitterButton:Button = findViewById(R.id.twitter)
        var instagramButton:Button = findViewById(R.id.instagram)
        var googleButton:Button = findViewById(R.id.github)
        var loginButton:Button = findViewById(R.id.loginButton)
        var email:TextView = findViewById(R.id.emailText)
        var password:TextView = findViewById(R.id.passwordText)

        createAccountTextView.setOnClickListener {
            createUserPage()
        }

        twitterButton.setOnClickListener {
            val twitter = "https://twitter.com/Rsm_Altnts"
            goURL(twitter)
        }

        instagramButton.setOnClickListener {
            val instagram = "https://www.instagram.com/rasimaltnts/"
            goURL(instagram)
        }
        googleButton.setOnClickListener {
            val github = "https://github.com/RasimAltnts"
            goURL(github)
        }
        loginButton.setOnClickListener {
            login(email.text.toString(),password.text.toString())
        }



        super.onResume()
    }



    private fun createUserPage(){
        val intent = Intent(this,createuse::class.java)
        startActivity(intent)

    }

    fun goURL(url:String) {
        try {

            val openURL = Intent(android.content.Intent.ACTION_VIEW)
            openURL.data = Uri.parse(url)
            startActivity(openURL)
        }
        catch (e:Exception){
            Log.d("app","${e}")
        }
    }

    private fun login(email:String,password:String){
        auth.signInWithEmailAndPassword(email,password).addOnCompleteListener { task ->
            if(task.isSuccessful){
                val intent = Intent(this,MainActivity2::class.java)
                startActivity(intent)

            }
        }.addOnFailureListener { Exception->
            Toast.makeText(applicationContext,Exception.localizedMessage.toString(),Toast.LENGTH_LONG).show()
        }
    }


}