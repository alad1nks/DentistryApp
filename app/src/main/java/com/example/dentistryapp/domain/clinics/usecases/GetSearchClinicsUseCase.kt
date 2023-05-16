package com.example.dentistryapp.domain.clinics.usecases

import com.example.dentistryapp.domain.model.ClinicDomain

interface GetSearchClinicsUseCase {
    suspend fun search(request: String): List<ClinicDomain>
}