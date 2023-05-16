package com.example.dentistryapp.domain.registration.repository

interface RegistrationRepository {
    suspend fun register(phoneNumber: String, password: String): String
}