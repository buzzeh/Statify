package com.cristhian.statify

import android.app.Application
import android.content.ContentValues.TAG
import android.util.Log
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.cristhian.statify.database.SongRepository
import com.cristhian.statify.database.SongRoomDatabase
import com.cristhian.statify.objects.*
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers
import kotlinx.coroutines.*
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
    var library: LiveData<List<MinifiedSong>>

    //private var db = SongRoomDatabase.getDatabase(application)
    private var repository = SongRepository(SongRoomDatabase.getDatabase(application).songDao())

    init {
        user = MutableLiveData()
        artists = MutableLiveData()
        tracks = MutableLiveData()
        library = repository.library
    }

    private var parentJob = Job()
    private val coroutineContext: CoroutineContext
        get() = parentJob+ Dispatchers.Main


    private val scope = CoroutineScope(coroutineContext)


    private var disposable1: Disposable? = null

    private var disposable2: Disposable? = null

    private var disposable3: Disposable? = null

    private var disposable4: Disposable? = null

    override fun onCleared() {
        super.onCleared()
        scope.cancel()
    }


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

    fun getLibrary() {
        deleteLibrary()
        if (token != null) {
            disposable4 = SpotifyClient.create(base_url).getUserLibrary(token!!, 20,20).subscribeOn(
                Schedulers.io()).observeOn(
                AndroidSchedulers.mainThread()).subscribe(
                {result -> fillLibrary(result.items)},
                {error -> Log.d(TAG, error.toString())})

        }
    }

    private fun deleteLibrary() = scope.launch (Dispatchers.IO){
        //repository.deleteAll()
    }

    private fun fillLibrary(items: List<LibraryItem>) {
        items.forEach { item ->
            insert(item.track) //inserts minified song
        }

    }

    private fun insert(song: MinifiedSong) = scope.launch(Dispatchers.IO) {
        repository.insert(song)
    }

}