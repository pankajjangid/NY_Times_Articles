package com.pankajjangid.nytimespopulararticles.utils

import android.annotation.SuppressLint
import android.content.Context
import android.os.StrictMode
import androidx.multidex.MultiDex
import androidx.multidex.MultiDexApplication
import com.pankajjangid.nytimespopulararticles.R
import com.pankajjangid.nytimespopulararticles.modules.home.HomeRepository
import com.pankajjangid.nytimespopulararticles.modules.home.HomeViewModelFactory
import com.pankajjangid.nytimespopulararticles.networking.MyApi
import com.pankajjangid.nytimespopulararticles.networking.NetworkConnectionInterceptor
import com.pankajjangid.nytimespopulararticles.utils.Extensions.toast


import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.androidXModule
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.provider
import org.kodein.di.generic.singleton


class App : MultiDexApplication(), KodeinAware {


    companion object {

        var application: App? = null
        @SuppressLint("StaticFieldLeak")

        val TAG = "MyApp"

        @SuppressLint("StaticFieldLeak")
        lateinit var networkStatus: ConnectivityLiveData


        fun getInstance(): App? {
            return application
        }





    }

    override fun attachBaseContext(base: Context?) {
        super.attachBaseContext(base)
        MultiDex.install(this)
    }

    override fun onCreate() {
        super.onCreate()

        application = this
        networkStatus = ConnectivityLiveData(this)





    }

    fun isConnected(): Boolean {
        return if (networkStatus.value!!.isConnected) {


            true
        } else {

            toast(application!!.getString(R.string.no_internet_connection))
            false
        }
    }




    override val kodein = Kodein.lazy {
        import(androidXModule(this@App))

        bind() from singleton { NetworkConnectionInterceptor(instance()) }
        bind() from singleton { MyApi(instance()) }

        bind() from singleton { HomeRepository(instance()) }

        bind() from provider { HomeViewModelFactory(instance()) }



    }


}