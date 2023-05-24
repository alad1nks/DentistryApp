package com.example.dentistryapp.data.db

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.dentistryapp.data.db.model.DoctorEntity

@Dao
interface DentistryDao {


    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertFavouriteFilm(doctors: List<DoctorEntity>)

    @Query("DELETE FROM doctors")
    fun clearDoctors()
}