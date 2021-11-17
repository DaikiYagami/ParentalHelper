package com.tomaspev.parentalhelper

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import com.google.android.youtube.player.internal.t
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.database.DatabaseReference
import com.google.firebase.database.FirebaseDatabase
import kotlinx.android.synthetic.main.activity_main.*

// Clase que permite la diferenciacion de los tipos de proveedor para iniciar sesión
enum class  ProviderType {
    BASIC,
    GOOGLE,
}

class MainActivity : YouTubeBaseActivity(), YouTubePlayer.OnInitializedListener {
    private lateinit var database: DatabaseReference

    override fun onInitializationSuccess(provider: YouTubePlayer.Provider?, player: YouTubePlayer?, wasRestored: Boolean) {
        Toast.makeText(this, "Youtube Api Initialization Success", Toast.LENGTH_SHORT).show()
        if (!wasRestored) {
            player?.cueVideo("BmTjPbdNsZs");
        }
    }
    override fun onInitializationFailure(p0: YouTubePlayer.Provider?, p1: YouTubeInitializationResult?) {
        Toast.makeText(this, "Youtube Api Initialization Failed", Toast.LENGTH_SHORT).show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        yt_pv.initialize("AIzaSyAe6EYTOQxMjflvEdNOCTHBlYVXvfOobAI", this)

        val bundle = intent.extras                               // Variable que rescata los extras que trae el Intent
        val email = bundle?.getString("email")              // Variable que rescata el correo
        val provider = bundle?.getString("provider")        // Variable que rescata el provider


        setup(email ?: "", provider ?: "")         // Carga la funcion y sus datos provenientes de otros activity

        // VARIABLE QUE PERMITE MANTENER LA SESION INICIADA AL UTILIZAR PREFERENCIAS
        val prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
        prefs.putString("email", email)
        prefs.putString("provider", provider)
        prefs.apply()

        // Seba ==============================
        val user = FirebaseAuth.getInstance().currentUser
        var uid = ""
        user?.let {
            uid = user.uid
        }
        readData(uid)
    }

    private fun readData(uid: String) {
        database = FirebaseDatabase.getInstance().getReference("Usuario")
        database.child(uid).get().addOnSuccessListener {
            if (it.exists()) {
                val correo = it.child("email").value
                val contra = it.child("password").value
                tv_uid.text = contra.toString()
            }
            else {
                Toast.makeText(this, "User doesn't exists", Toast.LENGTH_SHORT).show()
            }
        }
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