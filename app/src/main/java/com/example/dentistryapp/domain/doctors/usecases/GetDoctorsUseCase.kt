package com.example.dentistryapp.domain.doctors.usecases

import com.example.dentistryapp.domain.model.DoctorDomain

interface GetDoctorsUseCase {
    suspend fun search(request: String): List<DoctorDomain>
}