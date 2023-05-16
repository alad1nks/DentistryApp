package com.example.dentistryapp.domain.doctors.repository

import com.example.dentistryapp.domain.model.DoctorDomain

interface DoctorsRepository {
    suspend fun getDoctors(name: String): List<DoctorDomain>
    suspend fun getDoctorsFromDatabase(name: String): List<DoctorDomain>
}