package com.nizar.moviecatalogue.presentation.detail

/**
 * Created by Nizar Assegaf on 10,March,2021
 */

sealed class DetailViewState {
    class ReviewsFetched<T : Any>(val data: T) : DetailViewState()
    class LikeState(val isLiked: Boolean) : DetailViewState()
    class MessageRes(val resId: Int) : DetailViewState()
}