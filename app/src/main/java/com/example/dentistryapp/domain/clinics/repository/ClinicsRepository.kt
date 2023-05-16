package com.example.dentistryapp.domain.clinics.repository

import com.example.dentistryapp.domain.model.ClinicDomain

interface ClinicsRepository {
    suspend fun getClinics(): List<ClinicDomain>
    suspend fun getClinicsFromDatabase(): List<ClinicDomain>
}