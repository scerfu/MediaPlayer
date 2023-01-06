package com.example.musicapi.view.adapter

import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.core.net.toUri
import androidx.recyclerview.widget.RecyclerView
import com.example.musicapi.R
import com.example.musicapi.databinding.SongItemLayoutBinding
import com.example.musicapi.model.Song
import com.squareup.picasso.Picasso

class MusicAdapter(private val dataSet:MutableList<Song>): RecyclerView.Adapter<MusicAdapter.MusicViewHolder>() {
    private lateinit var mediaPlayer: MediaPlayer
    private var pushed: Boolean = false

    class MusicViewHolder(private val binding: SongItemLayoutBinding):
            RecyclerView.ViewHolder(binding.root){
                fun onBind(songItem: Song){
                    binding.apply {
                        collectionName.text = songItem.collectionName
                        songArtist.text = songItem.artistName
                        songPrice.text = songItem.trackPrice.toString()
                        trackName.text = songItem.trackName
                        Picasso.get().load(songItem.artworkUrl60).resize(250,250).into(songCoverView)
                    }
                }
        val songTitle: TextView = binding.root.findViewById(R.id.collection_name)
        val songArtist: TextView = binding.root.findViewById(R.id.song_artist)
        val songPrice: TextView = binding.root.findViewById(R.id.song_price)
        val songTrack: TextView = binding.root.findViewById(R.id.track_name)
            }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int) = MusicViewHolder(
        SongItemLayoutBinding.inflate(
            LayoutInflater.from(parent.context),
            parent,
            false
        )
    )

    override fun onBindViewHolder(holder: MusicViewHolder, position: Int) {
        mediaPlayer = MediaPlayer()
        holder.onBind((dataSet[position]))
        holder.songTitle.text = dataSet[position].collectionName
        holder.songArtist.text = dataSet[position].artistName
        holder.songTrack.text = dataSet[position].trackName
        holder.songPrice.text = dataSet[position].trackPrice.toString()
        holder.itemView.setOnClickListener {
            if(!mediaPlayer.isPlaying && !pushed){
                try {
                    mediaPlayer.setDataSource(dataSet[position].previewUrl)
                    mediaPlayer.prepare()
                    mediaPlayer.start()
                    pushed = true
                } catch (e: Exception){e.printStackTrace()}
            }else{
                mediaPlayer.stop()
                mediaPlayer.release()
                mediaPlayer = MediaPlayer()
                mediaPlayer.setDataSource(dataSet[position].previewUrl)
                mediaPlayer.prepare()
                mediaPlayer.start()
                pushed = false

            }
        }
    }

    override fun getItemCount() = dataSet.size

    fun updateDataSet(items: List<Song>){
        val originalSize = dataSet.size -1
        dataSet.addAll(items)
        notifyItemRangeInserted(originalSize,50)
    }
}

