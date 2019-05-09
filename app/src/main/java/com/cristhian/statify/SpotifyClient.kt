package com.cristhian.statify


import com.cristhian.statify.objects.*
import io.reactivex.Observable
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory
import retrofit2.http.GET
import retrofit2.http.Header
import retrofit2.http.Path
import retrofit2.http.Query
import java.util.*

interface  SpotifyClient {
    @GET("/v1/me")
    fun getProfile(@Header("Authorization") token: String): Observable<User>

    companion object {


        fun create(baseUrl: String): SpotifyClient {
//
            val retrofit =
                Retrofit.Builder().addCallAdapterFactory(RxJava2CallAdapterFactory.create()).addConverterFactory(
                    GsonConverterFactory.create(GsonBuilder().create())
                )
                    .baseUrl(baseUrl)
                    .build()

            return retrofit.create(SpotifyClient::class.java)
        }
    }

    @GET("v1/me/top/artists?time_range=short_term&limit=4")
    fun getTopArtists(@Header("Authorization") token: String): Observable<ArtistContainer>

    @GET("v1/me/top/tracks?time_range=short_term&limit=4")
    fun getTopTracks(@Header("Authorization") token: String): Observable<SongContainer>

    @GET("/v1/me/tracks")
    fun getUserLibrary(@Header("Authorization") token: String, @Query("offset") offset: Int, @Query("limit") limit: Int): Observable<LibraryContainer>

//, @Query("offset") offset: Int, @Query("limit") limit: Int

    @GET("/v1/audio-features/{id}")
    fun getAudioFeatures(@Header("Authorization") token: String, @Path("id") id: String):Observable<MinifiedSong>

}
