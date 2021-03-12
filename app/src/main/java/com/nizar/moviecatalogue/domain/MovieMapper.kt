package com.nizar.moviecatalogue.domain

import com.nizar.moviecatalogue.data.local.MovieLocal
import com.nizar.moviecatalogue.data.remote.MovieRemote


interface MovieMapper {
     fun mapFromLocal(from: MovieLocal): MovieEntity
     fun mapFromRemote(from: MovieRemote): MovieEntity
     fun mapFromEntity(from: MovieEntity): MovieLocal
}