package com.nizar.moviecatalogue.presentation.detail

import android.content.Context
import android.media.Image
import android.net.Uri
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.nizar.moviecatalogue.R
import com.nizar.moviecatalogue.data.remote.ReviewRemote

/**
 * Created by Nizar Assegaf on 10,March,2021
 */
class ReviewAdapter(var mReviewRemoteList: MutableList<ReviewRemote>, val mContext: Context) :
    RecyclerView.Adapter<ReviewAdapter.ReviewViewHolder>() {

    val mLayoutInflater = LayoutInflater.from(mContext);

    override fun getItemCount(): Int {
        return if (mReviewRemoteList.size > 0) {
            mReviewRemoteList.size
        } else {
            0
        }
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ReviewViewHolder {
        val view = mLayoutInflater.inflate(R.layout.item_review_view, parent, false)
        return ReviewViewHolder(view)
    }

    fun addItems(mList: List<ReviewRemote>) {
        mReviewRemoteList.addAll(mList)
        notifyDataSetChanged()
    }

    fun clearItems() {
        mReviewRemoteList.clear()
    }


    inner class ReviewViewHolder internal constructor(itemView: View) :
        RecyclerView.ViewHolder(itemView) {
        var tvTrailerName: TextView = itemView.findViewById(R.id.trailer_tv) as TextView
        var tvAccount: TextView = itemView.findViewById(R.id.review_account_tv) as TextView
        var tvUsernameAccount: TextView =
            itemView.findViewById(R.id.review_account_username_tv) as TextView
        var ivAccount: ImageView = itemView.findViewById(R.id.account_iv) as ImageView

    }

    override fun onBindViewHolder(holder: ReviewViewHolder, position: Int) {
        val trailer = mReviewRemoteList[position]
        holder.tvTrailerName.text = trailer.content
        holder.tvAccount.text = trailer.author
        holder.tvUsernameAccount.text =
            if (trailer.author_details.username != "") trailer.author_details.username else "-"
        Glide.with(mContext)
            .load(trailer.author_details.avatar_path).placeholder(R.color.gray)
            .into(holder.ivAccount)
    }


}
