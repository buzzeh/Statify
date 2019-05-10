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
import kotlinx.android.synthetic.main.fragment_artists_stats.*


class track_stats : Fragment() {


    private lateinit var trackRecycler: RecyclerView
    private lateinit var trackAdapter: RecyclerView.Adapter<*>
    private lateinit var artistAdapter: RecyclerView.Adapter<*>
    private lateinit var model: SpotifyViewModel
    private lateinit var bottomNavigation: BottomNavigationView
    private lateinit var optionSpinner: Spinner


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the view for this fragment
        var view = inflater.inflate(R.layout.fragment_track_stats, container, false)
        trackRecycler = view.findViewById(R.id.trackRecycler)
        trackRecycler.layoutManager = LinearLayoutManager(context)

        var values = arrayOf("Last 4 Weeks", "Last 6 Months", "All Time")
        optionSpinner = view.findViewById(R.id.spinner2) as Spinner


        optionSpinner.adapter = ArrayAdapter<String>(context, R.layout.spinner_item, values)

        optionSpinner.onItemSelectedListener = object : AdapterView.OnItemSelectedListener {
            override fun onNothingSelected(parent: AdapterView<*>?) {
                Toast.makeText(context, "Nothing Selected", Toast.LENGTH_SHORT).show()
            }

            override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {


                if (values.get(position).equals("Last 4 Weeks")) {

                } else if (values.get(position).equals("Last 6 Months")) {

                } else if (values.get(position).equals("All Time")) {

                }


                Toast.makeText(context, values.get(position), Toast.LENGTH_SHORT).show()
            }
        }

        //set up artist recycler view with information retrieved from the model
        model = activity.run { ViewModelProviders.of(this!!).get(SpotifyViewModel::class.java) }
        model.artists.observe(
            this,
            Observer<List<Artist>> { list ->
                artistAdapter = ArtistAdapter(list, activity as MainActivity)
            }

        )


        //set up track recycler view with information from the
        model.tracks.observe(
            this,
            Observer<List<Song>> { list ->
                trackAdapter = TrackAdapter(list, activity as MainActivity)
                trackRecycler.adapter = trackAdapter
            }

        )


        bottomNavigation = view.findViewById(R.id.bottom_navigation)
//
        bottomNavigation.setOnNavigationItemSelectedListener(object :
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                var response = false
                when (item.itemId) {
                    R.id.profile_nav -> {
                        Navigation.findNavController(view).navigate(R.id.action_track_stats_to_ProfileFragment)
                        response = true
                    }
                    R.id.playlists_nav -> {
                        Navigation.findNavController(view).navigate(R.id.action_track_stats_to_PlaylistFragment)
                        response = true
                    }
                    R.id.visualizer_nav -> {
                        Navigation.findNavController(view).navigate(R.id.action_track_stats_to_VisualizerFragment)
                        response = true
                    }
                }
                return response

            }
        })

        return view
    }

    override fun onViewCreated(view: View, bundle: Bundle?) {

    }


    inner class TrackAdapter(private val tracks: List<Song>?, private val mainActivity: MainActivity) :
        RecyclerView.Adapter<TrackAdapter.ViewHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): ViewHolder {

            val v = LayoutInflater.from(parent.context)
                .inflate(R.layout.card_view, parent, false)


            return ViewHolder(v, mainActivity)
        }


        override fun onBindViewHolder(holder: ViewHolder, position: Int) {

            holder.bindItems(tracks?.get(position))
            context?.let {
                Glide.with(it).load(tracks?.get(position)!!.album.images[0].url)
                    .apply(RequestOptions().override(128, 128))
                    .into(holder.view.findViewById<ImageView>(R.id.thumbnail))
            }

        }

        inner class ViewHolder(val view: View, private val activity: MainActivity) : RecyclerView.ViewHolder(view) {
            fun bindItems(song: Song?) {
                val title: TextView = itemView.findViewById(R.id.title)
                title.text = song?.name

                val title2: TextView = itemView.findViewById(R.id.rating)
                title2.text = song?.artists!![0].name


                itemView.setOnClickListener {
                    //Toast.makeText(context, "Its " + song.name, Toast.LENGTH_SHORT).show()
                }
            }
        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = tracks?.size as Int


    }


    inner class ArtistAdapter(private val artists: List<Artist>?, private val mainActivity: MainActivity) :
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
            fun bindItems(artist: Artist?) {

                val title: TextView = itemView.findViewById(R.id.title)
                title.text = artist?.name

                itemView.setOnClickListener {
                }
            }

        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = artists?.size as Int

    }

    override fun onResume() {
        super.onResume()

        val window = activity!!.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = activity!!.resources.getColor(R.color.prettyBlueDark)
    }
}

