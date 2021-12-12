package com.tomaspev.parentalhelper

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detalle_contenido.*
import android.widget.TextView
import android.widget.Toast
import android.widget.VideoView
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import kotlinx.android.synthetic.main.activity_detalle_contenido.view.*
import kotlinx.android.synthetic.main.activity_detalle_registro.*
import kotlinx.android.synthetic.main.activity_main.*


class DetalleContenido : YouTubeBaseActivity() {

    val youtube_api_key = "AIzaSyAe6EYTOQxMjflvEdNOCTHBlYVXvfOobAI"

    private lateinit var youTubePlayer: YouTubePlayerView
    private lateinit var youtubePlayerInit: YouTubePlayer.OnInitializedListener

    private var activity: String? = null

    private lateinit var prefs: SharedPreferences.Editor
    private var bundle: Bundle? = null
    private var email: String? = null
    private var provider: String? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_contenido)
        // Código para mostrar los datos del contenido seleccionado ==============================================================>>>

        // Se recibe el objeto contenido pasado por el intent
        val contenido = intent.getSerializableExtra("contenido") as Contenido
        activity = intent.getStringExtra("activity")

        // Se identifican los campos mostrados en el layout
        val titulo: TextView = findViewById(R.id.tv_detalle_contenido_titulo)
        val ambito: TextView = findViewById(R.id.tv_detalle_contenido_ambito)
        val tramo: TextView = findViewById(R.id.tv_detalle_contenido_tramo)
        val objetivo: TextView = findViewById(R.id.tv_detalle_contenido_objetivo)

        val descripcion: TextView = findViewById(R.id.tv_detalle_contenido_descripcionShort)
        /*val descripcionShort: TextView = findViewById(R.id.tv_detalle_contenido_descripcionShort)*/
        val descripcionLong: TextView = findViewById(R.id.tv_detalle_contenido_descripcionLong)


        val video_id = contenido.enlaceWeb

        // Se le asignan los valores guardados en el objeto registro a los campos del layout
        titulo.text = contenido.titulo
        ambito.text = contenido.ambito
        tramo.text = contenido.tramo
        objetivo.text = contenido.objetivo

        descripcion.text = contenido.descripcionShort
        descripcionLong.text = contenido.descripcionLong


        // yutu
        youTubePlayer = findViewById(R.id.videoView)

        youtubePlayerInit = object : YouTubePlayer.OnInitializedListener{
            override fun onInitializationSuccess(
                p0: YouTubePlayer.Provider?,
                p1: YouTubePlayer?,
                p2: Boolean
            ) {
                p1?.loadVideo(video_id)
            }

            override fun onInitializationFailure(
                p0: YouTubePlayer.Provider?,
                p1: YouTubeInitializationResult?
            ) {
                Toast.makeText(applicationContext, "Error", Toast.LENGTH_SHORT).show()
            }

        }
        youTubePlayer.initialize(youtube_api_key, youtubePlayerInit)

        bundle = intent.extras                               // Variable que rescata los extras que trae el Intent
        email = bundle?.getString("email")              // Variable que rescata el correo
        provider = bundle?.getString("provider")        // Variable que rescata el provider

        prefs = getSharedPreferences(getString(R.string.prefs_file), Context.MODE_PRIVATE).edit()
    }

    override fun onBackPressed() {
        if (activity == "NewContent") {
            intent = Intent(this, NewContent::class.java)
            intent.putExtras(bundle!!)
        }
        else if (activity == "Destacados") {
            intent = Intent(this, Destacados::class.java)
            intent.putExtras(bundle!!)
        }
        else {
            intent = Intent(this, MainActivity::class.java)
            intent.putExtras(bundle!!)
        }
        if (intent != null) {
            startActivity(intent)
            finish()
        }
    }
}


