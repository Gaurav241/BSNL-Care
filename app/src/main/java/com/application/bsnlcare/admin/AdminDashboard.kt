package com.application.bsnlcare.admin

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ImageView
import com.application.bsnlcare.R
import com.application.bsnlcare.customer.CustomerSelectedDetails

class AdminDashboard : AppCompatActivity() {

    lateinit var logoutBtn: ImageView
    private lateinit var back_Btn : ImageView
    lateinit var checkDetail : Button
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_admin_dashboard)


        checkDetail = findViewById(R.id.check_customer_details)
        checkDetail.setOnClickListener(){
            val intent = Intent(this, CheckCustomerDetails::class.java)
            startActivity(intent)
        }



        logoutBtn = findViewById(R.id.logout_button_admin)
        logoutBtn.setOnClickListener(){
            // Clear the "is_logged_in" flag in SharedPreferences
            val sharedPrefs = getSharedPreferences("admin_prefs", Context.MODE_PRIVATE)
            val editor: SharedPreferences.Editor = sharedPrefs.edit()
            editor.putBoolean("is_logged_in", false)
            editor.apply()

            // Launch the AdminLogin activity and finish the current activity
            val intent = Intent(this, AdminLogin::class.java)
            startActivity(intent)
            finish()
        }

        // back button
        back_Btn = findViewById(R.id.btnBack)
        back_Btn.setOnClickListener(){
            onBackPressed()
            finish()
        }
    }
}