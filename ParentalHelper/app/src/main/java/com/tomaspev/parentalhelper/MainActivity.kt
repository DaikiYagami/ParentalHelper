package com.tomaspev.parentalhelper

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.google.firebase.auth.FirebaseAuth
import kotlinx.android.synthetic.main.activity_main.*

// Clase que permite la diferenciacion de los tipos de proveedor para iniciar sesión
enum class  ProviderType {
    BASIC,
    GOOGLE,
}

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val bundle = intent.extras                               // Variable que rescata los extras que trae el Intent
        val email = bundle?.getString("email")              // Variable que rescata el correo
        val provider = bundle?.getString("provider")        // Variable que rescata el provider


        setup(email ?: "", provider ?: "")         // Carga la funcion y sus datos provenientes de otros activity

        // VARIABLE QUE PERMITE MANTENER LA SESION INICIADA AL UTILIZAR PREFERENCIAS
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("provider", provider)
        prefs.apply()
    }

    // FUNCION QUE PERMITE CERRAR LA SESION EN FIREBASE Y VACIAR PREFS
    private fun setup(email: String, provider: String) {
        LogOutButton.setOnClickListener {

            val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
            prefs.clear()
            prefs.apply()

            FirebaseAuth.getInstance().signOut()
            val home = Intent(this, Login::class.java)
            startActivity(home)
        }
    }
}