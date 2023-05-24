package com.example.dentistryapp.data.doctors.repository

import com.example.dentistryapp.data.api.DentistryApi
import com.example.dentistryapp.domain.doctors.repository.DoctorsRepository
import com.example.dentistryapp.domain.model.DoctorDomain
import javax.inject.Inject

class DoctorsRepositoryImpl @Inject constructor(
    private val api: DentistryApi
) : DoctorsRepository {
    override suspend fun getDoctors(name: String): List<DoctorDomain> {
        return api.searchDoctors(com.example.dentistryapp.data.api.model.SearchDoctorBody(name)).result.toDomain()
    }

    override suspend fun getDoctorsFromDatabase(name: String): List<DoctorDomain> {
        return emptyList()
    }

    override suspend fun getSelectedDoctor(id: Int): com.example.dentistryapp.data.api.model.GetSelectedDoctorResponse {
        return api.getDoctor(com.example.dentistryapp.data.api.model.GetDoctorBody(id))
    }

    private fun List<com.example.dentistryapp.data.api.model.GetDoctorResponse>.toDomain(): List<DoctorDomain> {
        return this.map {
            DoctorDomain(
                id = it.id,
                uid = it.uid,
                name = it.name,
                specialization = it.post,
                experience = it.exp
            )
        }
    }
}