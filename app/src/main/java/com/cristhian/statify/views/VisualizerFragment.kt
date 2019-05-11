package com.cristhian.statify.views


import android.os.Bundle
import android.view.*
import android.widget.Button
import androidx.fragment.app.Fragment
import android.widget.ProgressBar
import android.widget.TextView
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.navigation.Navigation
import com.cristhian.statify.R
import com.cristhian.statify.SpotifyViewModel
import com.google.android.material.bottomnavigation.BottomNavigationView
import kotlinx.android.synthetic.main.fragment_visualizer.*
import kotlin.random.Random


class VisualizerFragment : Fragment() {

    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var model: SpotifyViewModel
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
        var pb_5 = view.findViewById<ProgressBar>(R.id.progressBar5)
        var pb_6 = view.findViewById<ProgressBar>(R.id.progressBar6)



        var num_text_1 = view.findViewById<TextView>(R.id.textView10)
        var num_text_2 = view.findViewById<TextView>(R.id.textView11)
        var num_text_3 = view.findViewById<TextView>(R.id.textView12)
        var num_text_4 = view.findViewById<TextView>(R.id.textView13)
        var num_text_5 = view.findViewById<TextView>(R.id.textView14)
        var num_text_6 = view.findViewById<TextView>(R.id.textView16)




        var show_stats:Button = view.findViewById(R.id.show_stats)


        pb_1.progress = 50
        pb_2.progress = 41
        pb_3.progress = 86
        pb_4.progress = 17
        pb_5.progress = 64
        pb_6.progress = 82


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

        show_stats.setOnClickListener {
            var a = Random.nextInt(0, 100)
            var b = Random.nextInt(0, 100)
            var c = Random.nextInt(0, 100)
            var d = Random.nextInt(0, 100)
            var e = Random.nextInt(0, 100)
            var f = Random.nextInt(0, 100)


            pb_1.progress = a
            num_text_1.setText(a.toString())
            pb_2.progress = b
            num_text_2.setText(b.toString())
            pb_3.progress = c
            num_text_3.setText(c.toString())
            pb_4.progress = d
            num_text_4.setText(d.toString())
            pb_5.progress = e
            num_text_5.setText(e.toString())
            pb_6.progress = f
            num_text_6.setText(f.toString())
        }

        return view
    }

    override fun onResume() {
        super.onResume()

        val window = activity!!.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = activity!!.resources.getColor(R.color.colorPrimaryDark)
    }





}
