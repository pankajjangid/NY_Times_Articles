package com.pankajjangid.nytimespopulararticles.utils.view_binding

import android.graphics.Bitmap
import android.graphics.Paint
import android.graphics.drawable.BitmapDrawable
import android.graphics.drawable.Drawable
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import androidx.annotation.ColorInt
import androidx.appcompat.widget.SwitchCompat
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.databinding.BindingAdapter
import androidx.swiperefreshlayout.widget.CircularProgressDrawable
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.RequestOptions
import com.bumptech.glide.request.target.SimpleTarget
import com.bumptech.glide.request.target.Target
import com.bumptech.glide.request.transition.Transition
import com.pankajjangid.nytimespopulararticles.R
import com.pankajjangid.nytimespopulararticles.utils.Logger
import de.hdodenhof.circleimageview.CircleImageView


object BindingAdapter {
    val logger = Logger("CustomSetter")
    val requestOptions = RequestOptions().diskCacheStrategy(DiskCacheStrategy.ALL)

    /*
        @JvmStatic
        @BindingAdapter("android:loadDrawable")
        fun setEmojiText(view: TextView, unicode: String?) {
            try {
                if (unicode != null) {
                    view.text = String(Character.toChars(unicode))
                }
            } catch (e: Exception) {
                e.printStackTrace()
            }
        }*/

    @BindingAdapter("app:tint")
    fun ImageView.setImageTint(@ColorInt color: Int) {
        setColorFilter(color)
    }




    @JvmStatic
    @BindingAdapter("strikeThrough")
    fun strikeThrough(textView: TextView, text: String?) {

        if (text.isNullOrEmpty()) {
            textView.text = "N/A"
        } else {

            textView.paintFlags = textView.paintFlags or Paint.STRIKE_THRU_TEXT_FLAG

            textView.text = text
        }
    }

    @JvmStatic
    @BindingAdapter("android:loadDrawable")
    fun loadDrawable(view: ImageView, image: Int?) {
        try {
            if (image != null) {
                view.setImageResource(image)
            }
        } catch (e: Exception) {
            e.printStackTrace()
        }
    }




    @JvmStatic
    @BindingAdapter("app:backgroundTintFab")
    fun backgroundTintFab(view: ImageView, color: Int) {

        view.setColorFilter(color)

    }

    @JvmStatic
    @BindingAdapter("android:loadCircleImage")
    fun loadCircleImage(view: CircleImageView, imageUrl: String?) {

/*
        var url = ""
        url = if (!imageUrl.isNullOrEmpty() && imageUrl.startsWith("http")) {
            imageUrl
        } else {
            WebServiceUrl.BASE_URL_IMAGE + imageUrl
        }

        logger.debug(imageUrl + "")*/
        if (!imageUrl.isNullOrEmpty()) {

            val circularProgressDrawable = CircularProgressDrawable(view.context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()

            view.background = circularProgressDrawable


            val requestOptions = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
            // .placeholder(R.drawable.ic_placeholder)
      //          .placeholder(R.drawable.ic_user)
                .error(R.drawable.no_image)
            Glide.with(view.context)
                .load(imageUrl).apply(requestOptions)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {

                        view.background =ContextCompat.getDrawable(view.context,R.drawable.no_image)
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {

                        view.background = null

                        return false
                    }
                })

                .into(view)
        } else {

            Glide.with(view.context)
                .load(imageUrl).apply(requestOptions)
                .placeholder(R.drawable.no_image)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {

                        view.background = null

                        return false
                    }
                })

                .into(view)

            /* Glide.with(view.context)

                 .load(R.drawable.no_image).apply(RequestOptions())
                 .dontAnimate()

                 .into(view)*/
        }

    }

    @JvmStatic
    @BindingAdapter("android:loadCardImage")
    fun loadCardImage(view: ImageView, imageUrl: String?) {



        logger.debug(imageUrl + "")
        if (!imageUrl.isNullOrEmpty()) {
            var url = ""
            url = if (!imageUrl.isNullOrEmpty() && imageUrl.startsWith("http")) {
                imageUrl
            } else {
                WebServiceUrl.BASE_URL_IMAGE + imageUrl
            }


            val circularProgressDrawable = CircularProgressDrawable(view.context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()

            view.background = circularProgressDrawable


            val requestOptions = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
            // .placeholder(R.drawable.ic_placeholder)

            Glide.with(view.context)
                .load(url).apply(requestOptions)
                .error(R.drawable.no_image)
                .placeholder(R.drawable.no_image)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {

                        view.background = null

                        return false
                    }
                })

                .into(view)
        } else {
            Glide.with(view.context)

                .load(R.drawable.ic_person).apply(RequestOptions())
                .dontAnimate()

                .into(view)
        }

    }

    @JvmStatic
    @BindingAdapter("android:setOnBoardImage")
    fun setOnBoardImage(view: ConstraintLayout, imageUrl: String) {
        val circularProgressDrawable = CircularProgressDrawable(view.context)
        circularProgressDrawable.strokeWidth = 5f
        circularProgressDrawable.centerRadius = 30f
        circularProgressDrawable.start()

        view.background = circularProgressDrawable

        var url = ""
        url = if (imageUrl.startsWith("http")) {
            imageUrl
        } else {
            WebServiceUrl.BASE_URL_IMAGE + imageUrl
        }
        Glide.with(view.context).asBitmap().apply(requestOptions).load(url)
            .thumbnail(0.1f)
            .placeholder(circularProgressDrawable)
            .into(object : SimpleTarget<Bitmap?>() {
                override fun onResourceReady(
                    resource: Bitmap,
                    transition: Transition<in Bitmap?>?
                ) {

                    val d: Drawable = BitmapDrawable(view.context.resources, resource)
                    view.visibility = View.VISIBLE
                    view.background = d
                }

            })


    }


    @JvmStatic
    @BindingAdapter("android:loadImage")
    fun loadImage(view: ImageView, imageUrl: String?) {
        logger.debug(imageUrl + "")

        if (!imageUrl.isNullOrEmpty()) {

            val circularProgressDrawable = CircularProgressDrawable(view.context)
            circularProgressDrawable.strokeWidth = 5f
            circularProgressDrawable.centerRadius = 30f
            circularProgressDrawable.start()

            view.background = circularProgressDrawable

            var url = ""
            url = if (!imageUrl.isNullOrEmpty() && imageUrl.startsWith("http")) {
                imageUrl
            } else {
                WebServiceUrl.BASE_URL_IMAGE + imageUrl
            }


            val requestOptions = RequestOptions()
                .diskCacheStrategy(DiskCacheStrategy.ALL)
                .fitCenter()
            // .placeholder(R.drawable.ic_placeholder)

            Glide.with(view.context)
                .load(url).apply(requestOptions)
                .dontAnimate()

                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        return false
                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {

                        view.background = null

                        return false
                    }
                })
                .into(view)

        } else {
              Glide.with(view.context)

                  .load(R.drawable.ic_person).apply(RequestOptions())
                  .dontAnimate()

                  .into(view)
        }

    }


    @JvmStatic
    @BindingAdapter("android:checkNull")
    fun checkNull(view: TextView, string: String?) {
        if (string.isNullOrEmpty())
            view.text = view.context.getString(R.string.na)
        else
            view.text = string

    }

    @JvmStatic
    @BindingAdapter("android:capitalizeText")
    fun capitalizeText(view: TextView, string: String?) {
        if (string.isNullOrEmpty())
            view.text = view.context.getString(R.string.na)
        else
            view.text = string.toLowerCase().capitalize()

    }


    @JvmStatic
    @BindingAdapter(value = ["onValueChangeListener"])
    fun setOnValueChangeListener(slider: Slider, listener: OnValueChangeListener) {
        slider.addOnChangeListener { _: Slider?, value: Float, _: Boolean ->
            listener.onValueChanged(value)
        }
    }

    @JvmStatic
    @BindingAdapter(value = ["onCheckedChangeListener"])
    fun setOnCheckedChange(switch: SwitchCompat, listener: OnCheckedChangeListener) {
        switch.setOnCheckedChangeListener { buttonView, isChecked ->
            listener.onValueChanged(isChecked)
        }

    }

    interface OnValueChangeListener {
        fun onValueChanged(value: Float)
    }

    interface OnCheckedChangeListener {
        fun onValueChanged(isChecked: Boolean)
    }


}