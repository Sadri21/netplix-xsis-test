package com.app.netplix.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.netplix.R
import com.app.netplix.databinding.ItemBannerBinding
import com.app.netplix.databinding.ItemLatestMovieBinding
import com.app.netplix.databinding.ItemMovieBinding
import com.app.netplix.utilities.GlobalConfig
import com.app.netplix.utilities.GlobalConfig.picassoBuilder
import com.app.netplix.model.movie.MovieData
import com.app.netplix.utilities.ClickAdapterInterface

class MovieAdapter(var listMovie: List<MovieData>, var context: Context, var type:String, var handler: ClickAdapterInterface): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        when (type) {
            "banner" -> {
                return BannerHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.item_banner, parent, false)
                )
            }
            "latest" -> {
                return LatestMovieHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.item_latest_movie, parent, false)
                )
            }
            else -> {
                return MovieHolder(
                    LayoutInflater.from(parent.context).inflate(R.layout.item_movie, parent, false)
                )
            }
        }
    }

    override fun getItemCount(): Int {
        return if (type == "banner") {
            if (listMovie.isNotEmpty()) {
                5
            } else {
                0
            }
        } else {
            listMovie.size
        }
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is BannerHolder -> {
                holder.bind(listMovie[position])
            }
            is LatestMovieHolder -> {
                holder.bind(listMovie[position])
            }
            is MovieHolder -> {
                holder.bind(listMovie[position])
            }
        }

    }

    inner class BannerHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val viewBinding = ItemBannerBinding.bind(itemView)

        fun bind(movieData: MovieData) {
            val imgURL = "${GlobalConfig.IMAGE_URL}w1280/${movieData.getBackdropPath()}"
            context.picassoBuilder().load(imgURL).placeholder(R.drawable.movie_placeholder).into(viewBinding.imgBanner)
            viewBinding.tvMovieTitle.text = movieData.getTitle()
            viewBinding.tvMovieDetail.text = movieData.getOverview()
            itemView.setOnClickListener {
                handler.onInformationClick(movieData.getId()!!, movieData.getTitle()!!, movieData.getOverview()!!)
            }
        }
    }

    inner class LatestMovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val viewBinding = ItemLatestMovieBinding.bind(itemView)

        fun bind(movieData: MovieData) {
            val imgURL = "${GlobalConfig.IMAGE_URL}w500/${movieData.getPosterPath()}"
            context.picassoBuilder().load(imgURL).placeholder(R.drawable.movie_placeholder).into(viewBinding.imgMovie)
            viewBinding.tvMovieTitle.text = movieData.getTitle()
            itemView.setOnClickListener {
                handler.onInformationClick(movieData.getId()!!, movieData.getTitle()!!, movieData.getOverview()!!)
            }
        }
    }

    inner class MovieHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val viewBinding = ItemMovieBinding.bind(itemView)

        fun bind(movieData: MovieData) {
            val imgURL = "${GlobalConfig.IMAGE_URL}w342/${movieData.getPosterPath()}"
            context.picassoBuilder().load(imgURL).placeholder(R.drawable.movie_placeholder).into(viewBinding.imgMovie)
            viewBinding.tvMovieTitle.text = movieData.getTitle()
            itemView.setOnClickListener {
                handler.onInformationClick(movieData.getId()!!, movieData.getTitle()!!, movieData.getOverview()!!)
            }
        }
    }

}