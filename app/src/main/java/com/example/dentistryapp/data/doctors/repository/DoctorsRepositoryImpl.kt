package com.example.dentistryapp.data.doctors.repository

import com.example.dentistryapp.data.api.DentistryApi
import com.example.dentistryapp.data.model.GetDoctorBody
import com.example.dentistryapp.data.model.SearchDoctorBody
import com.example.dentistryapp.data.model.GetDoctorResponse
import com.example.dentistryapp.data.model.GetSelectedDoctorResponse
import com.example.dentistryapp.domain.doctors.repository.DoctorsRepository
import com.example.dentistryapp.domain.model.DoctorDomain
import javax.inject.Inject

class DoctorsRepositoryImpl @Inject constructor(
    private val api: DentistryApi
) : DoctorsRepository {
    override suspend fun getDoctors(name: String): List<DoctorDomain> {
        return api.searchDoctors(SearchDoctorBody(name)).result.toDomain()
    }

    override suspend fun getDoctorsFromDatabase(name: String): List<DoctorDomain> {
        return emptyList()
    }

    override suspend fun getSelectedDoctor(id: Int): GetSelectedDoctorResponse {
        return api.getDoctor(GetDoctorBody(id))
    }

    private fun List<GetDoctorResponse>.toDomain(): List<DoctorDomain> {
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