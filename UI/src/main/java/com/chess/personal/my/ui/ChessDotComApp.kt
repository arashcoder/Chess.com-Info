package com.chess.personal.my.ui

import android.app.Activity
import android.support.v4.app.Fragment
import com.chess.personal.my.preference.PrefsHelper
import com.chess.personal.my.ui.injection.DaggerApplicationComponent
import com.squareup.picasso.OkHttp3Downloader
import com.squareup.picasso.Picasso
import dagger.android.AndroidInjector
import dagger.android.DispatchingAndroidInjector
import dagger.android.HasActivityInjector
import dagger.android.support.HasSupportFragmentInjector
import okhttp3.OkHttpClient
import timber.log.Timber
import java.util.concurrent.TimeUnit
import javax.inject.Inject

class ChessDotComApp: android.app.Application(), HasActivityInjector, HasSupportFragmentInjector {

    @Inject
    lateinit var androidFragmentInjector: DispatchingAndroidInjector<Fragment>
    override fun supportFragmentInjector(): AndroidInjector<Fragment> {
        return androidFragmentInjector
    }

    @Inject
    lateinit var androidInjector: DispatchingAndroidInjector<Activity>

    override fun activityInjector(): AndroidInjector<Activity> {
        return androidInjector
    }


    companion object {

        private lateinit var instance: ChessDotComApp

        fun get(): ChessDotComApp {
            return instance
        }
    }
    lateinit var picasso: Picasso
    override fun onCreate() {
        super.onCreate()
        instance = this
        setupTimber()
        PrefsHelper.init(this)//TODO:

        DaggerApplicationComponent
                .builder()
                .application(this)
                .build()
                .inject(this)

        initPicasso()
    }

    private fun setupTimber() {
        Timber.plant(Timber.DebugTree())
    }

    private fun initPicasso() {
        val builder = OkHttpClient.Builder()
                .connectTimeout(30, TimeUnit.SECONDS)
                .readTimeout(30, TimeUnit.SECONDS)
                .writeTimeout(30, TimeUnit.SECONDS)
        picasso = Picasso.Builder(this)
                .downloader(OkHttp3Downloader(builder.build()))
                .build()
    }

}