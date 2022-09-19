package com.yusufmendes.basicnoteapp.di

import android.util.Log
import com.yusufmendes.basicnoteapp.network.service.Api
import com.yusufmendes.basicnoteapp.util.constants.Constants.BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Inject
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
open class RetrofitClient @Inject constructor() {

    @Provides
    @Singleton
    fun okHttpClient() = OkHttpClient.Builder()
        .addInterceptor(HttpLoggingInterceptor {
            Log.d("tag", it)
        }.apply {
            //logcat'te gostermek icin kullanıldı.
            level = HttpLoggingInterceptor.Level.BODY
        }).build()

    //retrofit islemleri
    @Provides
    @Singleton
    fun retrofitInstance(): Api =
        Retrofit.Builder()
            .baseUrl(BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(okHttpClient())
            .build()
            .create(Api::class.java)

}
