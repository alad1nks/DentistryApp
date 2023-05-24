package com.example.dentistryapp.data.db.model

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "doctors")
data class DoctorEntity(
    @PrimaryKey
    val id: Int,
    val uid: Long,
    val name: String,
    val post: String,
    val exp: String
)