package com.nizar.moviecatalogue.presentation.movies

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.mock
import com.nhaarman.mockitokotlin2.times
import com.nhaarman.mockitokotlin2.verify
import com.nhaarman.mockitokotlin2.whenever
import com.nizar.moviecatalogue.CoroutineTestingRule
import com.nizar.moviecatalogue.data.repository.MovieRepo
import com.nizar.moviecatalogue.domain.MovieEntity
import com.nizar.moviecatalogue.presentation.base.ViewState
import com.nizar.moviecatalogue.presentation.commons.TestDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class MoviesViewModelTest {


    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = CoroutineTestingRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var moviesRepo: MovieRepo
    private lateinit var moviesViewModel: MoviesViewModel

    private val mockMovieEntity = MovieEntity(1, "", 3.4, 12, true, 2.3, "test", "", "", "")
    private val mockMovieResponse = arrayListOf(mockMovieEntity, mockMovieEntity, mockMovieEntity)

    @Before
    @Throws(Exception::class)
    fun setup() {
        MockitoAnnotations.initMocks(this)
        moviesRepo = mock()
        moviesViewModel = MoviesViewModel(
            moviesRepo,
            TestDispatcher()
        )
    }

    @Test
    fun testingGetMoviesResult_success() {
        val expected = mockMovieResponse
        runBlockingTest {
            whenever(moviesRepo.getMovies("")).thenReturn(flow { emit(mockMovieResponse) })
            moviesViewModel.apply {
                getMovies()
                uiState.observeForever {
                    val actual = (it as ViewState.HasData<*>).data as ArrayList<MovieEntity>
                    assert(expected == actual)
                }
                verify(moviesRepo, times(1)).getMovies("")
            }
        }
    }


    @Test
    fun testingGetMoviesResult_failure() {
        val expected = "No movies found"
        runBlockingTest {
            whenever(moviesRepo.getMovies("")).thenReturn(flow { emit(null) })
            moviesViewModel.apply {
                getMovies()
                uiState.observeForever {
                    val actual = (it as ViewState.HasError).error
                    assert(expected == actual)
                }
                verify(moviesRepo, times(1)).getMovies("")
            }
        }
    }

}