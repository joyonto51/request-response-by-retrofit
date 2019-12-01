package com.itsd.retrores

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Base64
import android.util.Log
import com.google.gson.JsonArray
import com.google.gson.JsonObject
import com.itsd.retrores.models.User
import com.itsd.retrores.services.LoginBuilder
import com.itsd.retrores.services.LoginServices
import kotlinx.android.synthetic.main.activity_main.*
import okhttp3.Interceptor
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val button = button

        button.setOnClickListener{
            Login()
        }
    }

    private fun Login(){

        val jsonObject = JsonObject()
        jsonObject.addProperty("user","Asif")
        val citiesArray = JsonArray()
        citiesArray.add("Dhaka")
        citiesArray.add("Örebro")
        jsonObject.add("cities", citiesArray)

        val loginBuilder = LoginBuilder.buildService(LoginServices::class.java)

        val requestCall = loginBuilder.getUserData(jsonObject)

        requestCall.enqueue(object: Callback<User> {

            override fun onResponse(call: Call<User>, response: Response<User>) {
                val user = response.body()!!
                Log.e("Login", "Login Successful")
                Log.e("Login", "${user.name}")
                textView.text = "${user.name}"
            }

            override fun onFailure(call: Call<User>, t: Throwable) {
                Log.e("Login", "Login Failed")
                textView.text = "Failed"
            }
        })
    }
}
