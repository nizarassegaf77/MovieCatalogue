

package com.nizar.moviecatalogue.di.module


import com.nizar.moviecatalogue.data.repository.MovieRepoImp
import com.nizar.moviecatalogue.presentation.main.MainViewModel
import dagger.Module
import dagger.Provides


@Module
class MainActivityModule {

    @Provides
    internal fun provideMainViewModel(movieRepository: MovieRepoImp): MainViewModel {
        return MainViewModel(movieRepository)
    }

}