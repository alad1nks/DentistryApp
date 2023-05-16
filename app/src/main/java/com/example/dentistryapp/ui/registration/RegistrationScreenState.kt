package com.example.dentistryapp.ui.registration

sealed interface RegistrationScreenState {
    class InputError(
        val phoneNumberError: Boolean,
        val nameError: Boolean,
        val surnameError: Boolean,
        val passwordError: Boolean,
        val repeatPasswordError: Boolean
    ) : RegistrationScreenState
    object Registered : RegistrationScreenState
    object ConnectionError : RegistrationScreenState
}