package com.nizar.moviecatalogue.presentation.movies

import android.content.Context
import android.net.Uri
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.LinearLayout
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nizar.moviecatalogue.R
import com.nizar.moviecatalogue.domain.MovieEntity
import javax.inject.Inject

/**
 * Created by Nizar Assegaf on 10,March,2021
 */

class MoviesAdapter @Inject constructor(
    private val mMoviesList: MutableList<MovieEntity>,
    val mContext: Context
) : RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>() {


    val mLayoutInflater = LayoutInflater.from(mContext);
    private lateinit var listener: callback

    override fun getItemCount(): Int {
        return mMoviesList.size
    }

    fun setListener(mCallback: callback) {
        listener = mCallback
    }

    fun addItems(mList: ArrayList<MovieEntity>?) {
        if (mList != null) {
            clearItems()
            mMoviesList.addAll(mList)
            notifyDataSetChanged()
        }
    }


    private fun clearItems() {
        mMoviesList.clear()
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view = mLayoutInflater.inflate(R.layout.item_movie_view, parent, false)
        return MovieViewHolder(view)
    }

    // stores and recycles views as they are scrolled off screen
    inner class MovieViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var myImgView: ImageView = itemView.findViewById(R.id.movieImg) as ImageView
        var mTitleView: TextView = itemView.findViewById(R.id.title_tv) as TextView
        var mReleaseDate: TextView = itemView.findViewById(R.id.release_date_tv) as TextView
        var mOverview: TextView = itemView.findViewById(R.id.text_overview_tv) as TextView
        var mContainerLayout: LinearLayout = itemView.findViewById(R.id.container_item_movive) as LinearLayout

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        val moviePoster = mMoviesList[position].poster_path
        if (moviePoster != null)
            Glide.with(mContext)
                .load(Uri.parse("http://image.tmdb.org/t/p/w185$moviePoster"))
                .into(holder.myImgView)
        //Log.v("Img = ", "http://image.tmdb.org/t/p/w185$moviePoster")
        holder.mTitleView.text = mMoviesList[position].title ?: ""
        holder.mReleaseDate.text = mMoviesList[position].release_date ?: ""
        holder.mOverview.text = mMoviesList[position].overview ?: ""
        holder.mContainerLayout.setOnClickListener { listener.onItemClick(mMoviesList[position]) }

    }

    interface callback {
        fun onItemClick(movieRemote: MovieEntity)
    }

}