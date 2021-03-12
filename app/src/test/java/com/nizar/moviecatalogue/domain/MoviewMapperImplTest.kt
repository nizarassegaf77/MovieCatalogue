package com.nizar.moviecatalogue.domain

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nizar.moviecatalogue.data.local.MovieLocal
import com.nizar.moviecatalogue.data.remote.MovieRemote
import org.junit.Assert
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations


class MovieMapperImpTest {

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var movieMapper: MovieMapper
    private val mockMovieEntity = MovieEntity(1, "", 3.4, 12, true, 2.3, "test", "", "", "")
    private val mockMovieLocal = MovieLocal(1, "", 3.4, 12, true, 2.3, "test", "", "", "")
    private val mockMovieRemote = MovieRemote(12, 1, true, 2.3, "test", 3.4, "", "", "", "", false,"","")


    @Before
    @Throws(Exception::class)
    fun setup() {
        MockitoAnnotations.initMocks(this)
        movieMapper = MovieMapperImp()
    }


    @Test
    fun test_mapFromLocal(){
        val expected = mockMovieEntity
        val actual = movieMapper.mapFromLocal(mockMovieLocal)
        Assert.assertEquals(expected, actual)
    }

    @Test
    fun test_mapFromRemoteToLocal(){
        val expected = mockMovieEntity
        val actual = movieMapper.mapFromRemote(mockMovieRemote)
        Assert.assertEquals(expected, actual)
    }


}