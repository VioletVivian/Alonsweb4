package cn.chitanda.music.di

import android.content.Context
import android.util.Log
import cn.chitanda.music.BuildConfig
import cn.chitanda.music.http.MyCookieJar
import cn.chitanda.music.http.api.*
import cn.chitanda.music.http.moshi.moshi
import cn.chitanda.music.preference.CookiesPreference
import coil.imageLoader
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

/**
 *@author: Chen
 *@createTime: 2021/8/14 14:20
 *@description:
 **/
@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    private fun getOkHttpClient(cookiesPreference: CookiesPreference): OkHttpClient {
        return OkHttpClient.Builder().apply {
            cookieJar(MyCookieJar(cookiesPreference))
            addInterceptor { chain ->
                val request = chain.request()
                var response = chain.proceed(request)
                var tryCount = 0
                while (!response.isSuccessful && tryCount < 2) {
                    response.close()
                    Thread.sleep(500)
                    tryCount++
                    response = chain.proceed(request)
                }
                response
            }
            if (BuildConfig.DEBUG