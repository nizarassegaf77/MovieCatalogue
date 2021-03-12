package com.nizar.moviecatalogue.data.local.dataSource

import androidx.room.*
import com.nizar.moviecatalogue.data.local.MovieLocal
import javax.inject.Singleton


@Singleton
@Database(entities = [(MovieLocal::class)], version = 1, exportSchema = false)
abstract class MovieDb : RoomDatabase() {
    abstract fun movieDao(): MovieDao
}

@Dao
interface MovieDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(movies: MovieLocal)

    @Query("SELECT * FROM movies")
    fun fetchAllMoviesFavorite(): List<MovieLocal>

    @Query("SELECT * FROM movies WHERE id = :movieId ")
    fun isMovieLiked(movieId: Int): MovieLocal?

    @Delete
    fun delete(movies: MovieLocal)

}
