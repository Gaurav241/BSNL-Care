package com.application.bsnlcare.customer.fragments

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import com.application.bsnlcare.R
import com.application.bsnlcare.customer.CustomerDashboard

class Experience : Fragment() {

    lateinit var viewExperience: View
    lateinit var rgrp : RadioGroup
    lateinit var nextButton: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewExperience =  inflater.inflate(R.layout.fragment_experience, container, false)

        rgrp = viewExperience.findViewById(R.id.rgrp)

        nextButton = viewExperience.findViewById(R.id.next_button)
        nextButton.setOnClickListener(){
            var selectId = rgrp.checkedRadioButtonId
            if (selectId == -1){
                Toast.makeText(requireContext(),"Please select any one!!", Toast.LENGTH_SHORT).show()
            } else {
                val rB = rgrp.findViewById<RadioButton>(selectId)
                CustomerDashboard.experience = rB.text.toString()

                // change fragment
                val tollfree = Tollfree()
                requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragment_container,tollfree).commit()

            }
        }


        return viewExperience
    }

}