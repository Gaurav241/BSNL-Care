package com.application.bsnlcare.admin

import android.os.Bundle
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.application.bsnlcare.R
import com.application.bsnlcare.db.ResponseDetails
import com.application.bsnlcare.db.ResponseDetailsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await


@Database(entities = [ResponseDetails::class], version = 1)
abstract class ResponseDatabase : RoomDatabase() {
    abstract fun responseDetailsDao(): ResponseDetailsDao
}

class CheckCustomerDetails : AppCompatActivity() {

    lateinit var res: TextView
//    lateinit var deleteBtn: Button
    private lateinit var firestore: FirebaseFirestore
    private lateinit var responsesCollection: CollectionReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_check_customer_details)



        // Database


        val db = Room.databaseBuilder(
            applicationContext,
            ResponseDatabase::class.java,
            "response-database"
        ).build()



        // Firestore
        firestore = FirebaseFirestore.getInstance()
        responsesCollection = firestore.collection("responses")

        // Retrieve data from Firestore
        GlobalScope.launch(Dispatchers.IO) {
            val allResponseDetails = responsesCollection.get().await().toObjects(ResponseDetails::class.java)
            res = findViewById(R.id.res)
            // Display the retrieved data in the UI thread
            launch(Dispatchers.Main) {
                var details = ""


                for (response in allResponseDetails) {
                  if(response.emailOfCustomer != "") {
                      details += "Name: ${response.nameOfCustomer}\n" +
                              "Number: ${response.registrationNumber}\n" +
                              "Email: ${response.emailOfCustomer}\n" +
                              "Complaint: ${response.feedback}\n" +
                              "Gender: ${response.gender}\n" +
                              "Twitter: ${response.twitter}\n" +
                              "TollFree: ${response.tollfree}\n" +
                              "Experience: ${response.experience}\n" +
                              "MyBsnl: ${response.mybsnl}\n" +
                              "Recommended: ${response.recommended}\n" +
                              "Plan: ${response.plan}\n"
                  }
                }
                res.text = details;
            }
        }


//        deleteBtn = findViewById(R.id.delete_button)
//        deleteBtn.setOnClickListener() {
//            GlobalScope.launch(Dispatchers.IO) {
//                db.responseDetailsDao().deleteAllResponseDetails()
//            }
//            res.text = ""
//            deleteBtn.isEnabled = false
//        }

    }
}