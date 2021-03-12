package com.nizar.moviecatalogue.presentation.movies

import androidx.lifecycle.viewModelScope
import com.nizar.moviecatalogue.data.repository.MovieRepo
import com.nizar.moviecatalogue.presentation.base.BaseViewModel
import com.nizar.moviecatalogue.presentation.commons.CoroutineDispatcher
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class MoviesViewModel @Inject constructor(private val movieRepo: MovieRepo,
                                          private val dispatcher: CoroutineDispatcher = CoroutineDispatcher()) : BaseViewModel() {


    fun getMovies(category:String?="") {
        viewModelScope.launch(dispatcher.IO) {
            movieRepo.getMovies(category?:"").collect {
                withContext(dispatcher.Main){
                    if (!it.isNullOrEmpty()){
                        onSuccess(it)
                    }else
                        onError("No movies found")
                }
            }
        }
    }

    fun getFavorites() {
        viewModelScope.launch(dispatcher.IO) {
            movieRepo.getMoviesFavorite().collect {
                withContext(dispatcher.Main){
                    if (!it.isNullOrEmpty()){
                        onSuccess(it)
                    }else
                        onError("No movie found")
                }
            }
        }
    }

}