package com.application.bsnlcare.customer.fragments

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.*
import com.application.bsnlcare.R
import com.application.bsnlcare.customer.CustomerDashboard
import com.application.bsnlcare.customer.CustomerSelectedDetails


class Feedback : Fragment() {

    lateinit var viewFeedback: View
    lateinit var nextButton: Button
    lateinit var editText: EditText

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewFeedback = inflater.inflate(R.layout.fragment_feedback, container, false)

        editText = viewFeedback.findViewById(R.id.EditText)

        nextButton = viewFeedback.findViewById(R.id.next_button)
        nextButton.setOnClickListener {
            val enteredValue = editText.text.toString().trim()

            // Regular expression to check if the entered value contains at least one alphabet (letter) and no special characters
            val regex = Regex("^(?=.*[a-zA-Z])[a-zA-Z0-9]+$")

            if (!enteredValue.matches(regex)) {
                Toast.makeText(requireContext(), "Please enter a valid room number containing at least one alphabet (letter)!", Toast.LENGTH_SHORT).show()
            } else {
                CustomerDashboard.feedback = enteredValue

                val intent = Intent(requireContext(), CustomerSelectedDetails::class.java)
                intent.putExtra("plan", CustomerDashboard.plan)
                intent.putExtra("recommended", CustomerDashboard.recommended)
                intent.putExtra("mybsnl", CustomerDashboard.mybsnl)
                intent.putExtra("experience", CustomerDashboard.experience)
                intent.putExtra("tollfree", CustomerDashboard.tollfree)
                intent.putExtra("twitter", CustomerDashboard.twitter)
                intent.putExtra("feedback", CustomerDashboard.feedback)
                intent.putExtra("gender", CustomerDashboard.gender)
                intent.putExtra("name", CustomerDashboard.nameOfCustomer)
                intent.putExtra("email", CustomerDashboard.emailOfCustomer)
                intent.putExtra("reg", CustomerDashboard.registrationNumber)
                startActivity(intent)
                requireActivity().finish()
            }
        }
        return viewFeedback
    }
}