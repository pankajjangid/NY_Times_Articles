package com.pankajjangid.nytimespopulararticles.networking

import android.app.Activity
import com.pankajjangid.nytimespopulararticles.R


import com.pankajjangid.nytimespopulararticles.utils.App
import com.pankajjangid.nytimespopulararticles.utils.Extensions.toast
import okhttp3.ResponseBody
import org.json.JSONObject
import retrofit2.HttpException
import java.io.IOException
import java.net.SocketTimeoutException

object ApiUtils {


    fun handleRetrofitError(e: Throwable) {
        e.printStackTrace()

        when (e) {
            is HttpException -> {
                val responseBody: ResponseBody = e.response()!!.errorBody()!!
                App.application?.toast(getErrorMessage(responseBody))
            }
            is SocketTimeoutException -> {

                App.application?.resources?.getString(R.string.msg_socket_time_out)?.let {
                    App.application?.toast(
                        it
                    )
                }

            }
            is IOException -> {
                App.application?.resources?.getString(R.string.msg_api_error)?.let {
                    App.application?.toast(
                        it
                    )
                }
            }
            else -> {
                App.application?.resources?.getString(R.string.msg_api_error)?.let {
                    App.application?.toast(
                        it
                    )
                }
            }
        }
    }


    fun isTimeOut(e: Throwable): Boolean {
        if (e is SocketTimeoutException) {
            return true

        }
        return false
    }

    private fun getErrorMessage(responseBody: ResponseBody): String {
        try {
            val jsonObject = JSONObject(responseBody.string())
            return jsonObject.getString("message")
        } catch (e: Exception) {
            return e.message!!
        }
    }

    fun checkResponseStatus(
        activity: Activity,
        statusCode: Int,
        msg: String?,
        showSuccessMessage: Boolean = false,
        isActivity: Boolean
    ): Boolean {

        var message = ""

        if (msg.isNullOrEmpty()) message = "" else message = msg

        when (statusCode) {
            WebServiceUrl.STATUS_SUCCESS -> {
                if (showSuccessMessage)
                    App.application?.toast(message)
                return true
            }
            WebServiceUrl.STATUS_AUTH_FAILED -> {
                handleAuthFailed(activity, message, isActivity)
                return false
            }
            WebServiceUrl.STATUS_PARAMETER_MISSING -> {
                handleServerError(activity, message)
                return false
            }
            WebServiceUrl.STATUS_SESSION_EXPIRE -> {
                handleAuthFailed(activity, message, isActivity)
                return false
            }
            WebServiceUrl.STATUS_SERVER_ERROR -> {
                handleServerError(activity, message)
                return false
            }
            WebServiceUrl.STATUS_SERVER_400 -> {
                return false
            }
            else -> {
                handleServerError(activity, message)

                return false
            }
        }

    }

    private fun handleServerError(activity: Activity, msg: String) {
        App.application?.toast(msg)

    }

    private fun handleAuthFailed(activity: Activity, msg: String, isActivity: Boolean) {
        if (msg.isNotEmpty())
            App.application?.toast(msg)



        try {

            /*   if (isActivity)
                   (activity as BaseActivity<*>).logOut(activity)
               else
                   BaseFragment<*>*/

          //  logOut(activity)

        } catch (e: Exception) {
            e.printStackTrace()
        }
        /*
        Utils.startNewActivity(activity,LogOutActivity::class.java)
*/


    }





}
