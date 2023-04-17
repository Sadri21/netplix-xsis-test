package com.app.netplix.view

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.View
import android.view.View.OnKeyListener
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.widget.SearchView.OnQueryTextListener
import androidx.recyclerview.widget.GridLayoutManager
import com.app.netplix.databinding.ActivityAllVideoBinding
import com.app.netplix.utilities.ClickAdapterHandler
import com.app.netplix.view.adapter.MovieAdapter
import com.app.netplix.viewModel.RequestViewModel

class AllVideoActivity : AppCompatActivity(), ClickAdapterHandler {

    lateinit var viewBinding : ActivityAllVideoBinding
    var model = RequestViewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewBinding = ActivityAllVideoBinding.inflate(layoutInflater)
        setContentView(viewBinding.root)
        supportActionBar!!.hide()
        setupView()
        getViewModel()
    }

    private fun setupView() {
        viewBinding.svMovie.requestFocus()
        viewBinding.rvMovie.layoutManager = GridLayoutManager(this, 2)
        viewBinding.svMovie.setOnQueryTextListener(object: OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                imm.hideSoftInputFromWindow(viewBinding.svMovie.windowToken, 0)
                request(query ?: "")
                return true
            }

            override fun onQueryTextChange(newText: String?): Boolean {
                return false
            }

        })
    }

    private fun getViewModel() {

        model.isLoading.observe(this) {
            if (!it.isLoading) {
                viewBinding.pbLoading.visibility = View.GONE
            }
        }

        model.moviesResult.observe(this) {
            val adapter = MovieAdapter(it, this, "", this)
            viewBinding.rvMovie.adapter = adapter
        }

    }

    private fun request(query: String) {
        viewBinding.pbLoading.visibility = View.VISIBLE
        model.getSearchMovie(query)
    }

    override fun onInformationClick(id: Int, title: String, detail: String) {
        super.onInformationClick(id, title, detail)
        val dialog = CustomDialogDetail(this, this, id, detail, title)
        dialog.show()
    }






}