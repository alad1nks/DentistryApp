package com.example.dentistryapp.data.registration.repository

import com.example.dentistryapp.data.api.DentistryApi
import com.example.dentistryapp.data.api.model.RegistrationBody
import com.example.dentistryapp.domain.registration.repository.RegistrationRepository
import javax.inject.Inject

class RegistrationRepositoryImpl @Inject constructor(
    private val api: DentistryApi
) : RegistrationRepository {
    override suspend fun register(phoneNumber: String, password: String): String {
        return api.register(
            RegistrationBody(
                phoneNumber,
                password,
                1
            )
        ).response
    }
}