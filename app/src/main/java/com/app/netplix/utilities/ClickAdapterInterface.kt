package com.app.netplix.utilities

interface ClickAdapterInterface {
    fun onItemClick(videoID: String)
    fun onInformationClick(id: Int, title: String, detail: String)
}

interface ClickAdapterHandler: ClickAdapterInterface {
    override fun onItemClick(videoID: String) { }
    override fun onInformationClick(id: Int, title: String, detail: String) { }
}