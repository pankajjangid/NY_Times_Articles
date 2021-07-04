package com.pankajjangid.nytimespopulararticles.utils

// States represented as enums
enum class NetworkState(val isConnected : Boolean) {

    CONNECTED(true),

    DISCONNECTED(false),

    UNINITIALIZED(false)

}
