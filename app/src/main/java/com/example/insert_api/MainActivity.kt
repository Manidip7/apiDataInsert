package com.example.insert_api

import android.content.ContentValues.TAG
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import com.example.insert_api.model.UserDataModelItem
import retrofit2.*
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    lateinit var name : EditText
    lateinit var username : EditText
    lateinit var password : EditText
    lateinit var uid : EditText
    lateinit var btn : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        name = findViewById(R.id.name)
        username = findViewById(R.id.username)
        password = findViewById(R.id.password)
        uid = findViewById(R.id.uid)
        btn = findViewById(R.id.btn)

        val retrofitBuild = Retrofit.Builder()
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl("https://jsonplaceholder.typicode.com/")
            .build()
        val apiintercafe = retrofitBuild.create(ApiIntercafe::class.java)

        btn.setOnClickListener {
            val name = name.text.toString()
            val email = username.text.toString()
            val pass = password.text.toString()
            val uid = uid.text.toString()

            val userData = UserDataModelItem(name,email.toInt(),pass,uid.toInt())

           val call =  apiintercafe.getData(userData)

            call.enqueue(object : Callback<UserDataModelItem?> {
                override fun onResponse(
                    call: Call<UserDataModelItem?>,
                    response: Response<UserDataModelItem?>)
                {
                    Log.d(TAG, "onResponse: "+response.code())
                    btn.text = response.code().toString()
                }

                override fun onFailure(call: Call<UserDataModelItem?>, t: Throwable) {
                    Log.d(TAG, "onFailure: "+t.message.toString())
                }
            })

        }
    }
}