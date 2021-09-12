package com.example.firebase

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import com.google.firebase.auth.ktx.auth
import com.google.firebase.auth.FirebaseAuth

class createuse : AppCompatActivity() {
    private lateinit var auth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_createuse)
        actionBar?.hide()
        auth = FirebaseAuth.getInstance()//Firebase is initilazed..

    }

    override fun onResume() {
        super.onResume()

        val createButton:Button = findViewById(R.id.createAccountButton)

        createButton.setOnClickListener {

            //Take user Password and email.

             val email:TextView = findViewById(R.id.userIDPlainText)
             val password:TextView = findViewById(R.id.editTextPassword)
             val passwordAgain:TextView = findViewById(R.id.editTextPassword2)

            //Send password and email to userOnCreate function.
           userOnCreate(email.text.toString(),password.text.toString(),passwordAgain.text.toString())

        }





    }

    fun backToMainActivity(){
        val intent = Intent(this,MainActivity::class.java)
        startActivity(intent)
    }

    fun userOnCreate(email:String,password:String,passwordAgain:String){
        Log.d("app","userOnCreate")
        if(password == passwordAgain/*Check in password*/){
            auth.createUserWithEmailAndPassword(email,password).addOnCompleteListener(this){/*User add to Firebase*/
                task ->
                if(task.isSuccessful){
                    backToMainActivity()
                }
            }.addOnFailureListener{Exception->
                Toast.makeText(applicationContext,Exception.localizedMessage.toString(),Toast.LENGTH_LONG)
            }


        }
        else{
            Toast.makeText(this,"Password dont match",Toast.LENGTH_LONG)
            Log.d("app","password dont match")
        }

    }

}