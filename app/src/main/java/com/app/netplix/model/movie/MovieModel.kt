package com.app.netplix.model.movie

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class MovieModel {
    @SerializedName("page")
    @Expose
    private var page: Int? = null

    @SerializedName("results")
    @Expose
    private var results: List<MovieData>? = null

    @SerializedName("total_pages")
    @Expose
    private var totalPages: Int? = null

    @SerializedName("total_results")
    @Expose
    private var totalResults: Int? = null

    fun getPage(): Int? {
        return page
    }

    fun setPage(page: Int?) {
        this.page = page
    }

    fun getResults(): List<MovieData>? {
        return results
    }

    fun setResults(results: List<MovieData>?) {
        this.results = results
    }

    fun getTotalPages(): Int? {
        return totalPages
    }

    fun setTotalPages(totalPages: Int?) {
        this.totalPages = totalPages
    }

    fun getTotalResults(): Int? {
        return totalResults
    }

    fun setTotalResults(totalResults: Int?) {
        this.totalResults = totalResults
    }
}