package com.application.bsnlcare.customer.fragments

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.application.bsnlcare.R
import com.application.bsnlcare.customer.CustomerDashboard

class Twitter : Fragment() {

    lateinit var viewTwitter: View
    lateinit var rgrp: RadioGroup
    lateinit var nextButton: Button
    lateinit var twitterButton: Button // New Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewTwitter = inflater.inflate(R.layout.fragment_twitter, container, false)

        rgrp = viewTwitter.findViewById(R.id.rgrp)

        nextButton = viewTwitter.findViewById(R.id.next_button)
        nextButton.setOnClickListener {
            val selectedId = rgrp.checkedRadioButtonId
            if (selectedId == -1) {
                Toast.makeText(requireContext(), "Please select any one!!", Toast.LENGTH_SHORT).show()
            } else {
                val radioButton = rgrp.findViewById<RadioButton>(selectedId)
                CustomerDashboard.twitter = radioButton.text.toString()

                // change fragment
                val feedback = Feedback()
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, feedback).commit()
            }
        }

        twitterButton = viewTwitter.findViewById(R.id.twitter_button)
        twitterButton.setOnClickListener {
            val twitterUrl = "https://twitter.com/UmeshCEO"
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(twitterUrl))
            startActivity(intent)
        }

        return viewTwitter
    }
}
