package com.tomaspev.parentalhelper

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer
import com.google.android.youtube.player.YouTubePlayerView

class DetalleRegistro : YouTubeBaseActivity() {

    val video_id = "uTns3HT9Luo"
    val youtube_api_key = "AIzaSyAe6EYTOQxMjflvEdNOCTHBlYVXvfOobAI"

    private lateinit var youtubePlayer: YouTubePlayerView
    private lateinit var btnPlay: Button

    lateinit var youtubePlayerInit: YouTubePlayer.OnInitializedListener

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detalle_registro)

        youtubePlayer = findViewById(R.id.ytplayer)
        btnPlay = findViewById(R.id.btn_play)

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

        btnPlay.setOnClickListener {
            youtubePlayer.initialize(youtube_api_key, youtubePlayerInit)
        }
    }
}