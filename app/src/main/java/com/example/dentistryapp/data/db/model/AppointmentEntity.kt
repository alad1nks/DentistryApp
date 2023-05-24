package com.example.dentistryapp.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "appointments")
data class AppointmentEntity(
    @PrimaryKey
    val id: Int,
    val pid: Int,
    val did: Int,
    val sid: Int,
    val timeBegin: String,
    val timeEnd: String
)
