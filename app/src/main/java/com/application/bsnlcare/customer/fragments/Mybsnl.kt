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
import android.content.Intent
import android.net.Uri
import com.google.android.material.button.MaterialButton
import com.application.bsnlcare.R
import com.application.bsnlcare.customer.CustomerDashboard

class Mybsnl : Fragment() {

    lateinit var viewMybsnl: View
    lateinit var rgrp : RadioGroup
    lateinit var nextButton: Button

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        viewMybsnl =  inflater.inflate(R.layout.fragment_mybsnl, container, false)

        rgrp = viewMybsnl.findViewById(R.id.rgrp)

        nextButton = viewMybsnl.findViewById(R.id.next_button)
        nextButton.setOnClickListener(){
            var selectId = rgrp.checkedRadioButtonId
            if (selectId == -1){
                Toast.makeText(requireContext(),"Please select any one!!", Toast.LENGTH_SHORT).show()
            } else {
                val rB = rgrp.findViewById<RadioButton>(selectId)
                CustomerDashboard.mybsnl = rB.text.toString()

                // change fragment
                val experience = Experience()
                requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragment_container,experience).commit()
            }
        }

        val playstoreButton = viewMybsnl.findViewById<MaterialButton>(R.id.playstore_button)
        playstoreButton.setOnClickListener {
            openPlayStore()
        }

        return viewMybsnl
    }

    private fun openPlayStore() {
        val packageName = "in.bsnl.portal.bsnlportal"
        try {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("market://details?id=$packageName"))
            startActivity(intent)
        } catch (e: android.content.ActivityNotFoundException) {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse("https://play.google.com/store/apps/details?id=$packageName"))
            startActivity(intent)
        }
    }
}
