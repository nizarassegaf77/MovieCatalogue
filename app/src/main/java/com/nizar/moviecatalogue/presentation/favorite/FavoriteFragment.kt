package com.nizar.moviecatalogue.presentation.favorite

import androidx.lifecycle.LifecycleOwner
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DefaultItemAnimator
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import com.nizar.moviecatalogue.R
import com.nizar.moviecatalogue.domain.MovieEntity
import com.nizar.moviecatalogue.presentation.base.BaseFragment
import com.nizar.moviecatalogue.presentation.commons.GridSpacingItemDecoration
import com.nizar.moviecatalogue.presentation.movies.MoviesAdapter
import com.nizar.moviecatalogue.presentation.movies.MoviesViewModel
import kotlinx.android.synthetic.main.fragment_detail_movie.*
import kotlinx.android.synthetic.main.fragment_movies.*
import kotlinx.android.synthetic.main.fragment_movies.toolbar
import javax.inject.Inject


class FavoriteFragment : BaseFragment<MoviesViewModel>(), MoviesAdapter.callback {

    @Inject
    internal lateinit var mViewModelFactory: ViewModelProvider.Factory

    @Inject
    lateinit var mMovieAdapter: MoviesAdapter

    override fun getLayoutId(): Int = R.layout.fragment_movies_favorite
    override fun getViewModel(): MoviesViewModel = ViewModelProviders.of(this, mViewModelFactory).get(
        MoviesViewModel::class.java)
    override fun getLifeCycleOwner(): LifecycleOwner = this

    override fun initUI() {
        moviesRecycler.setHasFixedSize(true)
        moviesRecycler.layoutManager = LinearLayoutManager(context)
        moviesRecycler.itemAnimator = DefaultItemAnimator()
        moviesRecycler.adapter = mMovieAdapter
        //toolbar.title = "Favorite Movie"
        mMovieAdapter.setListener(this)
        toolbar.setNavigationOnClickListener {
            activity?.onBackPressed()
        }
        showLoading()
        getViewModel().getFavorites()
    }

    override fun onSuccess(data: Any) {
        val arrayList:ArrayList<MovieEntity> = ArrayList()
        (data as List<*>).forEach {
            arrayList.add(it as MovieEntity)
        }
        mMovieAdapter.addItems(arrayList)
        /*if (data is ArrayList<*>) {
            mMovieAdapter.addItems(data as ArrayList<MovieEntity>?)
        }else{
            mMovieAdapter.addItems(arrayListOf(data as MovieEntity))
        }*/
    }

    override fun onFailure(error: String) {
        showMessage(error)
    }

    override fun onItemClick(movieRemote: MovieEntity) {
        //mListener.onMovieSelected(movieRemote)
    }

}