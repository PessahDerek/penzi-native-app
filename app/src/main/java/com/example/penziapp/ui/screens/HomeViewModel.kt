package com.example.penziapp.ui.screens

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.penziapp.network.PenziApi
import kotlinx.coroutines.launch
import retrofit2.HttpException

/**
 * contains the corresponding view model for the AdminDashboardApp app
 * Responsible for making the network calls to get the users & message data
 */

class HomeViewModel : ViewModel() {

    fun getAllDashMessages() {
        viewModelScope.launch {
            try {
                var list = PenziApi.retrofitService.fetchMessages()
                Log.d("Fetched: ", list.toString())
            } catch (e: Exception) {
                when(e){
                    is HttpException -> {
                        Log.d("Error fetching (msg)", e.response().toString())
                    }
                    else -> {
                        Log.d("Error fetching Msg", "Unknown ${e.toString()}")
                    }
                }
            }
        }
    }

}
