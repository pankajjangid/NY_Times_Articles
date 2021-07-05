package com.pankajjangid.nytimespopulararticles.modules.details

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.MenuItem
import androidx.appcompat.widget.Toolbar
import com.google.gson.Gson
import com.pankajjangid.nytimespopulararticles.R
import com.pankajjangid.nytimespopulararticles.base.BaseActivity
import com.pankajjangid.nytimespopulararticles.databinding.ActivityDetailsBinding
import com.pankajjangid.nytimespopulararticles.networking.response.PopularArticleResponse
import com.pankajjangid.nytimespopulararticles.utils.view_binding.BindingAdapter
import kotlinx.android.synthetic.main.layout_toolbar.*


class DetailsActivity : BaseActivity<ActivityDetailsBinding>() {


    companion object{
        const val KEY_DETAILS = "DETAILS"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(this,R.layout.activity_details)

        //Show the toolbar back button
        showToolbar.value = true

        //Set toolbar title
        toolbar.title =getString(R.string.details)

        if (intent.getStringExtra(KEY_DETAILS)!=null){

            val stringExtra = intent.getStringExtra(KEY_DETAILS)
            val dataObj: PopularArticleResponse.Result = Gson().fromJson(
                stringExtra,
                PopularArticleResponse.Result::class.java
            )

            setDetails(dataObj)


        }


    }

    private fun setDetails(dataObj: PopularArticleResponse.Result) {

        BindingAdapter.checkNull(binding.tvTitle,dataObj.title)
        BindingAdapter.checkNull(binding.tvDesc,dataObj.abstract)
        BindingAdapter.checkNull(binding.tvBy,dataObj.byline)
        BindingAdapter.checkNull(binding.tvDate,dataObj.publishedDate)

        if (!dataObj.media.isNullOrEmpty()){
            BindingAdapter.loadCardImage(binding.imgPoster, dataObj.media[0]?.mediaMetadata?.get(2)?.url)

        }else{
            binding.imgPoster.setImageResource(R.drawable.no_image)
        }

        binding.tvUrl.setOnClickListener {
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(dataObj.url))
            // Note the Chooser below. If no applications match,
            // Android displays a system message.So here there is no need for try-catch.
            // Note the Chooser below. If no applications match,
            // Android displays a system message.So here there is no need for try-catch.
            startActivity(Intent.createChooser(intent, "Browse with"))
        }


    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {

            android.R.id.home -> {
                onBackPressed()
            }


        }
        return super.onOptionsItemSelected(item)
    }
    override fun getToolbarInstance(): Toolbar? = toolbar
}