package com.nizar.moviecatalogue.presentation.movies

import android.content.Intent
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.View
import android.widget.TextView
import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.nizar.moviecatalogue.R
import com.nizar.moviecatalogue.domain.MovieEntity
import com.nizar.moviecatalogue.presentation.base.BaseFragment
import com.nizar.moviecatalogue.presentation.commons.GridSpacingItemDecoration
import com.nizar.moviecatalogue.presentation.favorite.FavoriteActivity
import kotlinx.android.synthetic.main.fragment_movies.*
import javax.inject.Inject


class MoviesFragment : BaseFragment<MoviesViewModel>(), MoviesAdapter.callback {

    @Inject
    internal lateinit var mViewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var mGridLayoutManager: GridLayoutManager

    @Inject
    lateinit var mGridSpacingItemDecoration: GridSpacingItemDecoration

    @Inject
    lateinit var mMovieAdapter: MoviesAdapter

    lateinit var mListener: MainFragmentListener

    private lateinit var dialog: BottomSheetDialog

    override fun getLayoutId(): Int = R.layout.fragment_movies
    override fun getViewModel(): MoviesViewModel =
        ViewModelProviders.of(this, mViewModelFactory).get(MoviesViewModel::class.java)

    override fun getLifeCycleOwner(): LifecycleOwner = this

    override fun initUI() {
        moviesRecycler.setHasFixedSize(true)
        moviesRecycler.layoutManager = LinearLayoutManager(context)
        moviesRecycler.itemAnimator = DefaultItemAnimator()
        moviesRecycler.adapter = mMovieAdapter
        mMovieAdapter.setListener(this)
        showLoading()
        getViewModel().getMovies()

        categoryBtn.setOnClickListener {
            showDialogCategory()
        }

        favoriteButton.setOnClickListener {
            context?.startActivity(Intent(context, FavoriteActivity::class.java))
        }
    }

    private fun showDialogCategory() {
        val view: View = layoutInflater.inflate(R.layout.bottomsheet_category, null)
        val tvPopular = view.findViewById<TextView>(R.id.popular_tv)
        val tvUpcoming = view.findViewById<TextView>(R.id.upcoming_tv)
        val tvTopRated = view.findViewById<TextView>(R.id.top_rated_tv)
        val tvNowPlaying = view.findViewById<TextView>(R.id.now_playing_tv)

        tvPopular.setOnClickListener {
            getViewModel().getMovies(tvPopular.text.toString())
            hideDialogCategory()
        }

        tvUpcoming.setOnClickListener {
            getViewModel().getMovies(tvUpcoming.text.toString())
            hideDialogCategory()
        }

        tvTopRated.setOnClickListener {
            getViewModel().getMovies(tvTopRated.text.toString())
            hideDialogCategory()
        }

        tvNowPlaying.setOnClickListener {
            getViewModel().getMovies(tvNowPlaying.text.toString())
            hideDialogCategory()
        }

        context?.let {
            dialog = BottomSheetDialog(it)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setContentView(view)
            dialog.show()
        }
    }

    private fun hideDialogCategory() {
        dialog.dismiss()
    }

    override fun onSuccess(data: Any) {
        if (data is ArrayList<*>) mMovieAdapter.addItems(data as ArrayList<MovieEntity>?)
    }

    override fun onFailure(error: String) {
        showMessage(error)
    }

    override fun onItemClick(movieRemote: MovieEntity) {
        mListener.onMovieSelected(movieRemote)
    }

    interface MainFragmentListener {
        fun onMovieSelected(movieRemote: MovieEntity)
    }


}