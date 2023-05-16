package com.example.dentistryapp.domain.login.usecases

import com.example.dentistryapp.ui.login.LoginScreenState

interface LoginUseCase {
    suspend fun logIn(login: String, password: String): LoginScreenState
}