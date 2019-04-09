package com.cristhian.statify


import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.create
import retrofit2.http.GET
import retrofit2.http.Header

interface  SpotifyClient {
    @GET("/v1/me")
    fun getProfile(@Header("Authorization") token:String): Call<User>

    companion object {


        fun create(baseUrl: String): SpotifyClient {
//
            val retrofit = Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(
                GsonConverterFactory.create(GsonBuilder().create()))
                .baseUrl(baseUrl)
                .build()

            return retrofit.create(SpotifyClient::class.java)

//            val builder = Retrofit.Builder()
//                .baseUrl(baseUrl)
//                .addConverterFactory(GsonConverterFactory.create())
//
//            val retrofit = builder.build()
//            return retrofit.create(SpotifyClient::class.java)
        }
    }

    @GET("v1/me/top/artists")
    fun getTopArtists(@Header("Authorization") token:String): Call<ArtistContainer>
}
