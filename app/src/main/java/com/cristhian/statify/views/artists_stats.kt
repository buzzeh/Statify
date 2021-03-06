package com.cristhian.statify.views


import android.os.Bundle
import android.view.*
import android.widget.*
import androidx.core.os.bundleOf
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
import com.cristhian.statify.objects.*
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.cristhian.statify.objects.Artist as Artist1


class artists_stats : Fragment() {

    private lateinit var artistRecycler: RecyclerView
    private lateinit var artistAdapter: RecyclerView.Adapter<*>
    private lateinit var model: SpotifyViewModel
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var optionSpinner: Spinner


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the view for this fragment
        var view = inflater.inflate(R.layout.fragment_artists_stats, container, false)

        artistRecycler = view.findViewById(R.id.artistRecycler)
        artistRecycler.layoutManager = LinearLayoutManager(context)


        var values = arrayOf("Last 4 Weeks", "Last 6 Months", "All Time")


        //set up artist recycler view with information retrieved from the model
        model = activity.run { ViewModelProviders.of(this!!).get(SpotifyViewModel::class.java) }
        model.artists.observe(
            this,
            Observer<List<Artist1>> { list ->
                artistAdapter = ArtistAdapter(list, activity as MainActivity)
                artistRecycler.adapter = artistAdapter
            }

        )

        optionSpinner = view.findViewById(R.id.spinner) as Spinner


        optionSpinner.adapter = ArrayAdapter<String>(context, R.layout.spinner_item, values)

        optionSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(context, "Nothing Selected", Toast.LENGTH_SHORT).show()
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {


                when {
                    values[position] == "Last 4 Weeks" -> model.retrieveTopArtists("short_term", 10)
                    values[position] == "Last 6 Months" -> model.retrieveTopArtists("medium_term", 10)
                    values[position] == "All Time" -> model.retrieveTopArtists("long_term", 10)
                }


                Toast.makeText(context, values[position], Toast.LENGTH_SHORT).show()
            }
        }


        bottomNavigation = view.findViewById(R.id.bottom_navigation)
//
        bottomNavigation.setOnNavigationItemSelectedListener { item ->
            var response = false
            when (item.itemId) {
                R.id.profile_nav -> {
                    Navigation.findNavController(view).navigate(R.id.action_artists_stats_to_ProfileFragment)
                    response = true
                }
                R.id.playlists_nav -> {
                    Navigation.findNavController(view).navigate(R.id.action_artists_stats_to_PlaylistFragment)
                    response = true
                }
                R.id.visualizer_nav -> {
                    Navigation.findNavController(view).navigate(R.id.action_artists_stats_to_VisualizerFragment)
                    response = true
                }
            }
            response
        }


        return view
    }

    override fun onViewCreated(view: View, bundle: Bundle?) {

    }

    override fun onResume() {
        super.onResume()

        val window = activity!!.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = activity!!.resources.getColor(R.color.prettyBlueDark)
    }

    inner class ArtistAdapter(private val artists: List<Artist1>?, private val mainActivity: MainActivity) :
        RecyclerView.Adapter<ArtistAdapter.ViewHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ViewHolder {

            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_view, parent, false)
            return ViewHolder(v, mainActivity)
        }


        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            holder.bindItems(artists?.get(position))
            context?.let {
                Glide.with(it).load(artists?.get(position)!!.images[0].url).apply(RequestOptions().override(128, 128))
                    .into(holder.view.findViewById<ImageView>(R.id.thumbnail))
            }

        }

        inner class ViewHolder(val view: View, private val activity: MainActivity) : RecyclerView.ViewHolder(view) {
            fun bindItems(artist: Artist1?) {

                val title: TextView = itemView.findViewById(R.id.title)
                title.text = artist?.name

                itemView.setOnClickListener {
                }
            }

        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = artists?.size as Int

    }
}

