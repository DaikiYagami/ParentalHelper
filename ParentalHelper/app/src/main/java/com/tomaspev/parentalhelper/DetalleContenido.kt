package com.tomaspev.parentalhelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_detalle_contenido.*
import android.widget.TextView
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView
import kotlinx.android.synthetic.main.activity_main.*


class DetalleContenido : YouTubeBaseActivity() {
    val video_id = "B9YMaeehOmk"
    val youtube_api_key = "AIzaSyAe6EYTOQxMjflvEdNOCTHBlYVXvfOobAI"

    private lateinit var youTubePlayer: YouTubePlayerView
    private lateinit var youtubePlayerInit: YouTubePlayer.OnInitializedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_contenido)
        // Código para mostrar los datos del contenido seleccionado ==============================================================>>>

        // Se recibe el objeto contenido pasado por el intent
        val contenido = intent.getSerializableExtra("contenido") as Contenido

        // Se identifican los campos mostrados en el layout
        val titulo: TextView = findViewById(R.id.tv_detalle_contenido_titulo)
        val ambito: TextView = findViewById(R.id.tv_detalle_contenido_ambito)
        val tramo: TextView = findViewById(R.id.tv_detalle_contenido_tramo)
        val objetivo: TextView = findViewById(R.id.tv_detalle_contenido_objetivo)

        /*val descripcionShort: TextView = findViewById(R.id.tv_detalle_contenido_descripcionShort)*/
        val descripcionLong: TextView = findViewById(R.id.tv_detalle_contenido_descripcionLong)

        // Se le asignan los valores guardados en el objeto registro a los campos del layout
        titulo.text = contenido.titulo
        ambito.text = contenido.ambito
        tramo.text = contenido.tramo
        objetivo.text = contenido.objetivo

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
    }

}


