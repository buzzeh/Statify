package com.cristhian.statify.views


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.cristhian.statify.R
import com.google.android.material.bottomnavigation.BottomNavigationView


class PlaylistFragment : Fragment() {


    private lateinit var bottomNavigation:BottomNavigationView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_playlist, container, false)

        bottomNavigation= view.findViewById(R.id.bottom_navigation)

        bottomNavigation.setOnNavigationItemSelectedListener(object :
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                var response = false
                when (item.itemId) {
                    R.id.profile_nav -> {
                        Navigation.findNavController(view).navigate(R.id.action_login_to_profile)
                        response = true
                    }
                    R.id.playlists_nav -> {
                        response = true
                    }
                    R.id.visualizer_nav -> {
                        Navigation.findNavController(view).navigate(R.id.action_PlaylistFragment_to_VisualizerFragment2)
                        response = true
                    }
                }
                return response

            }
        })

        return view
    }


}
