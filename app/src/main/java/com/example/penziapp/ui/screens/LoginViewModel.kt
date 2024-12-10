package com.example.penziapp.ui.screens

import android.util.Log
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.penziapp.AppPreferences
import com.example.penziapp.data.SharedPref
import com.example.penziapp.network.LoginValuesObj
import com.example.penziapp.network.PenziApi
import kotlinx.coroutines.launch
import retrofit2.HttpException

class LoginViewModel : ViewModel() {
    var errorCode = mutableIntStateOf(0)
    var message = mutableStateOf("")
    var token = mutableStateOf("")
    var username = mutableStateOf("")

    fun handleLogin(loginValues: LoginValuesObj, nav: NavController, sharedPref: SharedPref) {
        Log.d("Tried", loginValues.toString())
        viewModelScope
            .launch {
                try {
                    val response = PenziApi.retrofitService.login(loginValues)
                    username.value = response.username
                    token.value = response.token
                    errorCode.intValue = 200
                    sharedPref.saveToken(response.token)
                    sharedPref.saveUsername(response.username)
                    Log.d("Token", response.token)
                    Log.d("Okay", response.toString())
                    AppPreferences.token = response.token
                    nav.navigate("home")
                } catch (e: Exception) {
                    when (e) {
                        is HttpException -> {
                            errorCode.intValue = e.code().toInt()
                            message.value = when (e.code()) {
                                400 -> "Please check your input and try again"
                                401 -> "Wrong password or username"
                                else -> {
                                    "Something went wrong. Please refresh and try again!"
                                }
                            }
                            Log.d("Http err", message.value)
                        }

                        else -> {
                            Log.d("API err", e.toString())
                        }
                    }
                }
            }
    }

}
