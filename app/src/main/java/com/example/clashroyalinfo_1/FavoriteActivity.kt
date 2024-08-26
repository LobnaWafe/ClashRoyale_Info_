package com.example.clashroyalinfo_1

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.MenuItem
import android.widget.ImageView
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView

class FavoriteActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList2: ArrayList<Cards>
    private lateinit var drawerlayout: DrawerLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_favorite)

        // Initialize RecyclerView
        newRecyclerView = findViewById(R.id.itemmss)
        newRecyclerView.layoutManager = GridLayoutManager(this, 4)
        newRecyclerView.setHasFixedSize(true)

        // Navigation
        drawerlayout = findViewById(R.id.drawer_layout)
        val toolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.toolbar)
        val navigationView = findViewById<NavigationView>(R.id.nav_view)
        navigationView.setNavigationItemSelectedListener(this)

        val toggle = ActionBarDrawerToggle(
            this,
            drawerlayout,
            toolbar,
            R.string.open_nav,
            R.string.close_nav
        )
        drawerlayout.addDrawerListener(toggle)
        toggle.syncState()

        if (savedInstanceState == null) {
            //replaceFragment(HomeFragment())
            navigationView.setCheckedItem(R.id.home_nav)
        }

        // Initialize the ArrayList
        newArrayList2 = ArrayList()
        //Navigation
        navigationView.setCheckedItem(R.id.fav_nav)
        // Distribute cards when activity is created
        distributeCards()
    }

    private fun distributeCards() {
        // Clear the list to prevent duplicates
        newArrayList2.clear()

        // Ensure Fav_imageList is populated with actual Cards instances
       for (card in Fav_imageList) {
            newArrayList2.add(Cards(card)) // Assuming card is of type Cards
        }

        newRecyclerView.adapter = MyAdapter2()
    }

    // Navigation
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home_nav -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.fav_nav -> {
                // Stay in FavoriteActivity, do nothing
            }
        }
        drawerlayout.closeDrawer(GravityCompat.START)
        return true
    }

    override fun onBackPressed() {
        if (drawerlayout.isDrawerOpen(GravityCompat.START)) {
            drawerlayout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

}