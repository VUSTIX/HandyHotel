package com.example.handyhotel

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import java.util.*

class StartActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start)

        val sharedPreferences: SharedPreferences = getSharedPreferences("MyPrefs", Context.MODE_PRIVATE)
        val verificationId = sharedPreferences.getString("verificationId", null)

        if (verificationId != null) {
            val intent = Intent(this, AppActivity::class.java)
            intent.putExtra("selectedTab", R.id.navigation_hotels)
            startActivity(intent)
            finish()
        } else {
            val intent = Intent(this, AuthorizationActivity::class.java)
            startActivity(intent)
            finish()
        }

    }
}