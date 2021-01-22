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
import