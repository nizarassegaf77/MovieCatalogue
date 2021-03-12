package com.nizar.moviecatalogue.di.module

import com.nizar.moviecatalogue.presentation.favorite.FavoriteFragment
import com.nizar.moviecatalogue.presentation.movies.MoviesFragment
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class FavoriteFragmentProvider {

    @ContributesAndroidInjector(modules =[(FavoriteFragmentModule::class)])
    internal abstract fun provideFavoriteFragmentFactory(): FavoriteFragment

}