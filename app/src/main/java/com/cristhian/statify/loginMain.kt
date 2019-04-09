package com.cristhian.statify


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import com.google.gson.GsonBuilder
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory


class loginMain : Fragment() {

    private var token:String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_login_main, container, false)
    }
    override fun onViewCreated(view: View, bundle: Bundle?) {

        token = this.arguments?.getString("token")
        token = "Bearer $token"

        var userCall = SpotifyClient.create("https://api.spotify.com").getProfile(token as String)
        var artistCall = SpotifyClient.create("https://api.spotify.com").getTopArtists(token as String)
        userCall.enqueue(object: Callback<User> {
            override fun onResponse(userCall: Call<User>, response: Response<User>) {
                var user = response.body()
                view.findViewById<TextView>(R.id.userName).text = "Hello " + user?.display_name + ","
                Toast.makeText(activity, response.body()?.birthdate, Toast.LENGTH_LONG).show()

            }

            override fun onFailure(userCall: Call<User>, t: Throwable) {
                Toast.makeText(activity, t.message, Toast.LENGTH_SHORT).show()
            }
        })

        artistCall.enqueue(object: Callback<ArtistContainer> {
            override fun onResponse(call: Call<ArtistContainer>, response: Response<ArtistContainer>) {
                var artists:ArtistContainer? = response.body()
                view.findViewById<TextView>(R.id.topArtists).text = artists.toString()
            }

            override fun onFailure(call: Call<ArtistContainer>, t: Throwable) {

                Toast.makeText(activity, t.message, Toast.LENGTH_SHORT).show()
            }
        })




    }


}
