package com.pankajjangid.nytimespopulararticles.modules.home

import android.os.Bundle
import android.util.Log
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.pankajjangid.nytimespopulararticles.R
import com.pankajjangid.nytimespopulararticles.base.BaseActivity
import com.pankajjangid.nytimespopulararticles.databinding.ActivityHomeBinding
import com.pankajjangid.nytimespopulararticles.networking.response.PopularArticleResponse
import com.pankajjangid.nytimespopulararticles.utils.Extensions.toast
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance

class HomeActivity : BaseActivity<ActivityHomeBinding>(), HomeListener,KodeinAware {
    private val factory: HomeViewModelFactory by instance()
    private lateinit var viewModel : HomeViewModel

    private val adapter:HomeAdapter by lazy {
        HomeAdapter()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(this,R.layout.activity_home)

        viewModel =  ViewModelProvider(this, factory).get(HomeViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.listener=this


        binding.rvArticle.adapter = adapter
        binding.rvArticle.setHasFixedSize(true)

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

        if (responseData is PopularArticleResponse){
            adapter.addItems(responseData.results)
        }

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