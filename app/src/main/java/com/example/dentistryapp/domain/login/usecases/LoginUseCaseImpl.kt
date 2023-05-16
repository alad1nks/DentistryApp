package com.example.dentistryapp.domain.login.usecases

import com.example.dentistryapp.domain.login.repository.LoginRepository
import com.example.dentistryapp.preferences.SharedPreferences
import com.example.dentistryapp.ui.login.LoginScreenState
import retrofit2.HttpException
import java.io.IOException
import javax.inject.Inject

class LoginUseCaseImpl @Inject constructor(
    private val repository: LoginRepository,
    private val sharedPreferences: SharedPreferences
) : LoginUseCase {
    override suspend fun logIn(login: String, password: String): LoginScreenState {
        return try {
            val jwt = repository.logIn(login, password)
            sharedPreferences.setJwt(jwt)
            LoginScreenState.AUTHORIZED
        } catch (e: IOException) {
            LoginScreenState.NETWORK_ERROR
        } catch (e: HttpException) {
            LoginScreenState.PASSWORD_ERROR
        }
    }
}