package com.nizar.moviecatalogue.presentation.main

import android.content.ActivityNotFoundException
import android.content.Context
import android.content.Intent
import android.net.Uri
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.nizar.moviecatalogue.R
import com.nizar.moviecatalogue.data.remote.TrailerRemote
import com.nizar.moviecatalogue.domain.MovieEntity
import com.nizar.moviecatalogue.presentation.base.BaseActivity
import com.nizar.moviecatalogue.presentation.detail.DetailFragment
import com.nizar.moviecatalogue.presentation.movies.MoviesFragment
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject

class MainActivity : BaseActivity<MainViewModel>(), HasSupportFragmentInjector,
    MoviesFragment.MainFragmentListener {


    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    @Inject
    lateinit var mMainViewModel: MainViewModel

    private lateinit var mContext: Context

    override fun getLayoutId(): Int = R.layout.activity_main
    override fun getViewModel(): MainViewModel = mMainViewModel


    override fun supportFragmentInjector(): AndroidInjector<Fragment> =
        fragmentDispatchingAndroidInjector


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        setUp()
    }

    private fun setUp() {
        val mMainFragment = MoviesFragment()
        mMainFragment.mListener = this
        replaceFragment(mMainFragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        //supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
        if (fragment is DetailFragment)
            supportFragmentManager.beginTransaction().replace(R.id.container, fragment)
                .addToBackStack("details").commit()
        else {
            supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
        }
    }

    override fun onBackPressed() {
        setUp()
    }

    override fun onMovieSelected(movieRemote: MovieEntity) {
        val mDetailFragment = DetailFragment(movieRemote)
        replaceFragment(mDetailFragment)
    }

}

