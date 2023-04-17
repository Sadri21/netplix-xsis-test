package com.app.netplix.model.genre

import com.google.gson.annotations.Expose
import com.google.gson.annotations.SerializedName

class GenreModel {

    @SerializedName("genres")
    @Expose
    private var genres: List<GenreData>? = null

    fun getGenres(): List<GenreData>? {
        return genres
    }

    fun setGenres(genres: List<GenreData>?) {
        this.genres = genres
    }
}