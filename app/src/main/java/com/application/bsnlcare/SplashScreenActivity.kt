package com.application.bsnlcare

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.FirebaseApp
import com.google.firebase.firestore.FirebaseFirestore


class SplashScreenActivity : AppCompatActivity() {
    private lateinit var db: FirebaseFirestore
    override fun onCreate(savedInstanceState: Bundle?) {
        FirebaseApp.initializeApp(this);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_splash_screen)
        db = FirebaseFirestore.getInstance()
        Handler(Looper.getMainLooper()).postDelayed({
            startActivity(Intent(this, GlobalLogin::class.java))
            finish()
        }, 4000)
    }
}