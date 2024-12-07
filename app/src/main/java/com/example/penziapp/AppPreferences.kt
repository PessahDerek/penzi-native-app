package com.example.penziapp

import android.content.Context
import android.content.Context.MODE_PRIVATE
import android.content.SharedPreferences
import androidx.core.content.edit

object AppPreferences {
    private lateinit var sharedPreferences: SharedPreferences

    // Call `AppPreferences.setup(applicationContext)` in your MainActivity's `onCreate` method
    fun setup(context: Context) {
        // Set your app name here
        sharedPreferences =
            context.getSharedPreferences("${context.packageName}.sharedprefs", MODE_PRIVATE)
    }

    // Replace these example attributes with your stored values
    var token: String?
        get() = Key.TOKEN.getString()
        set(value) = Key.TOKEN.setString(value)

    var username: String?
        get() = Key.USERNAME.getString()
        set(value) = Key.USERNAME.setString(value)

    private enum class Key {
        TOKEN, USERNAME; // Replace these cases with your stored values keys

        fun getBoolean(): Boolean? =
            if (::sharedPreferences.isInitialized && sharedPreferences.contains(name))
                sharedPreferences.getBoolean(name, false)
            else null

        fun getFloat(): Float? =
            if (::sharedPreferences.isInitialized && sharedPreferences.contains(name))
                sharedPreferences.getFloat(name, 0f)
            else null

        fun getInt(): Int? =
            if (::sharedPreferences.isInitialized && sharedPreferences.contains(name))
                sharedPreferences.getInt(name, 0)
            else null

        fun getLong(): Long? =
            if (::sharedPreferences.isInitialized && sharedPreferences.contains(name))
                sharedPreferences.getLong(name, 0)
            else null

        fun getString(): String? =
            if (::sharedPreferences.isInitialized && sharedPreferences.contains(name))
                sharedPreferences.getString(name, "")
            else null

        fun setBoolean(value: Boolean?) =
            value?.let { sharedPreferences.edit { putBoolean(name, it) } } ?: remove()

        fun setFloat(value: Float?) =
            value?.let { sharedPreferences.edit { putFloat(name, it) } } ?: remove()

        fun setInt(value: Int?) =
            value?.let { sharedPreferences.edit { putInt(name, it) } } ?: remove()

        fun setLong(value: Long?) =
            value?.let { sharedPreferences.edit { putLong(name, it) } } ?: remove()

        fun setString(value: String?) =
            value?.let { sharedPreferences.edit { putString(name, it) } } ?: remove()

        fun exists(): Boolean =
            ::sharedPreferences.isInitialized && sharedPreferences.contains(name)

        fun remove() =
            sharedPreferences.edit { remove(name) }
    }
}