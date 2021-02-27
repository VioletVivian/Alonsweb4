package cn.chitanda.music.http.api

import cn.chitanda.music.http.bean.PlaylistDetail
import cn.chitanda.music.http.bean.SongUrl
import cn.chitanda.music.http.bean.Songs
import retrofit2.http.GET
import retrofit2.http.Query

/**
 * @author: Chen
 * @createTime: 2022/1/17 16:21
 * @description:
 **/
interface SongsApi {

    @GE