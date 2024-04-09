package com.example.handyhotel

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.widget.Button
import android.widget.ImageButton
import androidx.appcompat.app.AppCompatActivity
import androidx.viewpager.widget.ViewPager
import androidx.viewpager2.widget.ViewPager2

class HotelPageActivity : AppCompatActivity() {

    private lateinit var likeButton: ImageButton
    private lateinit var backButton: ImageButton
    private var checkLike: Int = 0


    @SuppressLint("MissingInflatedId")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_hotel_page)

        val photosList = listOf(
            R.drawable.room1,
            R.drawable.room2,
            R.drawable.room3,
        )

        val viewPager: ViewPager = findViewById(R.id.viewPager)

        val adapter = AdapterPhoto(photosList)
        viewPager.adapter = adapter

        backButton = findViewById(R.id.backButton)
        backButton.setOnClickListener {
            @Suppress("DEPRECATION")
            onBackPressed()
        }

        likeButton = findViewById(R.id.likeButton)
        likeButton.setOnClickListener {
            if(checkLike == 0) {
                likeButton.setImageResource(R.drawable.ic_like2)
                checkLike++
            }
            else {
            likeButton.setImageResource(R.drawable.ic_like)
                checkLike--
            }
        }
    }

}