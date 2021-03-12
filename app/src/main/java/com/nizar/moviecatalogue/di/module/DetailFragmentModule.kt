package com.nizar.moviecatalogue.di.module

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.nizar.moviecatalogue.data.repository.MovieRepoImp
import com.nizar.moviecatalogue.presentation.commons.ViewModelProviderFactory
import com.nizar.moviecatalogue.presentation.detail.DetailFragment
import com.nizar.moviecatalogue.presentation.detail.DetailViewModel
import com.nizar.moviecatalogue.presentation.detail.ReviewAdapter
import dagger.Module
import dagger.Provides

@Module
class DetailFragmentModule {

    @Provides
    internal fun provideDetailFragmentViewModel(movieRepoImpl: MovieRepoImp): DetailViewModel {
        return DetailViewModel(movieRepoImpl)
    }

    @Provides
    internal fun provideLinearLayoutManager(fragment: DetailFragment): LinearLayoutManager {
        return LinearLayoutManager(fragment.activity)
    }

    @Provides
    internal fun provideTrailerAdapter(context: Context): ReviewAdapter {
        return ReviewAdapter(ArrayList(), context)
    }

    @Provides
    internal fun detailFragmentViewModelProvider(mainViewModel: DetailViewModel): ViewModelProvider.Factory {
        return ViewModelProviderFactory(mainViewModel)
    }

}