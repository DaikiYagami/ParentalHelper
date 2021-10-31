package com.tomaspev.parentalhelper

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View

class Login : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_login)
        supportActionBar?.hide()
    }
    fun registrarse(view: View){
        val intent = Intent(this, RegistroUsuario::class.java)
        startActivity(intent)
    }
    fun david(view: View){
        val intent = Intent(this, NewContent::class.java)
        startActivity(intent)
    }
}