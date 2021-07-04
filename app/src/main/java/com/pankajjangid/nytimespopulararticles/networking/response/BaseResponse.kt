package com.pankajjangid.nytimespopulararticles.networking.response
import com.google.gson.annotations.SerializedName


data class BaseResponse(
    @SerializedName("message")
    val message: String,
    @SerializedName("status")
    val status: String,
    @SerializedName("status_code")
    val statusCode: String
)