package com.cristhian.statify.views


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ProgressBar
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.navigation.Navigation
import com.cristhian.statify.R
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_visualizer.*


class VisualizerFragment : Fragment() {

    private lateinit var bottomNavigation: BottomNavigationView
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_visualizer, container, false)

        bottomNavigation = view.findViewById(R.id.bottom_navigation)

        var pb_1 = view.findViewById<ProgressBar>(R.id.rythm_bar)
        var pb_2 = view.findViewById<ProgressBar>(R.id.progressBar2)
        var pb_3 = view.findViewById<ProgressBar>(R.id.progressBar3)
        var pb_4 = view.findViewById<ProgressBar>(R.id.progressBar4)

        pb_1.progress = 50
        pb_2.progress = 41
        pb_3.progress = 86
        pb_4.progress = 17


        bottomNavigation.setOnNavigationItemSelectedListener(object :
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                var response = false
                when (item.itemId) {
                    R.id.profile_nav -> {
                        Navigation.findNavController(view).navigate(R.id.action_VisualizerFragment_to_ProfileFragment)
                        response = true
                    }
                    R.id.playlists_nav -> {
                        Navigation.findNavController(view).navigate(R.id.action_VisualizerFragment_to_PlaylistFragment2)
                        response = true
                    }
                    R.id.visualizer_nav -> {
                        response = true
                    }
                }
                return response

            }
        })

//        show_stats.setOnClickListener {
//            Toast.makeText(getContext(), "hi", LENGTH_SHORT).show()
//        }

        return view
    }






}
