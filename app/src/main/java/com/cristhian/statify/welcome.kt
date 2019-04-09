package com.cristhian.statify


import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.navigation.Navigation
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse
import com.spotify.sdk.android.authentication.LoginActivity.REQUEST_CODE



class welcome : Fragment() {

    private lateinit var welcomeView: View
    private val CLIENT_ID = "6b7c5a515e144ea2824276dedecdae17"
    private val REDIRECT_URI = "com.cristhian.statify://callback"
    var loggedIn = false
    var token:String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        welcomeView = inflater.inflate(R.layout.fragment_welcome, container, false)


        (welcomeView.findViewById(R.id.my_rounded_sign_in_button) as Button).setOnClickListener {
            if (token == null) {
                val request = AuthenticationRequest.Builder(CLIENT_ID, AuthenticationResponse.Type.TOKEN, REDIRECT_URI)
                    .setScopes(arrayOf("user-read-email", "user-read-birthdate","user-read-private", "playlist-read", "playlist-read-private", "user-top-read", "streaming"))
                    .build()
                val intent = AuthenticationClient.createLoginActivityIntent(activity!!, request)
                startActivityForResult(intent, REQUEST_CODE)
            }




        }

        // Inflate the layout for this fragment
        return welcomeView
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent?) {
        super.onActivityResult(requestCode, resultCode, intent)

        // Check if result comes from the correct activity
        if (requestCode == REQUEST_CODE) {
            val response = AuthenticationClient.getResponse(resultCode, intent)
            when (response.type) {
                // Response was successful and contains auth token
                AuthenticationResponse.Type.TOKEN -> {
                    // Handle successful response
                    token = response.accessToken
                    loggedIn = true
                    Navigation.findNavController(view as View).navigate(R.id.action_welcome_to_loginMain, bundleOf("token" to token))
                }

                // Auth flow returned an error
                AuthenticationResponse.Type.ERROR -> {
                }
            }// Handle error response
            // Most likely auth flow was cancelled
            // Handle other cases
        }
    }
}
