package com.application.bsnlcare.customer


import android.icu.text.SimpleDateFormat
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.application.bsnlcare.R
import com.application.bsnlcare.db.ResponseDetails
import com.application.bsnlcare.db.ResponseDetailsDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.tasks.await
import java.util.Date
import java.util.Locale

@Database(entities = [ResponseDetails::class], version = 1)
abstract class ResponseDatabase : RoomDatabase() {
    abstract fun responseDetailsDao(): ResponseDetailsDao
}

class CustomerSelectedDetails : AppCompatActivity() {


    lateinit var res: TextView
    lateinit var deleteBtn: Button
    lateinit var nextButton: Button
    private lateinit var firestore: FirebaseFirestore
    private lateinit var responsesCollection: CollectionReference

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_customer_selected_details)


        var reg = intent.getStringExtra("reg")
        var name = intent.getStringExtra("name")
        var email = intent.getStringExtra("email")
        var gender = intent.getStringExtra("gender")
        var feedback = intent.getStringExtra("feedback")
        var twitter = intent.getStringExtra("twitter")
        var tollfree = intent.getStringExtra("tollfree")
        var experience = intent.getStringExtra("experience")
        var mybsnl = intent.getStringExtra("mybsnl")
        var recommended = intent.getStringExtra("recommended")
        var plan = intent.getStringExtra("plan")


//        res.text = """Name : ${name}
//            |Reg. No.: ${reg}
//            |Email: ${email}
//            |Gender: ${gender}
//            |Hostel: ${hostel}
//            |Room Type: ${roomType}
//            |Seater: ${seater}
//            |Room: ${room} (Random)
//            |Bed: ${bed}
//        """.trimMargin()

        firestore = FirebaseFirestore.getInstance()
        responsesCollection = firestore.collection("responses")
        val currentUser = FirebaseAuth.getInstance().currentUser

        // Retrieve the user's email (username)
        val username = currentUser?.email
        // Retrieve data from Firestore
        GlobalScope.launch(Dispatchers.IO) {
            val query = responsesCollection.whereEqualTo("emailOfCustomer", username).get().await()
            val responseList = query.toObjects(ResponseDetails::class.java)

            // Display the retrieved data in the UI thread
            launch(Dispatchers.Main) {
                var details = ""

                res = findViewById(R.id.res)

                for (response in responseList) {
                    details += "Number: ${response.registrationNumber}\n" +
                            "Name: ${response.nameOfCustomer}\n" +
                            "Email: ${response.emailOfCustomer}\n" +
                            "Gender: ${response.gender}\n" +
                            "Feedback: ${response.feedback}\n" +
                            "Twitter: ${response.twitter}\n" +
                            "TollFree: ${response.tollfree}\n" +
                            "Experience: ${response.experience}\n" +
                            "MyBsnl: ${response.mybsnl}\n" +
                            "Recommended: ${response.recommended}\n" +
                            "Plan: ${response.plan}\n"

                    res.text = details;
                }


            }
        }
        // Database
        val currentDate = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault()).format(Date())

        val responseDetails = ResponseDetails(
            registrationNumber = reg ?: "",
            nameOfCustomer = name ?: "",
            emailOfCustomer = email ?: "",
            gender = gender ?: "",
            feedback = feedback ?: "",
            twitter = twitter ?: "",
            tollfree = tollfree ?: "",
            experience = experience ?: "",
            mybsnl = mybsnl ?: "",
            recommended = recommended ?: "",
            plan = plan ?: ""
        )


        val db = Room.databaseBuilder(
            applicationContext,
            ResponseDatabase::class.java,
            "response-database"
        ).build()
        firestore = FirebaseFirestore.getInstance()
        responsesCollection = firestore.collection("responses")

        GlobalScope.launch(Dispatchers.IO) {
            db.responseDetailsDao().insertResponseDetails(responseDetails)

        }
        var firestore = FirebaseFirestore.getInstance()
        responsesCollection = firestore.collection("responses")

        GlobalScope.launch(Dispatchers.IO) {
            db.responseDetailsDao().insertResponseDetails(responseDetails)


            responsesCollection.add(responseDetails)
                .addOnSuccessListener { documentReference ->
                    // Response details added successfully
                }
                .addOnFailureListener { e ->
                    // Error occurred while adding response details
                }
        }
        deleteBtn = findViewById(R.id.delete_button)
        deleteBtn.setOnClickListener() {
            GlobalScope.launch(Dispatchers.IO) {
                val email1 = username // Replace with the user's email provided

                // Retrieve the response details document based on the email
                val query = responsesCollection.whereEqualTo("emailOfCustomer", email1)
                val querySnapshot = query.get().await()

                if (!querySnapshot.isEmpty) {
                    // Delete the response details document
                    val document = querySnapshot.documents[0]
                    document.reference.delete().await()
                }
            }
            res.text = ""
            deleteBtn.isEnabled = false
        }

    }
}