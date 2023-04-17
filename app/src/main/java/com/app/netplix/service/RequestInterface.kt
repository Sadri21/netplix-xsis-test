package com.app.netplix.service

import com.app.netplix.utilities.GlobalConfig.API_KEY
import com.app.netplix.model.genre.GenreModel
import com.app.netplix.model.movie.MovieModel
import com.app.netplix.model.trailer.TrailerModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface RequestInterface {
    @GET("genre/movie/list")
    fun getGenres(
        @Query("api_key") api_key: String = API_KEY
    ): Call<GenreModel>

    @GET("discover/movie")
    fun getMovieList(
        @Query("api_key") api_key: String = API_KEY,
        @Query("sort_by") sortBy: String,
        @Query("page") page: Int,
        @Query("with_genres") genres: Int?,
        @Query("with_keyword") keyword: String = ""
    ): Call<MovieModel>

    @GET("movie/{movie_id}/videos")
    fun getVideosList(
        @Path("movie_id") movieID: Int,
        @Query("api_key") api_key: String = API_KEY
    ): Call<TrailerModel>

    @GET("search/movie")
    fun searchMovieText(
        @Query("api_key") api_key: String = API_KEY,
        @Query("page") page: Int,
        @Query("query") keyword: String
    ): Call<MovieModel>
}