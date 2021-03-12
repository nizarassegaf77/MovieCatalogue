package com.nizar.moviecatalogue.di.module

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nizar.moviecatalogue.data.repository.MovieRepoImp
import com.nizar.moviecatalogue.presentation.commons.ViewModelProviderFactory
import com.nizar.moviecatalogue.presentation.detail.DetailFragment
import com.nizar.moviecatalogue.presentation.detail.DetailViewModel
import com.nizar.moviecatalogue.presentation.detail.ReviewAdapter
import com.nizar.moviecatalogue.presentation.favorite.FavoriteFragment
import com.nizar.moviecatalogue.presentation.favorite.FavoriteViewModel
import com.nizar.moviecatalogue.presentation.movies.MoviesAdapter
import com.nizar.moviecatalogue.presentation.movies.MoviesViewModel
import dagger.Module
import dagger.Provides

@Module
class FavoriteFragmentModule {

    @Provides
    internal fun provideFavoriteFragmentViewModel(movieRepoImpl: MovieRepoImp): MoviesViewModel {
        return MoviesViewModel(movieRepoImpl)
    }

    @Provides
    internal fun provideLinearLayoutManager(fragment: FavoriteFragment): LinearLayoutManager {
        return LinearLayoutManager(fragment.activity)
    }

    @Provides
    internal fun favoriteFragmentViewModelProvider(mainViewModel: MoviesViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(mainViewModel)
    }

    @Provides
    internal fun provideMovieAdapter(context: Context): MoviesAdapter {
        return MoviesAdapter(ArrayList(),context)
    }

}