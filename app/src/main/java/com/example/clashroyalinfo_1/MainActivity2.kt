package com.example.clashroyalinfo_1

import android.graphics.Color
import android.os.Bundle
import android.widget.ImageView
import android.widget.TextView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import android.content.SharedPreferences
import android.graphics.drawable.Drawable
import androidx.core.graphics.drawable.toDrawable

val Fav_imageList :ArrayList<Int> = ArrayList()
val Fav_imageList2 : ArrayList<Pair<Int, String>> = ArrayList()

class MainActivity2 : AppCompatActivity() {
    private lateinit var sharedPreferences: SharedPreferences
    private var  cards = mutableMapOf<String, Boolean>()
 // Public ArrayList for holding images


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main2)

        sharedPreferences = getSharedPreferences("MyPrefs", MODE_PRIVATE)

        val imagviewbackground: ImageView = findViewById(R.id.imageView)
        val textname_viewbackground: TextView = findViewById(R.id.textname1)
        val textrarity_viewbackground: TextView = findViewById(R.id.textrarity1)
        val texttype_viewbackground: TextView = findViewById(R.id.texttype1)
        val ExplainView: TextView = findViewById(R.id.Explain)
        val heart: ImageView = findViewById(R.id.heartimage)

        val bundle: Bundle? = intent.extras
        if (bundle != null) {
            val img = bundle.getInt("Imagebachground")
            val name = bundle.getString("name")
            val type = bundle.getString("type")
            val rarity = bundle.getString("Rarity")
            val explain = bundle.getString("Explain")

            imagviewbackground.setImageResource(img)
            textname_viewbackground.text = name
            textrarity_viewbackground.text = rarity
            texttype_viewbackground.text = type
            ExplainView.text = explain

            // Load the heart state from SharedPreferences
            val initialHeart = sharedPreferences.getBoolean(name ?: "", true)
            cards[name ?: ""] = initialHeart // Store in the map

            // Update imageList based on the initial state
            if (!cards[name ?: ""]!!) {
                // If the state is false, add the corresponding image to Fav_imageList
                for ((image, cardName) in Fav_imageList2) {
                    if (cardName == name) {
                        if (!Fav_imageList.contains(image)) {
                            Fav_imageList.add(image)
                        }
                    }
                }
            }

            // Set the heart image based on the state
            heart.setImageResource(if (initialHeart) R.drawable.hearticon else R.drawable.hearticoon)

            val rarityColor = when (rarity?.toLowerCase()) {
                "common" -> "#1a8cff"
                "rare" -> "#ff9900"
                "epic" -> "#e600e6"
                "legendary" -> "#ff4d4d"
                else -> "#b8142d"
            }
            textrarity_viewbackground.setTextColor(Color.parseColor(rarityColor))
            textname_viewbackground.setTextColor(Color.parseColor(rarityColor))
            texttype_viewbackground.setTextColor(Color.parseColor(rarityColor))
        }

        heart.setOnClickListener {
            val cardName = textname_viewbackground.text.toString()
            val currentState = cards[cardName] ?: true
            cards[cardName] = !currentState // Toggle the state

            // Check if current state is false and adds corresponding image to Fav_imageList
            if (!cards[cardName]!!) {
                // If the state is false, check Fav_imageList2 for matching key
                for ((image, name) in Fav_imageList2) {
                    if (name == cardName) {
                        if (!Fav_imageList.contains(image)) {
                            Fav_imageList.add(image)
                        }
                    }
                }
            } else {
                // If the state is true, remove it from Fav_imageList if it exists
                for ((image, name) in Fav_imageList2) {
                    if (name == cardName) {
                        Fav_imageList.remove(image)
                    }
                }
            }
            heart.setImageResource(if (cards[cardName] == true) R.drawable.hearticon else R.drawable.hearticoon)
        }
    }



    override fun onPause() {
        super.onPause()
        // Save the current heart states
        val editor = sharedPreferences.edit()
        for ((name, state) in cards) {
            editor.putBoolean(name, state)
        }
        editor.apply()
    }

    override fun onResume() {
        super.onResume()
        // Load the states from SharedPreferences
        for (name in cards.keys) {
            val savedState = sharedPreferences.getBoolean(name, true)
            cards[name] = savedState
        }
    }

}