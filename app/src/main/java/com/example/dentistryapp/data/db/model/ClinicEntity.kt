package com.example.dentistryapp.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "clinics")
data class ClinicEntity(
    @PrimaryKey
    val id: Int,
    val name: String,
    val address: String,
    val phone: String
)
