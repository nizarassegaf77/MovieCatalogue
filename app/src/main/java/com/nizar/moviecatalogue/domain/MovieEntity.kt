package com.nizar.moviecatalogue.domain

data class MovieEntity(

        var id: Int,

        var poster_path: String?,

        var popularity: Double,

        var vote_count: Int,

        var video: Boolean,

        var vote_average: Double,

        var title: String?,

        var backdrop_path: String? = "null",

        var overview: String?,

        var release_date: String?
)