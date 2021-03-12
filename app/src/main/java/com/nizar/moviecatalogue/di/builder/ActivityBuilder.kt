

package com.nizar.moviecatalogue.di.builder

import com.nizar.moviecatalogue.di.module.*
import com.nizar.moviecatalogue.presentation.favorite.FavoriteActivity
import com.nizar.moviecatalogue.presentation.main.MainActivity
import dagger.Module
import dagger.android.ContributesAndroidInjector

@Module
abstract class ActivityBuilder {


    @ContributesAndroidInjector(modules = [(MainActivityModule::class),(MoviesFragmentProvider::class), (DetailFragmentProvider::class)])
    internal abstract fun bindMainActivity(): MainActivity

    @ContributesAndroidInjector(modules = [(FavoriteActivityModule::class),(FavoriteFragmentProvider::class),  ])
    internal abstract fun bindFavoriteActivity(): FavoriteActivity

}