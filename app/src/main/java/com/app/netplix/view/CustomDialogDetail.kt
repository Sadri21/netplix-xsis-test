package com.app.netplix.view

import android.app.Dialog
import android.content.Context
import android.content.DialogInterface
import android.os.Bundle
import android.support.annotation.NonNull
import android.view.LayoutInflater
import android.view.View
import android.view.Window
import android.view.WindowManager
import androidx.lifecycle.LifecycleOwner
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.netplix.R
import com.app.netplix.databinding.DialogShowMovieBinding
import com.app.netplix.utilities.ClickAdapterHandler
import com.app.netplix.view.adapter.GenreAdapter
import com.app.netplix.view.adapter.GenreTokenAdapter
import com.app.netplix.view.adapter.TrailerAdapter
import com.app.netplix.viewModel.RequestViewModel
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener


class CustomDialogDetail(context: Context, var owner: LifecycleOwner, var movieID: Int, var detail: String, var title: String): Dialog(context, R.style.WideDialog) {

    lateinit var binding : DialogShowMovieBinding
    var dialogYoutubePlayer: YouTubePlayer? = null
    var model = RequestViewModel()

    init {
        setCancelable(true)

    }

    override fun setOnDismissListener(listener: DialogInterface.OnDismissListener?) {
        super.setOnDismissListener(listener)
        binding.youtubePlayerView.release()
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DialogShowMovieBinding.inflate(LayoutInflater.from(context))
        requestWindowFeature(Window.FEATURE_NO_TITLE);
        window!!.setBackgroundDrawableResource(android.R.color.transparent)
        window!!.setLayout(
            WindowManager.LayoutParams.MATCH_PARENT,
            WindowManager.LayoutParams.MATCH_PARENT);
        setContentView(binding.root)
        binding.tvMovieDetail.text = detail
        binding.tvTitle.text = title
        binding.rvGenreToken.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
        binding.btnClose.setOnClickListener {
            dismiss()
        }
        model.isLoading.observe(owner) {
            if (!it.isLoading) {
                binding.pbLoading.visibility = View.GONE
            }
        }
        model.trailers.observe(owner) {
            //get first open
            val listTrailer = it.filter { data -> data.getType() == "Trailer" || data.getType() == "Teaser" }
            if (dialogYoutubePlayer != null && it.isNotEmpty()) {
                dialogYoutubePlayer?.loadVideo(listTrailer[0].getKey()!!, 0f)
            }

            //click play
            val adapter = TrailerAdapter(listTrailer, context, object: ClickAdapterHandler {
                override fun onItemClick(videoID: String) {
                    dialogYoutubePlayer?.loadVideo(videoID, 0f)
                }
            })
            binding.rvTrailer.layoutManager = LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)
            binding.rvTrailer.adapter = adapter

        }
        owner.lifecycle.addObserver(binding.youtubePlayerView)
        binding.youtubePlayerView.addYouTubePlayerListener(object :
            AbstractYouTubePlayerListener() {
            override fun onReady(youTubePlayer: YouTubePlayer) {
//                val videoId = "S0Q4gqBUs7c"
//                youTubePlayer.loadVideo(videoId, 0f)
                dialogYoutubePlayer = youTubePlayer
                model.getTrailer(movieID)
            }
        })
        model.getGenre()
        model.genres.observe(owner) {
            val adapter = GenreTokenAdapter(it, context)
            binding.rvGenreToken.adapter = adapter
        }

    }






}