package com.nizar.moviecatalogue.presentation.detail

import androidx.lifecycle.viewModelScope
import com.nizar.moviecatalogue.data.repository.MovieRepo
import com.nizar.moviecatalogue.domain.MovieEntity
import com.nizar.moviecatalogue.presentation.base.BaseViewModel
import com.nizar.moviecatalogue.presentation.commons.CoroutineDispatcher
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

class DetailViewModel @Inject constructor(
    private val movieRepo: MovieRepo,
    private val dispatcher: CoroutineDispatcher = CoroutineDispatcher()
) : BaseViewModel() {

    fun getLikeState(movieId: Int) {
        viewModelScope.launch(dispatcher.IO) {
            val likeState = movieRepo.isMovieLiked(movieId)
            withContext(dispatcher.Main) { onSuccess(DetailViewState.LikeState(likeState)) }
        }
    }

    fun fetchReviews(movieId: Int) {
        viewModelScope.launch(dispatcher.IO) {
            val trailerList = movieRepo.getReviews(movieId)
            withContext(dispatcher.Main) {
                if (!trailerList.isNullOrEmpty())
                    onSuccess(DetailViewState.ReviewsFetched(trailerList))
                else
                    onError("No review found")
            }
        }
    }

    fun updateLikeStatus(movie: MovieEntity) {
        viewModelScope.launch(dispatcher.IO) {
            var newLikeState = true
            movieRepo.changeLikeState(movie)
                .also { newLikeState = movieRepo.isMovieLiked(movie.id) }
            withContext(dispatcher.Main) {
                onSuccess(DetailViewState.LikeState(newLikeState))
            }
        }
    }


}

