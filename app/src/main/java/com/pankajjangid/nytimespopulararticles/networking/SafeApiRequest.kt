package com.pankajjangid.nytimespopulararticles.networking

import com.google.gson.Gson
import com.pankajjangid.nytimespopulararticles.networking.response.ErrorResponse
import org.json.JSONException
import retrofit2.Response

abstract class SafeApiRequest {

    suspend fun<T: Any> apiRequest(call: suspend () -> Response<T>) : T{

        val response = call.invoke()

        if(response.isSuccessful){

            return response.body()!!
        }else{

            val error = response.errorBody()?.toString()

            val errorResponse = Gson().fromJson(response.errorBody()!!.charStream(), ErrorResponse::class.java)

            val message = StringBuilder()
            error?.let{
                try{
                    message.append(errorResponse.fault.faultstring)

                }catch(e: JSONException){
                   // Utils.printSentry(e)
                    e.printStackTrace()
                }
             //   message.append("\n")
            }
           // message.append("Error Code: ${errorResponse.message}")
            throw ApiException(message.toString())
        }
    }

}
