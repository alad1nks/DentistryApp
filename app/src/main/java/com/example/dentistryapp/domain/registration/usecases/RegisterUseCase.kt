package com.example.dentistryapp.domain.registration.usecases

import com.example.dentistryapp.domain.registration.repository.RegistrationRepository
import com.example.dentistryapp.ui.registration.RegistrationScreenState
import javax.inject.Inject

class RegisterUseCase @Inject constructor(
    private val repository: RegistrationRepository
) {
    suspend fun register(phoneNumber: String, name: String, surname: String, password: String, repeatPassword: String): RegistrationScreenState {
        if (phoneNumber.length == 11 && name.isNotEmpty() && surname.isNotEmpty() && password.length >= 8 && password == repeatPassword) {
            return if (repository.register(phoneNumber, password)[0] == '-') {
                RegistrationScreenState.Registered
            } else {
                RegistrationScreenState.ConnectionError
            }
        }
        return RegistrationScreenState.OnRegistration(
            phoneNumber.length != 11,
            name.isEmpty(),
            surname.isEmpty(),
            password.length < 8,
            repeatPassword != password
        )
    }
}