package com.application.bsnlcare.customer.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.RadioGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.application.bsnlcare.R
import com.application.bsnlcare.customer.CustomerSelectedDetails


class CustomerHome : Fragment() {

    lateinit var viewRoomType: View
    lateinit var rgrp : RadioGroup
    lateinit var nextButton1: Button
    lateinit var nextButton: Button
    lateinit var res: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewRoomType =  inflater.inflate(R.layout.fragment_customer_home, container, false)
        nextButton1 = viewRoomType.findViewById(R.id.but)
        nextButton1.setOnClickListener {
            val intent = Intent(requireContext(), CustomerSelectedDetails::class.java)
            startActivity(intent)
            requireActivity().finish()
        }
        nextButton = viewRoomType.findViewById(R.id.next_button)
        nextButton.setOnClickListener(){

                val plan = Plan()
                requireActivity().supportFragmentManager.beginTransaction().replace(R.id.fragment_container,plan).commit()
        }
        return viewRoomType
    }
}