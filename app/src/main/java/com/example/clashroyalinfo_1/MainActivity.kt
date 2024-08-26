package com.example.clashroyalinfo_1

import android.annotation.SuppressLint
import android.app.Activity
import android.content.Intent
import android.graphics.drawable.Drawable
import android.media.MediaPlayer
import android.os.Bundle
import android.util.Log
import android.view.Gravity
import android.view.MenuItem
import android.widget.Button
import android.widget.ImageView
import android.widget.Toolbar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.ActionBarDrawerToggle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.graphics.drawable.toDrawable
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentTransaction
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.navigation.NavigationView

 var Flag:Boolean=true

class MainActivity : AppCompatActivity(),NavigationView.OnNavigationItemSelectedListener {

    private lateinit var newRecyclerView: RecyclerView
    private lateinit var newArrayList: ArrayList<Cards>
    private lateinit var imageList: ArrayList<cardsdetail>
    private lateinit var sortedArrayList: Array<cardsdetail>
    private lateinit var sortButton: Button
    private lateinit var mediaPlayer: MediaPlayer
    private var sortByExcer = true

    private lateinit var drawerlayout: DrawerLayout

    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        imageList = ArrayList()
        sortedArrayList = arrayOf(
            cardsdetail(
                R.drawable.bats,
                2,
                "a",
                R.drawable.batsbackground,
                "Bats",
                "Common",
                "Troop",
                "Spawns a handful of titing creatures.Think of them as sweet,purple..baals of DESTRUCTION"
            ),
            cardsdetail(
                R.drawable.beka,
                7,
                "b",
                R.drawable.pekkabackground,
                "P.E.K.K.A",
                "Rare",
                "Troop",
                "The Arean is a certified butterfly free zone. No distraction for P.E.K.K.A"
            ),
            cardsdetail(
                R.drawable.ballon,
                5,
                "c",
                R.drawable.ballonbackground,
                "Ballon",
                "Epic",
                "Troop",
                "As pretty as they are, you won't want a parade of THESE ballons showing up on the horizon. Drops powerful bombs and when shot down,crashes dealing area damage",

            ),
            cardsdetail(
                R.drawable.bowler,
                5,
                "c",
                R.drawable.bawolerbackground,
                "Bowler",
                "Epic",
                "Troop",
                "This big blue  dude digs the simple things in life . Dark Elixir drinks and throwing rocks",

            ),
            cardsdetail(
                R.drawable.chost,
                3,
                "d",
                R.drawable.chostbackground,
                "Chost",
                "Legendary",
                "Troop",
                "He drifts invisibly through the Areana until he's started by an enemy...then he attacks! then he invisible again! Zzzz",

            ),
            cardsdetail(
                R.drawable.angry_barbarian,
                6,
                "a",
                R.drawable.eliterbarabarinsbackground,
                "Elite Barabrians",
                "Common",
                "Troop",
                "Spawns a pair of leveled up Barabrins. They're like regular Barabrians, only harder, better, faster and stronger",

            ),
            cardsdetail(
                R.drawable.babydragon,
                4,
                "c",
                R.drawable.babydragonbackground,
                "Baby Dragon",
                "Epic",
                "Troop",
                "Burps fireballs from the sky that deal area damage. he hatch cute, humgry and readt for a barbeque",

            ),
            cardsdetail(
                R.drawable.firecracker,
                3,
                "a",
                R.drawable.firecrakerbackground,
                "Firecreacker",
                "common",
                "Troop",
                "Shoots a firework that explodes upon impact,damaging the target and showing ant thing directly behind it with sparks",

            ),
            cardsdetail(
                R.drawable.fireball,
                4,
                "b",
                R.drawable.fireballbackground,
                "Fireball",
                "Rare",
                "Spell",
                "Annnnnnd...fireball.Incinerates a small area, dealing high damage",

            ),
            cardsdetail(
                R.drawable.electrro_wizerd,
                4,
                "d",
                R.drawable.electrowizerdbachground,
                "Electro Wizerd",
                "Legendary",
                "Troop",
                "He lands with a \"POW!\", stuns nearby enemies and shootslighting with both hands! What a show off ",

            ),
            cardsdetail(
                R.drawable.dark_prince,
                4,
                "c",
                R.drawable.darkprincebackground,
                "Dark Prince",
                "Epic",
                "Troop",
                "He deals area damage and lets his spiked club do the talking for him. because when he does talk,it sounds like he has a bucket on his head",

            ),
            cardsdetail(
                R.drawable.goblin_gang,
                3,
                "a",
                R.drawable.goblingangbachground,
                "Goblin Gang",
                "Common",
                "Troop",
                "Spawns six Goblins. three with knives, three with spears",

            ),
            cardsdetail(
                R.drawable.hogrider,
                4,
                "b",
                R.drawable.hogriderbachground,
                "Hogrider",
                "Rare",
                "Troop",
                "Fast malee Troop that Tragets buildings and can jump over the river. He followed the echoing call of \"HogRiderrrr\" all the way through the Arean doors",

            ),
            cardsdetail(
                R.drawable.graveyard,
                5,
                "d",
                R.drawable.graveyardebackground,
                "Graveyard",
                "Legendary",
                "Spell",
                "Surprise! It's a party. A Skeleton party, anywhere in the Arena. Yay!",

            ),
            cardsdetail(
                R.drawable.furnace,
                4,
                "b",
                R.drawable.furancebachground,
                "Furance",
                "Rare",
                "Bulding",
                "The Furnace spawns one Fire Spirit at a time. It also makes great brick-oven pancakes",

            ),
            cardsdetail(
                R.drawable.flying_machine,
                4,
                "b",
                R.drawable.flyingmachinebachground,
                "Flaying Machine",
                "Rare",
                "Troop",
                "The Master Builder has sent his first contraption to the Arena! It's a fast and Fun Flying machine, but Fragile!",

            ),
            cardsdetail(
                R.drawable.magicarcherpng,
                4,
                "d",
                R.drawable.magicarcherbackground,
                "Magic Archer",
                "Legendary",
                "Troop",
                "Not quite a Wizard, nor an Archer - he shoots a magic arrow that passes through and damages all enemies in its path. It's not a trick, it's magic",

            ),
            cardsdetail(
                R.drawable.fire_spirit,
                1,
                "a",
                R.drawable.firespritebackground,
                "Fire-Spirit",
                "Common",
                "Troop",
                "The Fire Spirit is on a mission to give you a warm hug. It'd be adorable if it wasn't on fire",

            ),
            cardsdetail(
                R.drawable.log,
                2,
                "d",
                R.drawable.logbackground,
                "The Log",
                "Legendary",
                "Spell",
                "A spilt bottle of Rage turned an innocent tree trunk into \"The Log\". Now, it seeks revenge by crushing anything in its path! Reduced damage to Crown Towers",

            ),
            cardsdetail(
                R.drawable.witch,
                5,
                "c",
                R.drawable.witchbackground,
                "Witch",
                "Epic",
                "Troop",
                "Summons Skeletons, shoots destructo beams, has glowing pink eyes that unfortunately don't shoot lasers",

            ),
cardsdetail(R.drawable.darkwitchpng,4,"d",R.drawable.nightwitchbackground,"Night Witch","Legendry","Troop","Summons Bats to deo her bidding! if you get too clode,  she's not afraid of pitching in withher mean-looking battle staff"),

            //////////////////////////
            cardsdetail(
                R.drawable.demolisher,
                4,
                "b",
                R.drawable.demolisherbackground,
                "Demolisher",
                "Rare",
                "Troop",
                "Boom goes the dynamite! Goblin Demolisher deals area damage and explodes on death",

                ),
            cardsdetail(
                R.drawable.electro_dragon,
                5,
                "c",
                R.drawable.electrodragonback,
                "Electro Dragon",
                "Epic",
                "Troop",
                "Spits out bolts of electricity hitting up to three tragets.",

                ),
            cardsdetail(
                R.drawable.valkyrie,
                4,
                "b",
                R.drawable.witchbackground,
                "Valkyrie",
                "Rare",
                "Troop",
                "Summons Skeletons, shoots destructo beams, has glowing pink eyes that unfortunately don't shoot lasers",

                ),
            cardsdetail(
                R.drawable.mini_bekapng,
                4,
                "b",
                R.drawable.miniprkkabackground,
                "Mini P.K.K.A",
                "Rare",
                "Troop",
                "The Arean is certified butterfly free zone. No distractions for P.K.K.E.A, only destruction",
                ),
            cardsdetail(
                R.drawable.skeletonarmy,
                3,
                "c",
                R.drawable.skeletonarmybackground,
                "Skeleton Army",
                "Epic",
                "Troop",
                "Summons Skeletons, shoots destructo beams, has glowing pink eyes that unfortunately don't shoot lasers",

                ),
            cardsdetail(
                R.drawable.minions,
                3,
                "a",
                R.drawable.miniprkkabackground,
                "Minions",
                "Common",
                "Troop",
                "Three fast, unarmored flying attackers. Roses are red, minions are blue, the can fly, and will crush you!",

                ),
            cardsdetail(
                R.drawable.delivery,
                3,
                "a",
                R.drawable.deliverybackground,
                "Delivary",
                "Common",
                "Spell",
                "Summons Skeletons, shoots destructo beams, has glowing pink eyes that unfortunately don't shoot lasers",

                ),
            cardsdetail(
                R.drawable.wizard_card,
                5,
                "b",
                R.drawable.wizerdbackground,
                "Wizerd",
                "Rare",
                "Troop",
                "The most awesome man to ever set foot in the Arean, the Wizerd will blow you away with his handsomeness... and/or fireballs",

                ),
            cardsdetail(
                R.drawable.littleprince,
                3,
                "e",
                R.drawable.littelprincebachground,
                "Littel Prince",
                "Chamion",
                "Troop",
                "who let their nephew into the Arean?! Tjis misschievous Royal gains Hit Speed when firing, as long as he stands still!",

                ),
            cardsdetail(
                R.drawable.archer_queen,
                5,
                "e",
                R.drawable.archerqueenback,
                "Archer Queen",
                "Chamion",
                "Troop",
                "who let their nephew into the Arean?! Tjis misschievous Royal gains Hit Speed when firing, as long as he stands still!",

                ),
            cardsdetail(
                R.drawable.sparky,
                6,
                "d",
                R.drawable.sparkybackground,
                "Sparky",
                "Legendary",
                "Troop",
                "who let their nephew into the Arean?! Tjis misschievous Royal gains Hit Speed when firing, as long as he stands still!",

                ),
            cardsdetail(
                R.drawable.motherwitch,
                4,
                "d",
                R.drawable.motherwitchbackground,
                "Mother Witch",
                "Legendary",
                "Troop",
                "who let their nephew into the Arean?! Tjis misschievous Royal gains Hit Speed when firing, as long as he stands still!",

                ),
            cardsdetail(
                R.drawable.skeletonking,
                4,
                "e",
                R.drawable.skeletonkingbackground,
                "Skeleton King",
                "Chamion",
                "Troop",
                "who let their nephew into the Arean?! Tjis misschievous Royal gains Hit Speed when firing, as long as he stands still!",

                ),
            cardsdetail(
                R.drawable.mega_knight,
                7,
                "d",
                R.drawable.littelprincebachground,
                "Mega King",
                "Epic",
                "Troop",
                "who let their nephew into the Arean?! Tjis misschievous Royal gains Hit Speed when firing, as long as he stands still!",

                ),
            cardsdetail(
                R.drawable.hulk,
                8,
                "c",
                R.drawable.littelprincebachground,
                "Hulk",
                "Epic",
                "Troop",
                "who let their nephew into the Arean?! Tjis misschievous Royal gains Hit Speed when firing, as long as he stands still!",

                ),
            cardsdetail(
                R.drawable.hogs,
                5,
                "b",
                R.drawable.hogsbackground,
                "Hogs",
                "Rare",
                "Troop",
                "who let their nephew into the Arean?! Tjis misschievous Royal gains Hit Speed when firing, as long as he stands still!",

                ),
            cardsdetail(
                R.drawable.healspirit,
                1,
                "b",
                R.drawable.littelprincebachground,
                "Healspirit",
                "Rare",
                "Troop",
                "who let their nephew into the Arean?! Tjis misschievous Royal gains Hit Speed when firing, as long as he stands still!",

                ),
            cardsdetail(
                R.drawable.wallbaker,
                2,
                "c",
                R.drawable.littelprincebachground,
                "Wall Baker",
                "Epic",
                "Troop",
                "who let their nephew into the Arean?! Tjis misschievous Royal gains Hit Speed when firing, as long as he stands still!",

                ),
            cardsdetail(
                R.drawable.skeletons,
                1,
                "a",
                R.drawable.littelprincebachground,
                "Skeletons",
                "Common",
                "Troop",
                "who let their nephew into the Arean?! Tjis misschievous Royal gains Hit Speed when firing, as long as he stands still!",

                ),
            cardsdetail(
                R.drawable.monk,
                5,
                "e",
                R.drawable.littelprincebachground,
                "Monk",
                "Chamion",
                "Troop",
                "Monk has spent many isolated years prefecting a new style of combat",

                ),
            cardsdetail(
                R.drawable.royalgiant,
                6,
                "a",
                R.drawable.littelprincebachground,
                "Royal Giant",
                "Common",
                "Troop",
                "who let their nephew into the Arean?! Tjis misschievous Royal gains Hit Speed when firing, as long as he stands still!",

                ),
            cardsdetail(
                R.drawable.recruits,
                7,
                "a",
                R.drawable.littelprincebachground,
                "Royal Recruits",
                "common",
                "Troop",
                "who let their nephew into the Arean?! Tjis misschievous Royal gains Hit Speed when firing, as long as he stands still!",

                ),
            cardsdetail(
                R.drawable.lavahound,
                7,
                "d",
                R.drawable.littelprincebachground,
                "Lava Hound",
                "Legendary",
                "Troop",
                "who let their nephew into the Arean?! Tjis misschievous Royal gains Hit Speed when firing, as long as he stands still!",

                ),
            cardsdetail(
                R.drawable.icegolem,
                2,
                "b",
                R.drawable.littelprincebachground,
                "Ice Golem",
                "Rare",
                "Troop",
                "who let their nephew into the Arean?! Tjis misschievous Royal gains Hit Speed when firing, as long as he stands still!",

                ),
            cardsdetail(
                R.drawable.goblin_barrel,
                3,
                "c",
                R.drawable.fireballbackground,
                "Goblin Barrel",
                "Epic",
                "Spell",
                "who let their nephew into the Arean?! Tjis misschievous Royal gains Hit Speed when firing, as long as he stands still!",

                ),
            cardsdetail(
                R.drawable.zappies,
                4,
                "b",
                R.drawable.littelprincebachground,
                "Zappies",
                "Rare",
                "Troop",
                "who let their nephew into the Arean?! Tjis misschievous Royal gains Hit Speed when firing, as long as he stands still!",

                ),
            cardsdetail(
                R.drawable.battleram,
                4,
                "b",
                R.drawable.littelprincebachground,
                "Battele Ram",
                "Rare",
                "Troop",
                "who let their nephew into the Arean?! Tjis misschievous Royal gains Hit Speed when firing, as long as he stands still!",

                ),
            cardsdetail(
                R.drawable.earthquake,
                3,
                "b",
                R.drawable.littelprincebachground,
                "EarthQuake",
                "Rare",
                "Spell",
                "who let their nephew into the Arean?! Tjis misschievous Royal gains Hit Speed when firing, as long as he stands still!",

                ),
            cardsdetail(
                R.drawable.bombtower,
                4,
                "b",
                R.drawable.bombtowerbackground,
                "Bomb Tower",
                "Rare",
                "Tower",
                "who let their nephew into the Arean?! Tjis misschievous Royal gains Hit Speed when firing, as long as he stands still!",

                ),
        )
        setImage()
        // Setup button to toggle sorting
        sortButton = findViewById(R.id.sortedButoon)
        sortButton.setOnClickListener {
            sortByExcer = !sortByExcer // Toggle the sort flag
            sortCards()
        }

        // Initialize RecyclerView
        newRecyclerView = findViewById(R.id.itemms)
        newRecyclerView.layoutManager = GridLayoutManager(this, 4)
        newRecyclerView.setHasFixedSize(true)

         sortCards() // Initial sorting and loading of cards

        //////////////////////////////////////////////


// Initialize MediaPlayer

        mediaPlayer = MediaPlayer.create(this, R.raw.music)

        if (mediaPlayer != null&& Flag) {
            mediaPlayer.isLooping = true // Set looping
            mediaPlayer.start()
            Flag=false
        }

        ///////////////////////////////////////////////////

        ///////////////////////////////////////////////////// navigation
        drawerlayout = findViewById<DrawerLayout>(R.id.drawer_layout)
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
          //  replaceFragment(HomeFragment())
            navigationView.setCheckedItem(R.id.home_nav)
        }
            /////////////////////////////////////////////////

    }


    private fun sortCards() {
        if (sortByExcer) {
            sortedArrayList.sortBy { it.Excer }
        } else {
            sortedArrayList.sortBy { it.level }
        }

        setImage()
        distributeCards()

    }

    private fun distributeCards() {
        newArrayList = ArrayList()
        for (card in sortedArrayList) {
            newArrayList.add(Cards(card.Image)) // Assuming Cards constructor takes an image reference
        }
        newRecyclerView.adapter = MyAdapter(newArrayList)
        var adapter = MyAdapter(newArrayList)
        newRecyclerView.adapter = adapter
        adapter.onItemClickListner(object : MyAdapter.onItemClickListner {
            override fun onItemClick(position: Int) {
                var intent = Intent(this@MainActivity, MainActivity2::class.java)
                intent.putExtra("ImageId", newArrayList[position].titleimage_1)
                intent.putExtra("name", sortedArrayList[position].name)
                intent.putExtra("Imagebachground", sortedArrayList[position].Imagebachground)
                intent.putExtra("Rarity", sortedArrayList[position].rarity)
                intent.putExtra("type", sortedArrayList[position].type)
                intent.putExtra("Explain", sortedArrayList[position].explain)
                //intent.putExtra("Isheart", sortedArrayList[position].IsHeart)
                intent.putExtra("basicImage",sortedArrayList[position].Image)
                startActivity(intent)
            }
        })

    }

    fun setImage() {
        Fav_imageList2.clear() // Clear the list before adding new items
        for (i in sortedArrayList) {
            val ob: Pair<Int, String> = Pair(i.Image, i.name)
            Fav_imageList2.add(ob)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        // Release MediaPlayer resources when done
        if (::mediaPlayer.isInitialized) {
            mediaPlayer.stop()
            mediaPlayer.release()
        }
    }

    ////////////////////////////////////// navigation
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.home_nav -> {
                val intent = Intent(this, MainActivity::class.java)
                startActivity(intent)
            }
            R.id.fav_nav -> {
                val intent = Intent(this, FavoriteActivity::class.java)
                startActivity(intent)
            }
        }
        drawerlayout.closeDrawer(GravityCompat.START)
        return true
    }


    override fun onBackPressed() {
        super.onBackPressed()
        if(drawerlayout.isDrawerOpen(GravityCompat.START)){
           drawerlayout.closeDrawer(GravityCompat.START)
       }
       else{
           onBackPressedDispatcher.onBackPressed()
       }
    }
   //////////////////////////////////////

}



