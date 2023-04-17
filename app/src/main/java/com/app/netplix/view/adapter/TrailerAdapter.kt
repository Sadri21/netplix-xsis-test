package com.app.netplix.view.adapter

import android.content.Context
import android.content.Intent
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.netplix.R
import com.app.netplix.databinding.ItemTrailerBinding
import com.app.netplix.model.trailer.TrailerData
import com.app.netplix.utilities.ClickAdapterInterface
import com.app.netplix.utilities.GlobalConfig.picassoBuilder

class TrailerAdapter(var listTrailer : List<TrailerData>, var context: Context, var clickInterface: ClickAdapterInterface): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return TrailerViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_trailer, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listTrailer.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is TrailerViewHolder -> {
                holder.bind(listTrailer[position])
            }
        }
    }

    inner class TrailerViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemTrailerBinding.bind(itemView)

        fun bind(trailer: TrailerData) {
            val youtubeImgURL = "https://img.youtube.com/vi/${trailer.getKey()}/default.jpg"
            context.picassoBuilder().load(youtubeImgURL).placeholder(R.drawable.movie_placeholder).into(binding.tvVideoImage)
            binding.tvVideoTitle.text = trailer.getName()
            itemView.setOnClickListener {
                clickInterface.onItemClick(trailer.getKey()!!)
//                val intent = Intent(context, YoutubePlayer::class.java)
//                intent.putExtra("video_id", trailer.getKey())
//                context.startActivity(intent)
            }
        }
    }
}