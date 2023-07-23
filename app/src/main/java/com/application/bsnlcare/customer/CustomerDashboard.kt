package com.application.bsnlcare.customer

import android.content.Intent
import android.os.Bundle
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import com.google.firebase.auth.FirebaseAuth
import com.application.bsnlcare.R
import com.application.bsnlcare.customer.fragments.CustomerHome

class CustomerDashboard : AppCompatActivity() {

    companion object{
        lateinit var plan: String
        lateinit var recommended: String
        lateinit var mybsnl: String
        lateinit var experience: String
        lateinit var tollfree: String
        lateinit var twitter: String
        lateinit var feedback: String
        lateinit var gender: String
        var nameOfCustomer: String? = ""
        var emailOfCustomer: String? = ""
        var registrationNumber: String? = ""
    }

    private lateinit var logout_Btn : ImageView
    private lateinit var back_Btn : ImageView
    private lateinit var mAuth: FirebaseAuth

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_dashboard)

        mAuth = FirebaseAuth.getInstance()

        logout_Btn = findViewById(R.id.logout_button)
        logout_Btn.setOnClickListener(){
            
            mAuth.signOut()
            startActivity(Intent(this, CustomerLogin::class.java))
            finish()
        }
        back_Btn = findViewById(R.id.btnBack)
        back_Btn.setOnClickListener(){
            onBackPressed()
            finish()
        }
        // fragment ---------
        val roomTypeFragment = CustomerHome()

        supportFragmentManager.beginTransaction()
            .add(R.id.fragment_container, roomTypeFragment)
            .commit()
    }

}