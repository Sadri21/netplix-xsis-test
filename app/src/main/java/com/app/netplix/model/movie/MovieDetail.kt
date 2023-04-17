package com.app.netplix.model.movie

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName
import com.app.netplix.model.genre.GenreData


class MovieDetail {

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