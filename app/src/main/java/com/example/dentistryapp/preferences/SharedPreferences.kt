package com.example.dentistryapp.preferences

import android.content.Context
import dagger.hilt.android.qualifiers.ApplicationContext
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class SharedPreferences @Inject constructor(
    @ApplicationContext context: Context
) {
    companion object {
        const val ID = "id"
        const val PHONE_NUMBER = "phone_number"
        const val NAME = "name"
        const val SURNAME = "surname"
        const val JWT = "jwt"
        const val IS_LOGGED_IN = "is_authorized"
    }
    private val preference = context.getSharedPreferences("pref", Context.MODE_PRIVATE)
    private val editor = preference.edit()

    fun getId(): Int {
        return getInt(ID)
    }

    fun getPhoneNumber(): String {
        return getString(PHONE_NUMBER)
    }

    fun getName(): String {
        return getString(NAME)
    }

    fun getSurname(): String {
        return getString(SURNAME)
    }

    fun getJwt(): String {
        return getString(JWT)
    }

    fun isLoggedIn(): Boolean {
        return getBoolean(IS_LOGGED_IN)
    }

    fun setId(id: Int) {
        saveInt(ID, id)
    }

    fun setPhoneNumber(phoneNumber: String) {
        saveString(PHONE_NUMBER, phoneNumber)
    }

    fun setName(name: String) {
        saveString(NAME, name)
    }

    fun setSurname(surname: String) {
        saveString(SURNAME, surname)
    }

    fun setJwt(jwt: String) {
        saveString(JWT, jwt)
    }

    fun logIn() {
        saveBoolean(IS_LOGGED_IN, true)
    }

    fun logOut() {
        saveBoolean(IS_LOGGED_IN, false)
    }



    private fun saveString(key: String, value: String) {
        editor.putString(key, value).apply()
    }

    private fun getString(key: String, defaultValue: String = ""): String {
        return preference.getString(key, defaultValue) ?: defaultValue
    }

    private fun saveInt(key: String, value: Int) {
        editor.putInt(key, value).apply()
    }

    private fun getInt(key: String, defaultValue: Int = 0): Int {
        return preference.getInt(key, defaultValue)
    }

    private fun saveBoolean(key: String, value: Boolean) {
        editor.putBoolean(key, value).apply()
    }

    private fun getBoolean(key: String, defaultValue: Boolean = false): Boolean {
        return preference.getBoolean(key, defaultValue)
    }
}