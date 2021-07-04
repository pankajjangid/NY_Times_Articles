package com.pankajjangid.nytimespopulararticles.utils

import android.app.Activity
import android.content.Context
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.Toast
import com.google.gson.Gson
import com.google.gson.JsonObject

object Utils {
    fun jsonObjectToString(jsonObject: JsonObject?): String {

        return Gson().toJson(jsonObject)
    }



    fun toast(message: String?) {

        Toast.makeText(App.application, "$message", Toast.LENGTH_SHORT).show()
    }

    fun hideSoftKeyboard(act: Activity) {
        try {
            if (act.currentFocus != null) {
                val inputMethodManager =
                    act.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
                inputMethodManager.hideSoftInputFromWindow(
                    act.currentFocus!!.windowToken,
                    0
                )
            }
        } catch (e: Exception) {
        }
    }

    fun showKeyboard(context: Context, view: View) {
        try {
            val imm =
                context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
            imm.showSoftInput(view, 0)
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }

}