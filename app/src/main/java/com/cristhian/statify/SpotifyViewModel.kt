package com.cristhian.statify

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.cristhian.statify.objects.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import kotlin.coroutines.CoroutineContext


class SpotifyViewModel(application: Application): AndroidViewModel(application) {

    private val CLIENT_ID = "6b7c5a515e144ea2824276dedecdae17"
    private val REDIRECT_URI = "com.cristhian.statify://callback"
    var loggedIn = false
    var token: String? = null

    var base_url: String = "https://api.spotify.com"
    var user: MutableLiveData<User>
    var artists:MutableLiveData<List<Artist>>
    var tracks:MutableLiveData<List<Song>>

    init {
        user = MutableLiveData()
        artists = MutableLiveData()
        tracks = MutableLiveData()
    }

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob+ Dispatchers.Main


    private val scope = CoroutineScope(coroutineContext)


    private var disposable1: Disposable? = null

    private var disposable2: Disposable? = null

    private var disposable3: Disposable? = null



    fun retrieveUser(): User? {

        if (token != null) {
            scope.launch { Dispatchers.IO }
            disposable1 = SpotifyClient.create(base_url).getProfile(token!!).subscribeOn(
                Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()).subscribe(
                {result -> user.value = result},
                {error -> Log.d(TAG, error.toString())})

        }
        return user.value
    }

    fun retrieveTopArtists(){
        if (token != null) {
            scope.launch { Dispatchers.IO }
            disposable2 = SpotifyClient.create(base_url).getTopArtists(token!!).subscribeOn(
                Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()).subscribe(
                {result -> artists.value = result.items},
                {error -> Log.d(TAG, error.toString())})

        }
    }

    fun retrieveTopTracks(){
        if (token != null) {
            scope.launch { Dispatchers.IO }
            disposable3 = SpotifyClient.create(base_url).getTopTracks(token!!).subscribeOn(
                Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()).subscribe(
                {result -> tracks.value = result.items},
                {error -> Log.d(TAG, error.toString())})

        }

    }







}