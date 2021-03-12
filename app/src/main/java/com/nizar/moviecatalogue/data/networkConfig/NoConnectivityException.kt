package com.nizar.moviecatalogue.data.networkConfig

import java.io.IOException

class NoConnectivityException : IOException() {

    override val message: String
        get() = "No Internet Connection"
}