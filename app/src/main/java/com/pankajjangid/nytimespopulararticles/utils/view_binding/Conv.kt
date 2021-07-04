package com.pankajjangid.nytimespopulararticles.utils.view_binding

import androidx.databinding.InverseMethod
import java.text.NumberFormat
import java.util.*




object Conv {

    @JvmStatic
    @InverseMethod("toPrice")
    fun toPrice(

        value: Double
    ): String {
        var number: Double? = 0.0

        number = value.toDouble()

        val format = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 0
        format.currency = Currency.getInstance("INR")

        format.format(number)

        return format.format(number)


    }

    @JvmStatic
    @InverseMethod("toPrice")
    fun toPrice(

        value: String?
    ): String {
        var number: Double? = 0.0

        val format = NumberFormat.getCurrencyInstance()
        format.maximumFractionDigits = 0
        format.currency = Currency.getInstance("INR")

        format.format(number)

        return format.format(number)


    }


    @JvmStatic
    @InverseMethod("checkNull")
    fun checkNull(

        value: String?
    ): String {
        var s: String = ""


        if (value.isNullOrEmpty())
            s = "N/A"
        else
            s = value


        return s


    }

    @JvmStatic
    @InverseMethod("checkNullEmpty")
    fun checkNullEmpty(

        value: String?
    ): String {
        var s: String = ""


        s = if (value.isNullOrEmpty())
            ""
        else
            value


        return s


    }

    @JvmStatic
    @InverseMethod("textWithColon")
    fun textWithColon(

        title: String?,
        value: String?
    ): String {
        var s = ""


        s = if (value.isNullOrEmpty())
            "N/A"
        else
            "$title : $value"


        return s


    }


}