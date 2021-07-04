package com.pankajjangid.nytimespopulararticles.networking.response
import com.google.gson.annotations.SerializedName


data class ErrorResponse(
    val fault: Fault
) {
    data class Fault(
        val detail: Detail,
        val faultstring: String
    ) {
        data class Detail(
            val errorcode: String
        )
    }
}