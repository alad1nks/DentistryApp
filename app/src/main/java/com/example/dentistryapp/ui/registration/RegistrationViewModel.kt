package com.example.dentistryapp.ui.registration

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dentistryapp.domain.registration.usecases.RegisterUseCase
import com.example.dentistryapp.preferences.SharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegistrationViewModel @Inject constructor(
    private val registerUseCase: RegisterUseCase,
    private val preferences: SharedPreferences
) : ViewModel() {
    private val _phoneNumber: MutableLiveData<String> = MutableLiveData("")
    private val _name: MutableLiveData<String> = MutableLiveData("")
    private val _surname: MutableLiveData<String> = MutableLiveData("")
    private val _phoneNumberError: MutableLiveData<Boolean> = MutableLiveData(false)
    private val _nameError: MutableLiveData<Boolean> = MutableLiveData(false)
    private val _surnameError: MutableLiveData<Boolean> = MutableLiveData(false)
    private val _passwordError: MutableLiveData<Boolean> = MutableLiveData(false)
    private val _repeatPasswordError: MutableLiveData<Boolean> = MutableLiveData(false)
    private val _registered = MutableLiveData(false)
    val phoneNumberError: LiveData<Boolean> get() = _phoneNumberError
    val nameError: LiveData<Boolean> get() = _nameError
    val surnameError: LiveData<Boolean> get() = _surnameError
    val passwordError: LiveData<Boolean> get() = _passwordError
    val repeatPasswordError: LiveData<Boolean> get() = _repeatPasswordError
    val registered: LiveData<Boolean> get() = _registered

    fun register(phoneNumber: String, name: String, surname: String, password: String, repeatPassword: String) {
        viewModelScope.launch(Dispatchers.IO) {
            when (val state = registerUseCase.register(phoneNumber, name, surname, password, repeatPassword)) {
                is RegistrationScreenState.InputError -> {
                    _phoneNumberError.postValue(state.phoneNumberError)
                    _nameError.postValue(state.nameError)
                    _surnameError.postValue(state.surnameError)
                    _passwordError.postValue(state.passwordError)
                    _repeatPasswordError.postValue(state.repeatPasswordError)
                }
                RegistrationScreenState.Registered -> {
                    _registered.postValue(true)
                    preferences.setPhoneNumber(_phoneNumber.value!!)
                    preferences.setName(_name.value!!)
                    preferences.setSurname(_surname.value!!)
                }
                else -> {
                    _registered.postValue(false)
                }
            }
        }
    }

    fun removePhoneNumberError() {
        if (_phoneNumberError.value == true) {
            _phoneNumberError.value = false
        }
    }

    fun removeNameError() {
        if (_nameError.value == true) {
            _nameError.value = false
        }
    }

    fun removeSurnameError() {
        if (_surnameError.value == true) {
            _surnameError.value = false
        }
    }

    fun removePasswordError() {
        if (_passwordError.value == true) {
            _passwordError.value = false
        }
    }

    fun removeRepeatPasswordError() {
        if (_repeatPasswordError.value == true) {
            _repeatPasswordError.value = false
        }
    }
    fun setPhoneNumber(phoneNumber: String) {
        _phoneNumber.value = phoneNumber
    }
    fun setName(name: String) {
        _name.value = name
    }
    fun setSurname(surname: String) {
        _surname.value = surname
    }
    fun registered() {
        _registered.value = false
    }
}