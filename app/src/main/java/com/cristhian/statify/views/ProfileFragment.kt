package com.cristhian.statify.views


import android.os.Bundle
import android.view.*
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
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


class ProfileFragment : Fragment() {

    private var token: String? = null
    private var user:User? = null
    private lateinit var trackRecycler: RecyclerView
    private lateinit var trackAdapter: RecyclerView.Adapter<*>
    private lateinit var artistRecycler: RecyclerView
    private lateinit var artistAdapter: RecyclerView.Adapter<*>
    private var trackList: List<Song>? = null
    private var artistList: List<Artist>? = null
    private lateinit var bottomNavigation:BottomNavigationView
    private lateinit var model: SpotifyViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the view for this fragment
        var view = inflater.inflate(R.layout.fragment_profile, container, false)

        artistRecycler = view.findViewById(R.id.artistRecycler)
        artistRecycler.layoutManager = LinearLayoutManager(context)
        trackRecycler = view.findViewById(R.id.trackRecycler)
        trackRecycler.layoutManager = LinearLayoutManager(context)

        //set up artist recycler view with information retrieved from the model
        model = activity.run { ViewModelProviders.of(this!!).get(SpotifyViewModel::class.java) }
        model.artists.observe(
            this,
            Observer<List<Artist>> { list ->
                artistAdapter = ArtistAdapter(list, activity as MainActivity)
                artistRecycler.adapter = artistAdapter
            }

        )



        //set up user from information received from the model
        model.user.observe(
            this,
            Observer<User> { user ->
                view.findViewById<TextView>(R.id.userName).text = "Welcome, ${user.display_name}!"
            }

        )
//        model.library.observe(
//            this,
//            Observer<List<MinifiedSong>> { list ->
//                //Toast.makeText(context, list[0].name, Toast.LENGTH_LONG).show()
//            }
//        )


        //set up track recycler view with information from the
        model.tracks.observe(
            this,
            Observer<List<Song>> { list ->
                trackAdapter = TrackAdapter(list, activity as MainActivity)
                trackRecycler.adapter = trackAdapter
            }

        )



//
        bottomNavigation= view.findViewById(R.id.bottom_navigation)
//
        bottomNavigation.setOnNavigationItemSelectedListener(object:
            BottomNavigationView.OnNavigationItemSelectedListener {
            override fun onNavigationItemSelected(item: MenuItem): Boolean {
                var response = false
                when (item.itemId) {
                    R.id.profile_nav -> {

                        response = true
                    }
                    R.id.playlists_nav -> {
                        Navigation.findNavController(view).navigate(R.id.action_ProfileFragment_to_PlaylistFragment)
                        response = true
                    }
                    R.id.visualizer_nav -> {
                        Navigation.findNavController(view).navigate(R.id.action_ProfileFragment_to_VisualizerFragment)
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

    override fun onResume() {
        super.onResume()

        val window = activity!!.window
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = activity!!.resources.getColor(R.color.colorPrimaryDark)
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
                    Navigation.findNavController(view).navigate(R.id.action_ProfileFragment_to_track_stats)


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
                    Navigation.findNavController(view).navigate(R.id.action_ProfileFragment_to_artists_stats)
                }
            }

        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = artists?.size as Int

    }
}

