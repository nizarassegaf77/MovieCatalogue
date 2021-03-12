package com.nizar.moviecatalogue.di.module

import com.nizar.moviecatalogue.data.local.dataSource.MovieDao
import com.nizar.moviecatalogue.data.remote.dataSource.MovieApi
import com.nizar.moviecatalogue.data.remote.dataSource.ReviewApi
import com.nizar.moviecatalogue.data.repository.MovieRepo
import com.nizar.moviecatalogue.data.repository.MovieRepoImp
import com.nizar.moviecatalogue.domain.MovieMapper
import com.nizar.moviecatalogue.domain.MovieMapperImp
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class RepoModule {


    @Provides
    @Singleton
    internal fun provideMovieDataMapper(): MovieMapper {
        return MovieMapperImp()
    }

    @Provides
    @Singleton
    internal fun provideMovieRepository(movieDao: MovieDao,
                                        movieApi: MovieApi,
                                        reviewApi: ReviewApi,
                                        movieMapper: MovieMapper): MovieRepo {
        return MovieRepoImp(movieDao, movieApi,reviewApi, movieMapper)
    }

}