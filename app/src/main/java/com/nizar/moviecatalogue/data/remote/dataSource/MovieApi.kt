package com.nizar.moviecatalogue.data.remote.dataSource

import com.nizar.moviecatalogue.data.remote.MovieResponse
import com.nizar.moviecatalogue.presentation.commons.AppConstants.Companion.API_KEY_QUERY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query
import javax.inject.Singleton


@Singleton
interface MovieApi {


    companion object {

        const val POPULAR_MOVIES_QUERY: String = ("movie/popular")
        const val UPCOMING_MOVIES_QUERY: String = ("movie/upcoming")
        const val TOP_RATED_MOVIES_QUERY: String = ("movie/top_rated")
        const val NOW_PLAYING_MOVIES_QUERY: String = ("movie/now_playing")

    }



    @GET(POPULAR_MOVIES_QUERY)
    suspend fun getMostPopular(@Query(API_KEY_QUERY) apiKey: String): Response<MovieResponse>

    @GET(UPCOMING_MOVIES_QUERY)
    suspend fun getUpcoming(@Query(API_KEY_QUERY) apiKey: String): Response<MovieResponse>

    @GET(TOP_RATED_MOVIES_QUERY)
    suspend fun getTopRated(@Query(API_KEY_QUERY) apiKey: String): Response<MovieResponse>

    @GET(NOW_PLAYING_MOVIES_QUERY)
    suspend fun getNowPlaying(@Query(API_KEY_QUERY) apiKey: String): Response<MovieResponse>

}