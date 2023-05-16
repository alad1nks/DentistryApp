package com.example.dentistryapp.ui.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dentistryapp.domain.login.usecases.LoginUseCase
import com.example.dentistryapp.preferences.SharedPreferences
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(
    private val loginUseCase: LoginUseCase,
    private val sharedPreferences: SharedPreferences
) : ViewModel() {
    private val _authorized: MutableLiveData<Boolean> = MutableLiveData()
    val authorized: LiveData<Boolean> get() = _authorized
    fun logIn(login: String, password: String) {
        viewModelScope.launch(Dispatchers.IO) {
            if (loginUseCase.logIn(login, password) == LoginScreenState.AUTHORIZED) {
                sharedPreferences.logIn()
                _authorized.postValue(true)
            }
        }
    }

    fun loggedIn() {
        _authorized.value = false
    }
}