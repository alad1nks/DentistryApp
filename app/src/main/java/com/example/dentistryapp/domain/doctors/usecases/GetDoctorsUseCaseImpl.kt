package com.example.dentistryapp.domain.doctors.usecases

import android.util.Log
import com.example.dentistryapp.domain.doctors.repository.DoctorsRepository
import com.example.dentistryapp.domain.model.DoctorDomain
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class GetDoctorsUseCaseImpl @Inject constructor(
    private val repository: DoctorsRepository
) : GetDoctorsUseCase {

    override suspend fun search(request: String): List<DoctorDomain> {
        return repository.getDoctors(request)
    }
}