package com.example.penziapp.ui.screens

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.penziapp.network.MessageObj
import com.example.penziapp.network.MsgType
import com.example.penziapp.network.PenziApi
import com.example.penziapp.network.PersonObj
import kotlinx.coroutines.launch
import retrofit2.HttpException

/**
 * contains the corresponding view model for the AdminDashboardApp app
 * Responsible for making the network calls to get the users & message data
 */

class HomeViewModel : ViewModel() {
    //    val allMessages = mutableListOf<MessageObj>()
    val allMessages = mutableStateOf<Collection<MessageObj>>(listOf())
    val allUsers = mutableStateOf<Collection<PersonObj>>(listOf())

    fun getAllPeople(){
        viewModelScope.launch {
            try {
                val list = PenziApi.retrofitService.fetchUsers()
                allUsers.value = list
                Log.d("Fetched Users", list.toString())
            }catch (e: Exception){
                when (e) {
                    is HttpException -> {
                        Log.d("Error fetching (People)", e.response().toString())
                    }

                    else -> {
                        Log.d("Error fetching People", "Unknown ${e.toString()}")
                    }
                }
            }
        }
    }

    fun getAllDashMessages() {
        viewModelScope.launch {
            try {
                val list = PenziApi.retrofitService.fetchMessages()
                allMessages.value = list
                Log.d("Fetched: ", list.toString())
            } catch (e: Exception) {
                when (e) {
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
