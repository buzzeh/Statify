package com.cristhian.statify


import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.Window
import android.view.WindowManager
import android.widget.Button
import androidx.navigation.Navigation
import com.tolkiana.spotifyplayer.SpotifyService


// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 *
 */
class welcome : Fragment() {

    private lateinit var welcomeView: View


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        welcomeView = inflater.inflate(R.layout.fragment_welcome, container, false)


        (welcomeView.findViewById(R.id.my_rounded_sign_in_button) as Button).setOnClickListener {
            SpotifyService.connect(this.context!!) {
            }
        }

        // Inflate the layout for this fragment
        return welcomeView
    }
}
