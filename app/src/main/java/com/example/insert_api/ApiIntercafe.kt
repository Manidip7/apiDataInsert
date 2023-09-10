package com.example.insert_api

import com.example.insert_api.model.UserDataModelItem
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface ApiIntercafe {
//    https://jsonplaceholder.typicode.com/posts

    @POST("posts")
    fun getData(@Body userDataModelItem: UserDataModelItem): Call<UserDataModelItem>
}