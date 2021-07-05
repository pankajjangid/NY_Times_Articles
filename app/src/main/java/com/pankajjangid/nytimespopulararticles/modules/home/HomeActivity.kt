package com.pankajjangid.nytimespopulararticles.modules.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatDelegate
import androidx.appcompat.widget.Toolbar
import androidx.lifecycle.ViewModelProvider
import com.google.gson.Gson
import com.pankajjangid.nytimespopulararticles.R
import com.pankajjangid.nytimespopulararticles.base.BaseActivity
import com.pankajjangid.nytimespopulararticles.databinding.ActivityHomeBinding
import com.pankajjangid.nytimespopulararticles.modules.details.DetailsActivity
import com.pankajjangid.nytimespopulararticles.networking.NetworkUtils
import com.pankajjangid.nytimespopulararticles.networking.response.PopularArticleResponse
import com.pankajjangid.nytimespopulararticles.utils.App
import com.pankajjangid.nytimespopulararticles.utils.Extensions.toast
import kotlinx.android.synthetic.main.layout_toolbar.*

import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.kodein
import org.kodein.di.generic.instance


class HomeActivity : BaseActivity<ActivityHomeBinding>(), HomeListener,KodeinAware {
    private val factory: HomeViewModelFactory by instance()
    private lateinit var viewModel : HomeViewModel

    //Initialize the adapter
    private val adapter:HomeAdapter by lazy {
        HomeAdapter()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //Disable Night mode
        AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO);

        //Initialize the data binding
        setContentView(this,R.layout.activity_home)

        //Set the view model
        viewModel =  ViewModelProvider(this, factory).get(HomeViewModel::class.java)
        binding.viewModel = viewModel
        viewModel.listener=this

        //Hide the toolbar back button
        showToolbar.value = false

        //Set toolbar title
        toolbar.title =getString(R.string.popular_articles)

        //Set adapter to recyclerview
        binding.rvArticle.adapter = adapter
        binding.rvArticle.setHasFixedSize(true)

        //set listener
        adapter.listener = {
            view, item, position ->
            val itemString = Gson().toJson(item)
            val intent = Intent(this@HomeActivity, DetailsActivity::class.java)
            intent.putExtra(DetailsActivity.KEY_DETAILS, itemString)
            startActivity(intent)
        }


        // Call the api method
        loadPopularArticles()

    }

    //set the toolbar
    override fun getToolbarInstance(): Toolbar? = toolbar


    override fun loadPopularArticles() {

        viewModel.loadPopularArticles(period = "1")

    }

    //Show the progress bar
    override fun onLoading() {
        showProgress()
    }

    //Call when api response is successfully
    override fun onSuccess(responseData: Any?) {
        hideProgress()

        if (responseData is PopularArticleResponse){
            adapter.addItems(responseData.results)
        }

        Log.d("SUCCESS",".....")
    }

    //It call when response is failed
    override fun onFailure(message: String) {
        hideProgress()
        toast(message)
    }



    override val kodein: Kodein by kodein()

}