package com.example.dentistryapp.ui.registration

sealed interface RegistrationScreenState {
    data class OnRegistration(
        var phoneNumberError: Boolean,
        var nameError: Boolean,
        var surnameError: Boolean,
        var passwordError: Boolean,
        var repeatPasswordError: Boolean
    ) : RegistrationScreenState
    object Registered : RegistrationScreenState
    object ConnectionError : RegistrationScreenState
    object UserAlreadyExistsError : RegistrationScreenState
}