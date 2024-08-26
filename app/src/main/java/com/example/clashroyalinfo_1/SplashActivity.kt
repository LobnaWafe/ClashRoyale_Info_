package com.example.clashroyalinfo_1

import android.content.Intent
import android.media.MediaPlayer
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity

class SplashActivity : AppCompatActivity() {
    private val SPLASH_SCREEN_TIMEOUT: Long = 5000
    private lateinit var mediaPlayer: MediaPlayer
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash) // Create this layout

        mediaPlayer = MediaPlayer.create(this, R.raw.clashstartsplash)

        if (mediaPlayer != null) {
            mediaPlayer.isLooping = true // Set looping
            mediaPlayer.start() // Start audio immediately
        }

        // Delay for a few seconds
        Handler(Looper.getMainLooper()).postDelayed({
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
            finish() // Call finish to remove splash activity from back stack
            // Stop or release MediaPlayer if you no longer need it
           stopSound()
        }, 2000) // 2000 milliseconds delay
    }

    private fun stopSound() {
        if (::mediaPlayer.isInitialized) {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Release MediaPlayer resources when done
        if (::mediaPlayer.isInitialized) {
            //mediaPlayer.stop()
            mediaPlayer.release()
        }
    }
}