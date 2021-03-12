package com.nizar.moviecatalogue.data.remote.dataSource

import com.nizar.moviecatalogue.data.remote.ReviewResponse
import com.nizar.moviecatalogue.presentation.commons.AppConstants
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query

interface ReviewApi {


    companion object {

        const val GET_MOVIE_REVIEW: String = ("movie/{movie_id}/reviews")
    }

    @GET(GET_MOVIE_REVIEW)
    suspend fun getReviews(@Path("movie_id") id: Int, @Query(AppConstants.API_KEY_QUERY) apiKey: String): Response<ReviewResponse>
}