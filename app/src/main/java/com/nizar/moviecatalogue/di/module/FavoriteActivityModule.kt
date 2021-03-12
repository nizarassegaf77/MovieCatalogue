package com.nizar.moviecatalogue.di.module

import com.nizar.moviecatalogue.data.repository.MovieRepoImp
import com.nizar.moviecatalogue.presentation.favorite.FavoriteViewModel
import com.nizar.moviecatalogue.presentation.main.MainViewModel
import dagger.Module
import dagger.Provides

@Module
class FavoriteActivityModule {

    @Provides
    internal fun provideFavoriteViewModel(movieRepository: MovieRepoImp): FavoriteViewModel {
        return FavoriteViewModel(movieRepository)
    }

}