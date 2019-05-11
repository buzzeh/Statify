package com.cristhian.statify.views


import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.core.widget.addTextChangedListener
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cristhian.statify.R
import com.cristhian.statify.SpotifyViewModel
import com.cristhian.statify.objects.Features
import com.cristhian.statify.objects.MinifiedSong
import com.cristhian.statify.objects.Song
import com.google.android.material.bottomnavigation.BottomNavigationView


class PlaylistFragment : Fragment() {

    private lateinit var featureRecyclerView: RecyclerView
    private var list:List<String> = listOf("Acousticness", "Danceability", "Energy", "Instrumentalness", "Liveness", "Valence", "Tempo")
    private var minFeatures:Features = Features(0f,0f,0f, 0f, 0f, 0f, 0f)
    private var maxFeatures:Features = Features(1f, 1f, 1f, 1f, 1f, 1f, 200f)
    private lateinit var bottomNavigation:BottomNavigationView
    private lateinit var model: SpotifyViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        var view = inflater.inflate(R.layout.fragment_playlist, container, false)

        model = activity.run { ViewModelProviders.of(this!!).get(SpotifyViewModel::class.java) }
        var generateButton = view.findViewById<Button>(R.id.generate)

        generateButton.setOnClickListener{
            Toast.makeText(context, minFeatures.toString(), Toast.LENGTH_LONG).show()
            //model.generatePlaylist(minFeatures, maxFeatures)
            //Toast.makeText(context, model.playlist.value?.size.toString(), Toast.LENGTH_LONG).show()

        }
//        model.playlist.observe(
//            this,
//            Observer<List<MinifiedSong>> { list ->
//                if (list.isNotEmpty()) {
//                    //Toast.makeText(context, list[0].acousticness.toString(), Toast.LENGTH_LONG).show()
//                }
//
//            }
//        )

        bottomNavigation= view.findViewById(R.id.bottom_navigation)

        bottomNavigation.setOnNavigationItemSelectedListener { item ->
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
            response
        }

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

        inner class ViewHolder( view: View, private val activity: MainActivity) : RecyclerView.ViewHolder(view) {
            fun bindItems(feature: String?) {
                val name: TextView = itemView.findViewById(R.id.featureName)
                name.text = feature

                val min = itemView.findViewById<EditText>(R.id.low)
                val max = itemView.findViewById<EditText>(R.id.high)

                min.addTextChangedListener {
                    if (min.text.toString() != "") {
                        val minVal = min.text.toString().toFloatOrNull()
                        when (feature) {
                            list[0] -> minFeatures.acousticness = minVal
                            list[1] -> minFeatures.danceability = minVal
                            list[2] -> minFeatures.energy = minVal
                            list[3] -> minFeatures.instrumentalness = minVal
                            list[4] -> minFeatures.liveness = minVal
                            list[5] -> minFeatures.valence = minVal
                            list[6] -> minFeatures.tempo = minVal
                        }

                    }  }
                max.addTextChangedListener {
                    if (max.text.toString() != "") {
                        val maxVal = min.text.toString().toFloatOrNull()
                        when (feature) {
                            list[0] -> maxFeatures.acousticness = maxVal
                            list[1] -> maxFeatures.danceability = maxVal
                            list[2] -> maxFeatures.energy = maxVal
                            list[3] -> maxFeatures.instrumentalness = maxVal
                            list[4] -> maxFeatures.liveness = maxVal
                            list[5] -> maxFeatures.valence = maxVal
                            list[6] -> maxFeatures.tempo = maxVal
                        }

                    }
                }






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

    override fun onResume() {
        super.onResume()

        val window = activity!!.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = activity!!.resources.getColor(R.color.colorPrimaryDark)
    }


}
