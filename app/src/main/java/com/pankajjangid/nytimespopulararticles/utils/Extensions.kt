package com.pankajjangid.nytimespopulararticles.utils

import android.content.Context
import android.content.Intent
import android.view.Gravity
import android.view.View
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

object Extensions {

    fun View.show() {

        this.visibility = View.VISIBLE
    }

    fun View.hide() {

        this.visibility = View.GONE
    }

    fun Context.toast(message: String) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_SHORT)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

    fun Context.toastLong(message: String) {
        val toast = Toast.makeText(this, message, Toast.LENGTH_LONG)
        toast.setGravity(Gravity.CENTER, 0, 0)
        toast.show()
    }

    fun Context.startNewActivity(aClass: Class<*>) {
        this.startActivity(Intent(this, aClass))
    }




}