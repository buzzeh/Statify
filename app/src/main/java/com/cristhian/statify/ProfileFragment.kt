package com.cristhian.statify


import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.RequestOptions
import com.cristhian.statify.objects.*
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class ProfileFragment : Fragment() {

    private var token: String? = null
    private lateinit var trackRecycler: RecyclerView
    private lateinit var trackAdapter: RecyclerView.Adapter<*>
    private lateinit var artistRecycler: RecyclerView
    private lateinit var artistAdapter: RecyclerView.Adapter<*>
    private var trackList: List<Song>? = null
    private var artistList: List<Artist>? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_profile, container, false)
    }

    override fun onViewCreated(view: View, bundle: Bundle?) {

        var base_url: String = "https://api.spotify.com"
        token = this.arguments?.getString("token")
        token = "Bearer $token"

        var userCall = SpotifyClient.create(base_url).getProfile(token as String)
        var artistCall = SpotifyClient.create(base_url).getTopArtists(token as String)
        var trackCall = SpotifyClient.create(base_url).getTopTracks(token as String)
        userCall.enqueue(object : Callback<User> {
            override fun onResponse(userCall: Call<User>, response: Response<User>) {
                var user = response.body()
                view.findViewById<TextView>(R.id.userName).text = user?.display_name

            }

            override fun onFailure(userCall: Call<User>, t: Throwable) {
                Toast.makeText(activity, t.message, Toast.LENGTH_SHORT).show()
            }
        })

        artistCall.enqueue(object : Callback<ArtistContainer> {
            override fun onResponse(call: Call<ArtistContainer>, response: Response<ArtistContainer>) {
                var artists: ArtistContainer? = response.body()
                artistList = artists?.items
                artistRecycler = view.findViewById(R.id.artistRecycler)
                artistRecycler.layoutManager = LinearLayoutManager(context)
                artistAdapter = ArtistAdapter(artistList, activity as MainActivity)

                artistRecycler.adapter = artistAdapter
            }

            override fun onFailure(call: Call<ArtistContainer>, t: Throwable) {

                Toast.makeText(activity, t.message, Toast.LENGTH_SHORT).show()
            }
        })

        trackCall.enqueue(object : Callback<SongContainer> {
            override fun onResponse(call: Call<SongContainer>, response: Response<SongContainer>) {
                var tracks: SongContainer? = response.body()
                trackList = tracks?.items
                trackRecycler = view.findViewById(R.id.trackRecycler)
                trackRecycler.layoutManager = LinearLayoutManager(context)
                trackAdapter = TrackAdapter(trackList, activity as MainActivity)

                trackRecycler.adapter = trackAdapter

            }

            override fun onFailure(call: Call<SongContainer>, t: Throwable) {

                Toast.makeText(activity, t.message, Toast.LENGTH_SHORT).show()
            }
        })


    }


    inner class TrackAdapter(private val tracks: List<Song>?, private val mainActivity: MainActivity) :
        RecyclerView.Adapter<TrackAdapter.ViewHolder>() {
        override fun onCreateViewHolder(
            parent: ViewGroup,
            viewType: Int
        ): TrackAdapter.ViewHolder {

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

                itemView.setOnClickListener {

                    // portrait
                    //if (activity.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)

                    // landscape
                    //else
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
        ): ArtistAdapter.ViewHolder {

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

                    // portrait
                    //if (activity.resources.configuration.orientation == Configuration.ORIENTATION_PORTRAIT)

                    // landscape
                    //else
                }
            }

        }

        // Return the size of your dataset (invoked by the layout manager)
        override fun getItemCount() = artists?.size as Int

    }
}

