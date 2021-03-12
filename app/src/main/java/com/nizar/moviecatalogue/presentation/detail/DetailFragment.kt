package com.nizar.moviecatalogue.presentation.detail

import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.os.Bundle
import android.view.View
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.LinearLayoutManager
import com.bumptech.glide.Glide
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.nizar.moviecatalogue.R
import com.nizar.moviecatalogue.data.remote.ReviewRemote
import com.nizar.moviecatalogue.domain.MovieEntity
import com.nizar.moviecatalogue.presentation.base.BaseFragment
import kotlinx.android.synthetic.main.fragment_detail_movie.*
import javax.inject.Inject

@Suppress("UNCHECKED_CAST")
class DetailFragment(val movie: MovieEntity) : BaseFragment<DetailViewModel>() {


    @Inject
    internal lateinit var mViewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var mLinearLayoutManager: LinearLayoutManager

    @Inject
    lateinit var mReviewAdapter: ReviewAdapter



    override fun getLayoutId(): Int = R.layout.fragment_detail_movie
    override fun getViewModel(): DetailViewModel = ViewModelProviders.of(this, mViewModelFactory).get(DetailViewModel::class.java)
    override fun getLifeCycleOwner(): LifecycleOwner = this

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

    override fun initUI() {
        trailersRecycler.setHasFixedSize(true)
        trailersRecycler.layoutManager = mLinearLayoutManager
        trailersRecycler.itemAnimator = DefaultItemAnimator()
        trailersRecycler.adapter = mReviewAdapter
        toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
        initMovieUi(movie)
        getViewModel().getLikeState(movie.id)
        getViewModel().fetchReviews(movie.id)
    }

    private fun initMovieUi(movie: MovieEntity) {
        title_tv.text = movie.title
        text_overview_tv.text = movie.overview
        release_date_tv.text = "Released in : ${movie.release_date}"
        Glide.with(this)
            .load("http://image.tmdb.org/t/p/w185${movie.poster_path}")
            .into(moviePoster)
        like_img.setOnClickListener { getViewModel().updateLikeStatus(movie) }
    }

    override fun onSuccess(data: Any) {
        when (data) {
            is DetailViewState.MessageRes -> showMessage(getString(data.resId))
            is DetailViewState.LikeState -> renderLikeState(data.isLiked)
            is DetailViewState.ReviewsFetched<*> -> renderReviews(data.data as ArrayList<ReviewRemote>)
            //is DetailViewState.TrailersFetched<*> -> renderTrailers(data.data as ArrayList<TrailerRemote>)
        }
    }

    override fun onFailure(error: String) {
        trailers_loading.visibility = View.GONE
        showMessage(error)
    }

    private fun renderReviews(trailers: ArrayList<ReviewRemote>) {
        trailers_loading.visibility = View.GONE
        mReviewAdapter.addItems(trailers)
    }

    private fun renderLikeState(isLiked: Boolean) {
        //if (isLiked) R.string.movie_liked else R.string.movie_unliked
        like_img.setImageResource(if (isLiked) R.drawable.like else R.drawable.dislike)
    }


}