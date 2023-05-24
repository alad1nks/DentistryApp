package com.example.dentistryapp.data.clinics.repository

import com.example.dentistryapp.data.api.DentistryApi
import com.example.dentistryapp.domain.clinics.repository.ClinicsRepository
import com.example.dentistryapp.domain.model.ClinicDomain
import javax.inject.Inject

class ClinicsRepositoryImpl @Inject constructor(
    private val api: DentistryApi
) : ClinicsRepository {
    override suspend fun getClinics(): List<ClinicDomain> {
        return api.searchClinics().result.toDomain()
    }

    override suspend fun getClinicsFromDatabase(): List<ClinicDomain> {
        return emptyList()
    }

    private fun List<com.example.dentistryapp.data.api.model.ClinicResponse>.toDomain(): List<ClinicDomain> {
        return this.map {
            ClinicDomain(
                id = it.id,
                name = it.name,
                address = it.address,
                phone = it.phone
            )
        }
    }
}