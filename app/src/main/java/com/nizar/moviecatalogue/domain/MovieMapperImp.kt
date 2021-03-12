package com.nizar.moviecatalogue.domain

import com.nizar.moviecatalogue.data.local.MovieLocal
import com.nizar.moviecatalogue.data.remote.MovieRemote

class MovieMapperImp : MovieMapper {


    override fun mapFromLocal(from: MovieLocal): MovieEntity {
        return MovieEntity(
            from.id,
            from.poster_path, from.popularity,
            from.vote_count,
            from.video,
            from.vote_average,
            from.title,
            from.backdrop_path,
            from.overview,
            from.release_date
        )
    }

    override fun mapFromRemote(from: MovieRemote): MovieEntity {
        return MovieEntity(
            from.id,
            from.poster_path, from.popularity,
            from.vote_count,
            from.video,
            from.vote_average,
            from.title,
            from.backdrop_path,
            from.overview,
            from.release_date
        )
    }

    override fun mapFromEntity(from: MovieEntity): MovieLocal {
        return MovieLocal(
            from.id,
            from.poster_path, from.popularity,
            from.vote_count,
            from.video,
            from.vote_average,
            from.title,
            from.backdrop_path,
            from.overview,
            from.release_date
        )
    }
}