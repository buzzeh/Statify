package com.cristhian.statify.views


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.MenuItem
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cristhian.statify.R
import com.cristhian.statify.objects.Song
import com.google.android.material.bottomnavigation.BottomNavigationView


class PlaylistFragment : Fragment() {

    private lateinit var featureRecyclerView: RecyclerView
    private var list:List<String> = listOf("Danceability", "Acousticness", "Energy", "Liveness")

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
                        Navigation.findNavController(view).navigate(R.id.action_PlaylistFragment_to_ProfileFragment)
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

        featureRecyclerView = view.findViewById(R.id.featureRecycler)
        featureRecyclerView.layoutManager = LinearLayoutManager(context)
        featureRecyclerView.adapter = FeatureAdapter(list, activity as MainActivity)


        return view
    }





    inner class FeatureAdapter(private val features: List<String>?, private val mainActivity: MainActivity) :
        RecyclerView.Adapter<FeatureAdapter.ViewHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ViewHolder {

            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.generator_recycler_item, parent, false)


            return ViewHolder(v, mainActivity)
        }


        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            holder.bindItems(features?.get(position))


        }

        inner class ViewHolder(val view: View, private val activity: MainActivity) : RecyclerView.ViewHolder(view) {
            fun bindItems(feature: String?) {
                val name: TextView = itemView.findViewById(R.id.featureName)
                name.text = feature




                itemView.setOnClickListener {

                    // portrait
                    //if (activity.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)

                    // landscape
                    //else
                }
            }
        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = features?.size as Int


    }


}
