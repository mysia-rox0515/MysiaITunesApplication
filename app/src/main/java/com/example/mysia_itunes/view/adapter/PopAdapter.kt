package com.example.mysia_itunes.view.adapter

import android.content.Intent
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.mysia_itunes.R
import com.example.mysia_itunes.databinding.RowBinding
import com.example.mysia_itunes.model.MusicDetails
import com.squareup.picasso.Picasso

class PopAdapter (var rockList:List<MusicDetails>,
                  private var openMusic:(MusicDetails) -> Unit )
    : RecyclerView.Adapter<PopAdapter.ViewHolder>(){
    class ViewHolder(private val binding: RowBinding):
        RecyclerView.ViewHolder(binding.root) {

        fun onBinding(musicItem: MusicDetails, openData: (MusicDetails) -> Unit) {
            binding.artName.text = musicItem.artistName
            binding.trackName.text = musicItem.trackName
            binding.trackPrice.text = musicItem.trackPrice
            Picasso.get()
                .load(musicItem.artworkUrl60)
                .placeholder(R.drawable.ic_launcher_background)
                .error(R.drawable.ic_launcher_background)
                .into(binding.albumCover)
            binding.root.setOnClickListener {
                val PopMusic = Intent()
                PopMusic.action = Intent.ACTION_VIEW
                PopMusic.data = Uri.parse(musicItem.previewUrl)
                ContextCompat.startActivity(binding.root.context, PopMusic, null)
            }

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return PopAdapter.ViewHolder(
            RowBinding
                .inflate(
                    LayoutInflater.from(parent.context)
                    ,parent
                    ,false)
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.onBinding(rockList[position], openMusic)
    }

    override fun getItemCount(): Int {
        Log.d("Hi", "getItemCount:${rockList.size}")
        return rockList.size
    }
    fun updatedList(response: List<MusicDetails>){
        rockList = response
        Log.d("musicList", "updateList:${response} ")
        notifyDataSetChanged()
    }

}