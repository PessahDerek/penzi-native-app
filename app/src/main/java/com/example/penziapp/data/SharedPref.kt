package com.example.penziapp.data

import android.content.Context
import android.content.SharedPreferences

class SharedPref(context: Context) {
    private val sharedPreference: SharedPreferences = context.getSharedPreferences(
        "penzi_pref", Context.MODE_PRIVATE
    )

    fun token(): String? {
        return sharedPreference.getString("token", "")
    }
    fun username(): String? {
        return sharedPreference.getString("username", "")
    }

    fun saveToken(token: String) {
        sharedPreference.edit().putString("token", token).apply()
    }

    fun clearToken() {
        sharedPreference.edit().remove("token").apply()
    }

    fun saveUsername(username: String) {
        sharedPreference.edit().putString("username", username).apply()
    }

    fun clearBoth() {
        sharedPreference.edit().remove("token").remove("username").apply()
    }
}