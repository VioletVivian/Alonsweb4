
package cn.chitanda.music.repository

import cn.chitanda.music.http.api.HomeApi
import cn.chitanda.music.http.bean.HomeBanner
import cn.chitanda.music.http.bean.MLogExtInfo
import cn.chitanda.music.http.bean.RCMDShowType
import cn.chitanda.music.http.moshi.moshi
import cn.chitanda.music.ui.scene.PageState
import cn.chitanda.music.ui.scene.home.HomeViewState
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Types
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.withContext

/**
 *@author: Chen
 *@createTime: 2021/9/8 16:32
 *@description:
 **/
class HomeRepository(private val api: HomeApi) : BaseRemoteRepository() {

    suspend fun loadHomeData(refresh: Boolean = false) = withContext(Dispatchers.IO) {