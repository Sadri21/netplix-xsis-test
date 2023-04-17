package com.app.netplix.viewModel

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.app.netplix.model.genre.GenreData
import com.app.netplix.model.genre.GenreModel
import com.app.netplix.model.movie.MovieData
import com.app.netplix.service.RetrofitConfig
import com.app.netplix.model.movie.MovieModel
import com.app.netplix.model.trailer.TrailerData
import com.app.netplix.model.trailer.TrailerModel
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class RequestViewModel: ViewModel() {
    val isLoading = MutableLiveData<LoadingModel>()
    val errorMessage = MutableLiveData<String>()
    val movies = MutableLiveData<MovieResult>()
    val latest = MutableLiveData<MovieResult>()
    val genres = MutableLiveData<List<GenreData>>()
    val trailers = MutableLiveData<List<TrailerData>>()
    val moviesResult = MutableLiveData<List<MovieData>>()

    fun getListMovie(page: Int,  genres: Int?, sortBy: String, type: String, keyWord: String) {
        isLoading.postValue(LoadingModel(type, true))
        RetrofitConfig().getService().getMovieList(page = page, genres = genres, sortBy = sortBy, keyword = keyWord).enqueue(object:
            Callback<MovieModel> {
            override fun onResponse(
                call: Call<MovieModel>,
                response: Response<MovieModel>
            ) {
                if (response.isSuccessful) {
                    try {
                        val listMovie = response.body()!!.getResults()!!
                        val movieResult = MovieResult(type, listMovie)
                        if (type == "latest") {
                            latest.postValue(movieResult)
                        } else {
                            movies.postValue(movieResult)
                        }
                    } catch (e : Exception) {
                        errorMessage.postValue(e.localizedMessage)
                    }
                } else {
                    errorMessage.postValue("Error get data, Error code : " + response.code())
                }
                isLoading.postValue(LoadingModel(type, false))
            }

            override fun onFailure(call: Call<MovieModel>, t: Throwable) {
                errorMessage.postValue(t.localizedMessage)
                isLoading.postValue(LoadingModel(type, false))
            }

        })
    }

    fun getGenre() {
        isLoading.postValue(LoadingModel("genre", true))
        RetrofitConfig().getService().getGenres().enqueue(object:
            Callback<GenreModel> {
            override fun onResponse(
                call: Call<GenreModel>,
                response: Response<GenreModel>
            ) {
                if (response.isSuccessful) {
                    try {
                        val listGenre = response.body()!!.getGenres()!!
                        genres.postValue(listGenre)
                    } catch (e : Exception) {
                        errorMessage.postValue(e.localizedMessage)
                    }
                } else {
                    errorMessage.postValue("Error get data, Error code : " + response.code())
                }
                isLoading.postValue(LoadingModel("genre", false))
            }

            override fun onFailure(call: Call<GenreModel>, t: Throwable) {
                errorMessage.postValue(t.localizedMessage)
                isLoading.postValue(LoadingModel("genre", false))
            }

        })
    }

    fun getTrailer(movieID: Int) {
        isLoading.postValue(LoadingModel("trailer", true))
        RetrofitConfig().getService().getVideosList(movieID = movieID).enqueue(object:
            Callback<TrailerModel> {
            override fun onResponse(
                call: Call<TrailerModel>,
                response: Response<TrailerModel>
            ) {
                if (response.isSuccessful) {
                    try {
                        val listTrailer = response.body()!!.getResults()!!
                        trailers.postValue(listTrailer)
                    } catch (e : Exception) {
                        errorMessage.postValue(e.localizedMessage)
                    }
                } else {
                    errorMessage.postValue("Error get data, Error code : " + response.code())
                }
                isLoading.postValue(LoadingModel("trailer", false))
            }

            override fun onFailure(call: Call<TrailerModel>, t: Throwable) {
                errorMessage.postValue(t.localizedMessage)
                isLoading.postValue(LoadingModel("trailer", false))
            }

        })
    }

    fun getSearchMovie(keyWord: String) {
        isLoading.postValue(LoadingModel("search", true))
        RetrofitConfig().getService().searchMovieText(page = 1, keyword = keyWord).enqueue(object:
            Callback<MovieModel> {
            override fun onResponse(
                call: Call<MovieModel>,
                response: Response<MovieModel>
            ) {
                if (response.isSuccessful) {
                    try {
                        val listMovie = response.body()!!.getResults()!!
                        moviesResult.postValue(listMovie)
                    } catch (e : Exception) {
                        errorMessage.postValue(e.localizedMessage)
                    }
                } else {
                    errorMessage.postValue("Error get data, Error code : " + response.code())
                }
                isLoading.postValue(LoadingModel("search", false))
            }

            override fun onFailure(call: Call<MovieModel>, t: Throwable) {
                errorMessage.postValue(t.localizedMessage)
                isLoading.postValue(LoadingModel("search", false))
            }

        })
    }
}