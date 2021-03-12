package com.nizar.moviecatalogue.presentation.favorite

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import com.nizar.moviecatalogue.R
import com.nizar.moviecatalogue.presentation.base.BaseActivity
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.support.HasSupportFragmentInjector
import javax.inject.Inject


class FavoriteActivity : BaseActivity<FavoriteViewModel>(), HasSupportFragmentInjector{


    @Inject
    internal lateinit var fragmentDispatchingAndroidInjector: DispatchingAndroidInjector<Fragment>

    private lateinit var mContext: Context
    @Inject
    lateinit var mFavoriteViewModel: FavoriteViewModel

    override fun getLayoutId(): Int = R.layout.activity_favorite
    override fun getViewModel(): FavoriteViewModel = mFavoriteViewModel


    override fun supportFragmentInjector(): AndroidInjector<Fragment> =
        fragmentDispatchingAndroidInjector


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mContext = this
        setUp()
    }

    private fun setUp() {
        val mFavoriteFragment = FavoriteFragment()
        replaceFragment(mFavoriteFragment)
    }

    private fun replaceFragment(fragment: Fragment) {
        supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()

        /*if (fragment is DetailFragment)
            supportFragmentManager.beginTransaction().replace(R.id.container, fragment)
                .addToBackStack("details").commit()
        else {
            supportFragmentManager.beginTransaction().replace(R.id.container, fragment).commit()
        }*/
    }

    override fun onBackPressed() {
        finish()
    }

}