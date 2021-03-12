package com.nizar.moviecatalogue.presentation.detail

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.nhaarman.mockitokotlin2.*
import com.nizar.moviecatalogue.CoroutineTestingRule
import com.nizar.moviecatalogue.data.remote.AuthorDetails
import com.nizar.moviecatalogue.data.remote.ReviewRemote
import com.nizar.moviecatalogue.data.repository.MovieRepo
import com.nizar.moviecatalogue.domain.MovieEntity
import com.nizar.moviecatalogue.presentation.base.ViewState
import com.nizar.moviecatalogue.presentation.commons.TestDispatcher
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.Before
import org.junit.Rule
import org.junit.Test
import org.mockito.MockitoAnnotations

@ExperimentalCoroutinesApi
class DetailViewModelTest {


    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = CoroutineTestingRule()

    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var moviesRepo: MovieRepo
    private lateinit var detailViewModel: DetailViewModel
    private val mockTrailer =
        ReviewRemote("1", AuthorDetails("", "", "", 0), "", "", "test", "youtube", "trailer")
    private val mockTrailerResponse = arrayListOf(mockTrailer, mockTrailer, mockTrailer)

    @Before
    @Throws(Exception::class)
    fun setup() {
        MockitoAnnotations.initMocks(this)
        moviesRepo = mock()
        detailViewModel = DetailViewModel(
            moviesRepo,
            TestDispatcher()
        )
    }

    @Test
    fun test_GetTrailers_success() {
        val expected = mockTrailerResponse
        runBlockingTest {
            whenever(moviesRepo.getReviews(any())).thenReturn(mockTrailerResponse)
            detailViewModel.apply {
                fetchReviews(1)
                uiState.observeForever {
                    val actual =
                        ((it as ViewState.HasData<*>).data as DetailViewState.ReviewsFetched<*>).data as ArrayList<ReviewRemote>
                    assert(expected == actual)
                }
                verify(moviesRepo, times(1)).getReviews(any())
            }
        }
    }


    @Test
    fun test_GetTrailers_failure() {
        val expected = "No review found"
        runBlockingTest {
            whenever(moviesRepo.getReviews(any())).thenReturn(null)
            detailViewModel.apply {
                fetchReviews(1)
                uiState.observeForever {
                    val actual = (it as ViewState.HasError).error
                    assert(expected == actual)
                }
                verify(moviesRepo, times(1)).getReviews(any())
            }
        }
    }

    @Test
    fun test_GetMovieLikeState() {
        val expected = true
        runBlockingTest {
            whenever(moviesRepo.isMovieLiked(any())).thenReturn(true)
            detailViewModel.apply {
                getLikeState(1)
                uiState.observeForever {
                    val actual =
                        ((it as ViewState.HasData<*>).data as DetailViewState.LikeState).isLiked
                    assert(expected == actual)
                }
                verify(moviesRepo, times(1)).isMovieLiked(any())
            }
        }
    }

    @Test
    fun test_updateLikeStatus_false() {
        val mockMovieEntity = MovieEntity(1,  "", 3.4, 12, true, 2.3, "test", "", "", "")
        val expected = false
        runBlockingTest {
            detailViewModel.apply {
                updateLikeStatus(mockMovieEntity)
                uiState.observeForever {
                    val actual =
                        ((it as ViewState.HasData<*>).data as DetailViewState.LikeState).isLiked
                    assert(expected == actual)
                }
                verify(moviesRepo, times(1)).changeLikeState(mockMovieEntity)
            }
        }
    }

}