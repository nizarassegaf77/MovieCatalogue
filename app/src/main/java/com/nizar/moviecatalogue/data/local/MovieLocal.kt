package com.nizar.moviecatalogue.data.local

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "movies")
data class MovieLocal(@PrimaryKey
                      @ColumnInfo(name = "id")
                      var id: Int,

                      @ColumnInfo(name = "poster_path")
                      val poster_path: String?,

                      @ColumnInfo(name = "popularity")
                      val popularity: Double,

                      @ColumnInfo(name = "vote_count")
                      var vote_count: Int,

                      @ColumnInfo(name = "video")
                      var video: Boolean,

                      @ColumnInfo(name = "vote_average")
                      val vote_average: Double,

                      @ColumnInfo(name = "title")
                      val title: String?,

                      @ColumnInfo(name = "backdrop_path")
                      val backdrop_path: String? = "null",

                      @ColumnInfo(name = "overview")
                      val overview: String?,

                      @ColumnInfo(name = "release_date")
                      val release_date: String?
)