package com.pankajjangid.nytimespopulararticles.modules.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.pankajjangid.nytimespopulararticles.R
import com.pankajjangid.nytimespopulararticles.base.BaseActivity
import com.pankajjangid.nytimespopulararticles.databinding.ActivityHomeBinding
import com.pankajjangid.nytimespopulararticles.utils.Extensions.toast
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class HomeActivity : BaseActivity<ActivityHomeBinding>(), HomeListener,KodeinAware {
    private val factory: HomeViewModelFactory by instance()
    private lateinit var viewModel : HomeViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)


        viewModel =  ViewModelProvider(this, factory).get(HomeViewModel::class.java)
        viewModel.listener=this

        loadPopularArticles()
    }

    override fun getToolbarInstance(): Toolbar? = null
    override fun loadPopularArticles() {

        viewModel.loadPopularArticles(period = "1")

    }

    override fun onLoading() {
        showProgress()
    }

    override fun onSuccess(responseData: Any?) {
        hideProgress()
        Log.d("SUCCESS",".....")
    }

    override fun onFailure(message: String) {
        hideProgress()
        toast(message)
    }

    override fun onFailure() {
        hideProgress()

    }

    override val kodein: Kodein by kodein()

}