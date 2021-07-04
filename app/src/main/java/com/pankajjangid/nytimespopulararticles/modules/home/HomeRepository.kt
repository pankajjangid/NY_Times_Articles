package com.pankajjangid.nytimespopulararticles.modules.home

import com.google.gson.JsonObject
import com.pankajjangid.nytimespopulararticles.networking.MyApi
import com.pankajjangid.nytimespopulararticles.networking.SafeApiRequest
import com.pankajjangid.nytimespopulararticles.networking.WebServiceUrl
import com.pankajjangid.nytimespopulararticles.networking.response.PopularArticleResponse


class HomeRepository(private val myApi: MyApi) : SafeApiRequest() {

    suspend fun loadPopularArticles(period:String): PopularArticleResponse {
        return apiRequest { myApi.getPopularArticles(period,WebServiceUrl.API_KEY) }
    }

}