package com.nizar.moviecatalogue.presentation.commons

import com.nizar.moviecatalogue.presentation.commons.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlin.coroutines.CoroutineContext

class TestDispatcher : CoroutineDispatcher() {
    override val Main: CoroutineContext = Dispatchers.Unconfined
    override val IO: CoroutineContext = Dispatchers.Unconfined
}