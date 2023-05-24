package com.example.dentistryapp.data.db

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.dentistryapp.data.db.model.AppointmentEntity
import com.example.dentistryapp.data.db.model.ClinicEntity
import com.example.dentistryapp.data.db.model.DoctorEntity

@Database(
    entities = [
        AppointmentEntity::class,
        ClinicEntity::class,
        DoctorEntity::class
    ],
    version = 1,
    exportSchema = false
)
abstract class DentistryDatabase : RoomDatabase() {
    abstract fun dentistryDao(): DentistryDao
}