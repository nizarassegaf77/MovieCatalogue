package com.nizar.moviecatalogue.data.repository

import com.nizar.moviecatalogue.domain.MovieEntity
import com.nizar.moviecatalogue.data.local.MovieLocal
import com.nizar.moviecatalogue.data.remote.MovieRemote
import com.nizar.moviecatalogue.data.local.dataSource.MovieDao
import com.nizar.moviecatalogue.data.remote.MovieResponse
import com.nizar.moviecatalogue.data.remote.ReviewRemote
import com.nizar.moviecatalogue.data.remote.dataSource.MovieApi
import com.nizar.moviecatalogue.data.remote.dataSource.ReviewApi
import com.nizar.moviecatalogue.domain.MovieMapper
import com.nizar.moviecatalogue.presentation.commons.AppConstants
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import javax.inject.Inject
import javax.inject.Singleton


@Singleton
class MovieRepoImp @Inject constructor(
    private val movieDao: MovieDao,
    private val movieApi: MovieApi,
    private val reviewApi: ReviewApi,
    private val movieMapper: MovieMapper
) : BaseRepoImp(), MovieRepo {

    override fun getMoviesFavorite(): Flow<List<MovieEntity>?> = flow {
        val localData = fetchAllMoviesFavorite().map { movieMapper.mapFromLocal(it) }.toList()
        emit(localData)
    }

    override suspend fun getReviews(movieId: Int): ArrayList<ReviewRemote>? {
        val data = safeApiCall({ reviewApi.getReviews(movieId, AppConstants.API_KEY) }, "Error fetching Trailers")
        return if (data != null) data.results as ArrayList<ReviewRemote> else null
    }

    override fun getMovies(category: String): Flow<List<MovieEntity>?> = flow {
        val remoteData = fetchMoviesRemote(category)?.map { movieMapper.mapFromRemote(it) }?.toList()
        emit(remoteData)
    }

    override fun isMovieLiked(id: Int): Boolean {
        val result:MovieLocal? = movieDao.isMovieLiked(id)
        return result!=null
    }

    override fun changeLikeState(movieEntity: MovieEntity) {
        if(isMovieLiked(movieEntity.id)){
            movieDao.delete(movieMapper.mapFromEntity(movieEntity))
        }else{
            movieDao.insert(movieMapper.mapFromEntity(movieEntity))
        }
    }

    private fun fetchAllMoviesFavorite(): List<MovieLocal> = movieDao.fetchAllMoviesFavorite()

    private suspend fun fetchMoviesRemote(category: String?): ArrayList<MovieRemote>? {
        val data: MovieResponse?
        when (category) {
            "Popular" -> {
                data = safeApiCall({ movieApi.getMostPopular(AppConstants.API_KEY) }, "fetching movies")
            }
            "Upcoming" -> {
                data = safeApiCall({ movieApi.getUpcoming(AppConstants.API_KEY) }, "fetching movies")
            }
            "Top Rated" -> {
                data = safeApiCall({ movieApi.getTopRated(AppConstants.API_KEY) }, "fetching movies")
            }
            "Now Playing" -> {
                data = safeApiCall({ movieApi.getNowPlaying(AppConstants.API_KEY) }, "fetching movies")
            }
            else -> {
                data = safeApiCall({ movieApi.getMostPopular(AppConstants.API_KEY) }, "fetching movies")
            }
        }
        //val data = safeApiCall({ movieApi.getMostPopular(AppConstants.API_KEY) }, "fetching movies")
        return if (data != null) data.results as ArrayList<MovieRemote> else null
    }

}