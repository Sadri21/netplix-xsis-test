package com.app.netplix.model.trailer

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class TrailerModel {
    @SerializedName("id")
    @Expose
    private var id: Int? = null

    @SerializedName("results")
    @Expose
    private var results: List<TrailerData>? = null

    fun getId(): Int? {
        return id
    }

    fun setId(id: Int?) {
        this.id = id
    }

    fun getResults(): List<TrailerData>? {
        return results
    }

    fun setResults(results: List<TrailerData>?) {
        this.results = results
    }
}