package com.app.netplix.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.app.netplix.R
import com.app.netplix.databinding.ItemGenreTokenBinding
import com.app.netplix.model.genre.GenreData

class GenreTokenAdapter(var listGenre: List<GenreData>, var context: Context): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return GenreTokenHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_genre_token, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listGenre.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is GenreTokenHolder -> {
                holder.bind(listGenre[position])
            }
        }
    }

    inner class GenreTokenHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val bindingToken = ItemGenreTokenBinding.bind(itemView)

        fun bind(genre: GenreData) {
            bindingToken.tvGenreToken.text = genre.getName()
        }
    }
}