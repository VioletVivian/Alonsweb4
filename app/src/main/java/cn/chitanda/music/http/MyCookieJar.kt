package cn.chitanda.music.http

import cn.chitanda.music.http.moshi.moshi
import cn.chitanda.music.preference.CookiesPreference
import okhttp3.Cookie
import okhttp3.CookieJar
import okhttp3.HttpUrl

private const val TAG = "MyCookieJar"

class MyCookieJar(cookiesPreference: CookiesPreference) : Cook