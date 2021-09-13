package com.example.firebase

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.graphics.ImageDecoder
import android.net.Uri
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.view.View
import android.widget.ImageView
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import kotlinx.android.synthetic.main.activity_add_page.*
import java.lang.Exception

class addPage : AppCompatActivity() {

    var selectedImage: Uri? =  null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_add_page)
        val addButton:ImageView = findViewById(R.id.addButton)
        transmission(addButton)
        permissionRequest()
    }

    fun goSettings(view: View){
        val intent = Intent(this,settings::class.java)
        startActivity(intent)
    }

    fun goHome(view: View){
        val intent = Intent(this,homepage::class.java)
        startActivity(intent)
    }

    fun transmission(imageView: ImageView){
        imageView.animate().apply {
            duration=500
            rotation(45f)
        }

    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        if(requestCode == 1){
            if(grantResults.size > 0 && grantResults[0] == PackageManager.PERMISSION_GRANTED){
                val intent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
                startActivityForResult(intent,2)
            }
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if(requestCode==2 && resultCode == Activity.RESULT_OK && data != null){
            selectedImage = data.data
            try {
                if(selectedImage != null){

                    if(Build.VERSION.SDK_INT >= 28){
                        val source = ImageDecoder.createSource(this.contentResolver,selectedImage!!)
                        val bitmap = ImageDecoder.decodeBitmap(source)
                        uploadimageView.setImageBitmap(bitmap)

                    }
                    else{
                        val bitmap = MediaStore.Images.Media.getBitmap(this.contentResolver,selectedImage)
                        uploadimageView.setImageBitmap(bitmap)
                    }

                }
                
            }
            catch (e:Exception){
                e.printStackTrace()
            }
            
        }
        super.onActivityResult(requestCode, resultCode, data)
    }

    fun permissionRequest(){
        if(ContextCompat.checkSelfPermission(this,Manifest.permission.READ_EXTERNAL_STORAGE)!= PackageManager.PERMISSION_GRANTED){
            ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.READ_EXTERNAL_STORAGE),1)
        }
        else{
            val intent = Intent(Intent.ACTION_PICK,MediaStore.Images.Media.EXTERNAL_CONTENT_URI)
            startActivityForResult(intent,2)
        }
    }
}