package com.itsd.retrores.services

import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object LoginBuilder {
    // Base URL
    private const val baseURL = "http://192.168.0.167:8500/"

    // OkHttp Client
    private val okHttp: OkHttpClient.Builder = OkHttpClient.Builder()
        .addNetworkInterceptor(AddHeaderInterceptor())

    // Retrofit Builder
    private val builder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(baseURL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    // Finally Building the Retrofit Builder
    private val retrofit: Retrofit = builder.build()


    fun <T> buildService(serviceType:Class<T>): T {
        return retrofit.create(serviceType)
    }
}

// Credential
//val credential:String = "Basic " + Base64.encodeToString("jayanta:django123".toByteArray(), Base64.NO_WRAP)

// Credential
val credential:String = Credentials.basic("jayanta", "django123")

class AddHeaderInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {

        // Adding Authorization Header with request builder
        val builder = chain.request().newBuilder()
            .addHeader("Authorization", credential)

        return chain.proceed(builder.build())
    }
}