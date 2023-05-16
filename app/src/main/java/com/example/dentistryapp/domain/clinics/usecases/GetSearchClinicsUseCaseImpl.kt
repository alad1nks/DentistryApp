package com.example.dentistryapp.domain.clinics.usecases

import com.example.dentistryapp.domain.clinics.repository.ClinicsRepository
import com.example.dentistryapp.domain.model.ClinicDomain
import javax.inject.Inject

class GetSearchClinicsUseCaseImpl @Inject constructor(
    private val repository: ClinicsRepository
) : GetSearchClinicsUseCase {
    override suspend fun search(request: String): List<ClinicDomain> {
        return repository.getClinics()
    }

}