package com.pankajjangid.nytimespopulararticles.modules.details

import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.pankajjangid.nytimespopulararticles.R
import com.pankajjangid.nytimespopulararticles.base.BaseActivity
import com.pankajjangid.nytimespopulararticles.databinding.ActivityDetailsBinding


class DetailsActivity : BaseActivity<ActivityDetailsBinding>() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)
    }

    override fun getToolbarInstance(): Toolbar? = null
}