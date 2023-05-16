package com.example.dentistryapp.data.login.repository

import android.util.Log
import com.example.dentistryapp.data.api.DentistryApi
import com.example.dentistryapp.data.model.LoginBody
import com.example.dentistryapp.domain.login.repository.LoginRepository
import com.example.dentistryapp.preferences.SharedPreferences
import javax.inject.Inject

class LoginRepositoryImpl @Inject constructor(
    private val api: DentistryApi,
    private val preferences: SharedPreferences
) : LoginRepository {
    override suspend fun logIn(login: String, password: String): String {
        Log.d("start", "sasstart")
        val jwt = api.login(loginBody = LoginBody(login, password)).jwt
        Log.d("jwt", jwt)
        val id = api.getProfileInfo("jwt=$jwt").id
        Log.d("id", id.toString())
        preferences.setId(id)
        return jwt
    }
}