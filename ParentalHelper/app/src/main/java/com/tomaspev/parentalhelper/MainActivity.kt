package com.tomaspev.parentalhelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun startNewContent(view: View){
        Toast.makeText(this,"Ingresando a Nuevo Contenido", Toast.LENGTH_SHORT).show()
        val intent = Intent (this,NewContent::class.java)
        startActivity(intent)
        finish()
    }
}