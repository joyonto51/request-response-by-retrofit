package com.itsd.retrores.services

import com.google.gson.JsonObject
import com.itsd.retrores.models.User
import org.json.JSONObject
import retrofit2.Call
import retrofit2.http.*

interface LoginServices {

    // Interface Method
    @POST("test-app/data/")
    fun getUserData(@Body jsonObject: JsonObject): Call<User>

}