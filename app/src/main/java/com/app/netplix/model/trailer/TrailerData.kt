package com.app.netplix.model.trailer

import com.google.gson.annotations.Expose

import com.google.gson.annotations.SerializedName




class TrailerData {
    @SerializedName("iso_639_1")
    @Expose
    private var iso6391: String? = null

    @SerializedName("iso_3166_1")
    @Expose
    private var iso31661: String? = null

    @SerializedName("name")
    @Expose
    private var name: String? = null

    @SerializedName("key")
    @Expose
    private var key: String? = null

    @SerializedName("published_at")
    @Expose
    private var publishedAt: String? = null

    @SerializedName("site")
    @Expose
    private var site: String? = null

    @SerializedName("size")
    @Expose
    private var size: Int? = null

    @SerializedName("type")
    @Expose
    private var type: String? = null

    @SerializedName("official")
    @Expose
    private var official: Boolean? = null

    @SerializedName("id")
    @Expose
    private var id: String? = null

    fun getIso6391(): String? {
        return iso6391
    }

    fun setIso6391(iso6391: String?) {
        this.iso6391 = iso6391
    }

    fun getIso31661(): String? {
        return iso31661
    }

    fun setIso31661(iso31661: String?) {
        this.iso31661 = iso31661
    }

    fun getName(): String? {
        return name
    }

    fun setName(name: String?) {
        this.name = name
    }

    fun getKey(): String? {
        return key
    }

    fun setKey(key: String?) {
        this.key = key
    }

    fun getPublishedAt(): String? {
        return publishedAt
    }

    fun setPublishedAt(publishedAt: String?) {
        this.publishedAt = publishedAt
    }

    fun getSite(): String? {
        return site
    }

    fun setSite(site: String?) {
        this.site = site
    }

    fun getSize(): Int? {
        return size
    }

    fun setSize(size: Int?) {
        this.size = size
    }

    fun getType(): String? {
        return type
    }

    fun setType(type: String?) {
        this.type = type
    }

    fun getOfficial(): Boolean? {
        return official
    }

    fun setOfficial(official: Boolean?) {
        this.official = official
    }

    fun getId(): String? {
        return id
    }

    fun setId(id: String?) {
        this.id = id
    }
}