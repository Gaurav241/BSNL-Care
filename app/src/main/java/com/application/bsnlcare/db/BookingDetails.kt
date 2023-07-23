package com.application.bsnlcare.db


import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class ResponseDetails(
    @PrimaryKey val registrationNumber: String = "",
    val nameOfCustomer: String = "",
    val emailOfCustomer: String = "",
    val gender: String = "",
    val feedback: String = "",
    val twitter: String = "",
    val tollfree: String = "",
    val experience: String = "",
    val mybsnl: String = "",
    val recommended: String = "",
    val plan: String = ""
) {
    constructor() : this("", "", "", "", "","","","","","","")
}
