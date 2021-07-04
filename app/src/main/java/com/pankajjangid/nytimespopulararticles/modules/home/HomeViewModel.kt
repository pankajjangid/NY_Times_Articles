package com.pankajjangid.nytimespopulararticles.modules.home

import android.app.Activity
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.google.gson.Gson
import com.pankajjangid.nytimespopulararticles.networking.ApiUtils
import com.pankajjangid.nytimespopulararticles.networking.response.PopularArticleResponse
import com.pankajjangid.nytimespopulararticles.utils.Coroutines
import com.pankajjangid.nytimespopulararticles.utils.Utils

class HomeViewModel(private val repository: HomeRepository):ViewModel() {

    var listener: HomeListener? = null
    var isLoading = MutableLiveData<Boolean>()

    init {
        isLoading.value = false

    }

    fun loadPopularArticles( period:String) {
        isLoading.value=false

        listener?.onLoading()
        Coroutines.main {

            try {

                val response = repository.loadPopularArticles(period)
                listener?.onSuccess(response)

            } catch (e: Exception) {
                e.printStackTrace()
                e.message?.let { listener?.onFailure(it) }

            }

        }

    }


    fun onRefreshing(){
            isLoading.value=true
            listener?.loadPopularArticles()
    }
}