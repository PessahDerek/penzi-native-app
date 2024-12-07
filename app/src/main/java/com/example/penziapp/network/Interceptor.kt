package com.example.penziapp.network

import com.example.penziapp.AppPreferences
import okhttp3.Interceptor
import okhttp3.Request
import okhttp3.Response

class AuthInterceptor() : Interceptor {
    override fun intercept(chain: Interceptor.Chain): Response {

        val originalRequest: Request = chain.request()
        val token = AppPreferences.token
        // Add the Authorization header with the token
        val newRequest: Request = originalRequest.newBuilder()
            .header("Authorization", "Bearer $token")  // or "Basic <your_token>" if it's Basic auth
            .build()

        return chain.proceed(newRequest)
    }
}
