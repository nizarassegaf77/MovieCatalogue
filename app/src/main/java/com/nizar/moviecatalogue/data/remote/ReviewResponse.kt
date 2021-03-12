package com.nizar.moviecatalogue.data.remote

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class ReviewResponse(
    var id: Int,
    var page: Int,
    var results: List<ReviewRemote>,
    var total_pages: Int,
    var total_results: Int
): Parcelable

@Parcelize
data class ReviewRemote(
    var author: String,
    var author_details: AuthorDetails,
    var content: String,
    var created_at: String,
    var id: String,
    var updated_at: String,
    var url: String
): Parcelable

@Parcelize
data class AuthorDetails(
    var name: String,
    var username: String,
    var avatar_path: String,
    var rating: Int
): Parcelable