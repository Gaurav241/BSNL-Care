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

class Tollfree : Fragment() {

    lateinit var viewTollfree: View
    lateinit var rgrp: RadioGroup
    lateinit var nextButton: Button
    lateinit var phoneButton: Button // New Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewTollfree = inflater.inflate(R.layout.fragment_tollfree, container, false)

        rgrp = viewTollfree.findViewById(R.id.rgrp)

        nextButton = viewTollfree.findViewById(R.id.next_button)
        nextButton.setOnClickListener {
            val selectedId = rgrp.checkedRadioButtonId
            if (selectedId == -1) {
                Toast.makeText(requireContext(), "Please select any one!!", Toast.LENGTH_SHORT).show()
            } else {
                val radioButton = rgrp.findViewById<RadioButton>(selectedId)
                CustomerDashboard.tollfree = radioButton.text.toString()

                // change fragment
                val twitter = Twitter()
                requireActivity().supportFragmentManager.beginTransaction()
                    .replace(R.id.fragment_container, twitter).commit()
            }
        }

        // New Button Click Listener to Call BSNL Toll-Free Number
        phoneButton = viewTollfree.findViewById(R.id.phone_button)
        phoneButton.setOnClickListener {
            val tollFreeNumber = "18004444" // Replace with the actual toll-free number
            val intent = Intent(Intent.ACTION_DIAL, Uri.parse("tel:$tollFreeNumber"))
            startActivity(intent)
        }

        return viewTollfree
    }
}
