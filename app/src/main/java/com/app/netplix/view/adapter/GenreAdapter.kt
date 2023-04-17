package com.app.netplix.view.adapter

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.app.netplix.R
import com.app.netplix.databinding.ItemListGenreBinding
import com.app.netplix.model.genre.GenreData
import com.app.netplix.utilities.ClickAdapterInterface
import com.app.netplix.viewModel.RequestViewModel

class GenreAdapter(var listGenre: List<GenreData>, var context: Context, var lifeCyclerOwner: LifecycleOwner, var handler: ClickAdapterInterface): RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return GenreHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_genre, parent, false)
        )
    }

    override fun getItemCount(): Int {
        return listGenre.size
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is GenreHolder -> {
                holder.bind(listGenre[position])
            }
        }
    }

    inner class GenreHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListGenreBinding.bind(itemView)
        val model = RequestViewModel()

        fun bind(genreData: GenreData) {
            binding.tvGenreTitle.text = genreData.getName()
            request(genreData.getId() ?: 0)
            binding.rvMovie.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            model.movies.observe(lifeCyclerOwner) {
                val adapter = MovieAdapter(it.listMovie, context, "movie", handler)
                binding.rvMovie.adapter = adapter
            }
        }

        private fun request(idGenre: Int) {
            model.getListMovie(1, idGenre, "popularity.desc", "genre", "")
        }
    }
}