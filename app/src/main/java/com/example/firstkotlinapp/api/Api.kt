package com.example.kotlintraining.api

import com.example.firstkotlinapp.model.Rovers
import com.example.kotlintraining.model.Apod
import retrofit2.Call
import retrofit2.Response
import retrofit2.http.GET

interface Api {
    @GET("planetary/apod?api_key=uej4DeQlgTn9GRLfb98qSj38c2mecIuWspj3JyTN")
    suspend fun getAstronomyImageOfTheDay2() : Response<Apod>;

<<<<<<< HEAD
    @GET("/random?json=true")
    suspend fun getRandomGif() : Response<Gif>

    @GET("/latest/{page}?json=true")
    suspend fun getLatestGifs(
        @Path(value = "page", encoded = true)page: Int,
        @Query(value = "pageSize", encoded = true)pageSize:Int,
        @Query(value = "types", encoded = true)types: String?
    ) : Response<Gifs>


    @GET("/top/{page}?json=true")
    suspend fun getTopGifs(
        @Path(value = "page", encoded = true)page: Int,
        @Query(value = "pageSize", encoded = true)pageSize:Int,
        @Query(value = "types", encoded = true)types: String?
    ) : Response<Gifs>
=======

    @GET("planetary/apod?api_key=uej4DeQlgTn9GRLfb98qSj38c2mecIuWspj3JyTN")
    fun getAstronomyImageOfTheDay(): Call<Apod?>?

    @GET("mars-photos/api/v1/rovers/curiosity/photos?sol=1000&page=2&api_key=uej4DeQlgTn9GRLfb98qSj38c2mecIuWspj3JyTN")
    fun getMarsPhotos(): Call<Rovers?>?
>>>>>>> fb4599f... first commit
}