package com.nizar.moviecatalogue.data.repository

import com.nizar.moviecatalogue.data.remote.ReviewRemote
import com.nizar.moviecatalogue.domain.MovieEntity
import kotlinx.coroutines.flow.Flow

interface MovieRepo {
    fun getMoviesFavorite(): Flow<List<MovieEntity>?>
    suspend fun getReviews(movieId: Int): ArrayList<ReviewRemote>?
    fun getMovies(category:String): Flow<List<MovieEntity>?>
    fun isMovieLiked(id: Int): Boolean
    fun changeLikeState(movieEntity: MovieEntity)
}