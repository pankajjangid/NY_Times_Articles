package com.pankajjangid.nytimespopulararticles.modules.home

interface HomeListener {

    fun loadPopularArticles()
    fun onLoading()
    fun onSuccess(responseData: Any?)
    fun onFailure(message: String)
}
