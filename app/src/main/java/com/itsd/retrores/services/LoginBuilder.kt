package com.itsd.retrores.services

import android.util.Base64
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import okhttp3.Credentials
import okhttp3.Interceptor
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.io.IOException

object LoginBuilder {
    private const val url = "http://192.168.0.167:8500/"

    private val okHttp: OkHttpClient.Builder = OkHttpClient.Builder()
        .addNetworkInterceptor(AddHeaderInterceptor())

    private val builder: Retrofit.Builder = Retrofit.Builder()
        .baseUrl(url)
        .addConverterFactory(GsonConverterFactory.create())
        .client(okHttp.build())

    private val retrofit: Retrofit = builder.build()


    fun <T> buildService(serviceType:Class<T>): T {
        return retrofit.create(serviceType)
    }
}


//val credential:String = "Basic " + Base64.encodeToString("jayanta:django123".toByteArray(), Base64.NO_WRAP)
val credential:String = Credentials.basic("jayanta", "django123")

class AddHeaderInterceptor : Interceptor {

    @Throws(IOException::class)
    override fun intercept(chain: Interceptor.Chain): okhttp3.Response {

        val builder = chain.request().newBuilder()
            .addHeader("Authorization", credential)


        return chain.proceed(builder.build())
    }
}