package com.example.dentistryapp.domain.login.repository

interface LoginRepository {
    suspend fun logIn(login: String, password: String): String
}