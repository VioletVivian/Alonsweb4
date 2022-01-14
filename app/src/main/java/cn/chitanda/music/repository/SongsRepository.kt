package cn.chitanda.music.repository

import cn.chitanda.music.http.api.SongsApi
import cn.chitanda.music.ui.scene.playlist.PlaylistViewState
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

/**
 * @author: Chen
 * @createTime: 2022/1/17 16:21
 * @description:
 **/
class SongsRepository(private val api: SongsApi) : BaseRemoteRepository() {
    suspend fun getPlaylistDetail(id: String) = withContext(Dispatchers.