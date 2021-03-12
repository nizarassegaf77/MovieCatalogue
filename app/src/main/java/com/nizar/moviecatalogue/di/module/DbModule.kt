package com.nizar.moviecatalogue.di.module

import android.content.Context
import androidx.room.Room
import com.nizar.moviecatalogue.data.local.dataSource.MovieDao
import com.nizar.moviecatalogue.data.local.dataSource.MovieDb
import com.nizar.moviecatalogue.presentation.commons.AppConstants
import dagger.Module
import dagger.Provides
import javax.inject.Singleton


@Module
class DbModule{

    @Provides
    @Singleton
    internal fun provideMovieDb(context: Context): MovieDb {
        return Room.databaseBuilder(context, MovieDb::class.java, AppConstants.DB_NAME).fallbackToDestructiveMigration()
                .build()
    }

    @Provides
    @Singleton
    internal fun provideMovieDao(context: Context): MovieDao {
        return provideMovieDb(context).movieDao()
    }
}