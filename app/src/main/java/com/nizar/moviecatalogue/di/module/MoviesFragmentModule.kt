

package com.nizar.moviecatalogue.di.module

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import com.nizar.moviecatalogue.data.repository.MovieRepoImp
import com.nizar.moviecatalogue.presentation.commons.GridSpacingItemDecoration
import com.nizar.moviecatalogue.presentation.commons.ViewModelProviderFactory
import com.nizar.moviecatalogue.presentation.movies.MoviesAdapter
import com.nizar.moviecatalogue.presentation.movies.MoviesFragment
import com.nizar.moviecatalogue.presentation.movies.MoviesViewModel
import dagger.Module
import dagger.Provides


@Module
class MoviesFragmentModule {


    @Provides
    internal fun provideMainFragmentViewModel(movieRepoImp: MovieRepoImp): MoviesViewModel {
        return MoviesViewModel(movieRepoImp)
    }

    @Provides
    internal fun provideGridLayoutManager(fragment: MoviesFragment): GridLayoutManager {
        return GridLayoutManager((fragment.activity as Context?)!!, 2)
    }

    @Provides
    internal fun provideGridSpacingItemDecoration(): GridSpacingItemDecoration {
        return GridSpacingItemDecoration(2, 5, true)
    }

    @Provides
    internal fun provideMovieAdapter(context: Context): MoviesAdapter {
        return MoviesAdapter(ArrayList(),context)
    }


    @Provides
    internal fun mainFragmentViewModelProvider(moviesViewModel: MoviesViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(moviesViewModel)
    }

}