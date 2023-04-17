package com.app.netplix.view

import android.content.Intent
import android.os.Bundle
import android.os.Handler
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.app.netplix.R
import com.app.netplix.databinding.ActivityMainBinding
import com.app.netplix.model.movie.MovieData
import com.app.netplix.utilities.ClickAdapterHandler
import com.app.netplix.view.adapter.GenreAdapter
import com.app.netplix.view.adapter.MovieAdapter
import com.app.netplix.viewModel.RequestViewModel
import com.google.android.material.tabs.TabLayoutMediator

class MainActivity : AppCompatActivity(), ClickAdapterHandler {

    lateinit var binding : ActivityMainBinding
    var isLoadFinish = false
    var listVideo = arrayListOf<MovieData>()
    var model = RequestViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        supportActionBar!!.hide()
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);
        setupView()
        request()
        viewModelResult()
    }

    private fun setupView() {
        binding.titleDashboard.typeface = ResourcesCompat.getFont(this, R.font.aviola_memories_demo)
        runningBanner()
        binding.btnSarch.setOnClickListener {
            val intent = Intent(this, AllVideoActivity::class.java)
            startActivity(intent)
        }
    }

    private fun viewModelResult() {

        model.isLoading.observe(this) {
            when (it.forLoading) {
                "banner" -> {
                    isLoadFinish = !it.isLoading
                }
            }
        }

        model.movies.observe(this) {
            listVideo = it.listMovie as ArrayList<MovieData>
            val adapter = MovieAdapter(listVideo, this, it.type, this)
            binding.pager.adapter = adapter
            TabLayoutMediator(binding.intoTabLayout, binding.pager)
            { _, _ ->}.attach()
        }

        model.latest.observe(this) {
            val adapter = MovieAdapter(it.listMovie, this, it.type, this)
            binding.rvLatest.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
            binding.rvLatest.adapter = adapter
        }




        model.genres.observe(this) {
            val adapter = GenreAdapter(it, this, this, this);
            binding.rvGenre.layoutManager = LinearLayoutManager(this)
            binding.rvGenre.adapter = adapter
        }

    }

    private fun request() {
        model.getListMovie(1, null, "popularity.desc", "banner", "")
        model.getListMovie(1, null, "primary_release_date.desc", "latest", "")
        model.getGenre()
    }

    private fun runningBanner() {
        val handler = Handler()
        val run = object : Runnable {
            override fun run() {
                handler.postDelayed(this, 7000)// 7 seconds
                if (isLoadFinish) {
                    if (binding.pager.currentItem == 4) {
                        binding.pager.currentItem = 0
                    } else {
                        binding.pager.currentItem = binding.pager.currentItem + 1
                    }

                    Log.e("TAG", "run: " + binding.pager.currentItem)
                }
            }
        }
        handler.post(run)
    }

    override fun onInformationClick(id: Int, title: String, detail: String) {
        super.onInformationClick(id, title, detail)
        val dialog = CustomDialogDetail(this, this, id, detail, title)
        dialog.show()
    }




}