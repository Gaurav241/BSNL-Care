package com.application.bsnlcare.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

@Dao
interface ResponseDetailsDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertResponseDetails(responseDetails: ResponseDetails)

    @Query("SELECT * FROM ResponseDetails WHERE registrationNumber = :registrationNumber")
    suspend fun getResponseDetails(registrationNumber: String): ResponseDetails?

    @Query("SELECT * FROM responseDetails")
    suspend fun getAllResponseDetails(): List<ResponseDetails>

    @Query("DELETE FROM responseDetails")
    suspend fun deleteAllResponseDetails()
}
