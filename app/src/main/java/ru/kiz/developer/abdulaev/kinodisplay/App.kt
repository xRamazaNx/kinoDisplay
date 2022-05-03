package ru.kiz.developer.abdulaev.kinodisplay

import android.app.Application
import android.content.Context
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import ru.kiz.developer.abdulaev.kinodisplay.data.MovieServiceApi
import ru.kiz.developer.abdulaev.kinodisplay.ui.ViewModelFactoryProvider

fun Context.app() = applicationContext as App

class App : Application() {
    private lateinit var retrofit: Retrofit
    lateinit var movieServiceApi: MovieServiceApi
    lateinit var viewModelFactoryProvider: ViewModelFactoryProvider

    override fun onCreate() {
        super.onCreate()
        retrofit = Retrofit
            .Builder()
            .baseUrl(BuildConfig.baseUrl)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        movieServiceApi = retrofit.create(MovieServiceApi::class.java)
        viewModelFactoryProvider = ViewModelFactoryProvider.Base(this)
    }
}