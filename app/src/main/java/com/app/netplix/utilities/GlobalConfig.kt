package com.app.netplix.utilities

import android.content.Context
import android.widget.Toast
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso

object GlobalConfig {
    const val BASE_URL = "https://api.themoviedb.org/3/"
    const val API_KEY = "af41aa87a51674cf03ac2f280b540e7d"
    const val IMAGE_URL = "https://image.tmdb.org/t/p/"

    fun Context.picassoBuilder() : Picasso {
        return Picasso.Builder(this).loggingEnabled(true).build()
    }

    fun RecyclerView.scrollListener(isLinear: Boolean, callback: () -> Unit) {
        addOnScrollListener(object : RecyclerView.OnScrollListener() {
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = if (isLinear) {
                    recyclerView.layoutManager as LinearLayoutManager
                } else {
                    recyclerView.layoutManager as GridLayoutManager
                }
                val lastVisibleItemPosition = layoutManager.findLastVisibleItemPosition()
                val totalItemCount = layoutManager.itemCount
                if (lastVisibleItemPosition == totalItemCount - 1) {
                    callback()
                }
            }
        })
    }

    fun Context.showToast(message: String) {
        Toast.makeText(this, message, Toast.LENGTH_SHORT).show()
    }
}