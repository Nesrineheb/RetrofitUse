package com.example.serie5exo8



import retrofit2.Call
import retrofit2.http.GET

interface JsonPlaceHolderApi {

    @GET("posts")
    open fun getPosts(): Call<MutableList<Post?>?>?
}