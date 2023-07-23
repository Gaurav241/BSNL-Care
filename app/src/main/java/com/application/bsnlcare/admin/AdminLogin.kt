package com.application.bsnlcare.admin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.ImageView
import android.widget.Toast
import com.application.bsnlcare.R

class AdminLogin : AppCompatActivity() {

    lateinit var userTxt: EditText
    lateinit var passTxt: EditText
    lateinit var loginBtn: Button
    private lateinit var back_Btn : ImageView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_login)

        userTxt = findViewById(R.id.user_edit_text_admin)
        passTxt = findViewById(R.id.password_edit_text_admin)
        loginBtn = findViewById(R.id.login_button_admin)


        // Get SharedPreferences instance
        val sharedPrefs = getSharedPreferences("admin_prefs", Context.MODE_PRIVATE)

        // Check if admin is already logged in
        if (sharedPrefs.getBoolean("is_logged_in", false)) {
            val intent = Intent(this, AdminDashboard::class.java)
            startActivity(intent)
            finish()
        }

        loginBtn.setOnClickListener(){
            val user = userTxt.text.toString()
            val pass = passTxt.text.toString()
            if (user == "admin" && pass == "admin"){

                // Save the flag indicating that the admin is logged in
                val editor: SharedPreferences.Editor = sharedPrefs.edit()
                editor.putBoolean("is_logged_in", true)
                editor.apply()

                val intent = Intent(this, AdminDashboard::class.java)
                startActivity(intent)
                finish()
            }
            else {
                Toast.makeText(this, "Wrong Credentials Entered !!", Toast.LENGTH_SHORT).show()
            }
        }

        // back button
        back_Btn = findViewById(R.id.btnBack)
        back_Btn.setOnClickListener(){
            onBackPressed()
            finish()
        }

    }
}