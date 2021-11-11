package cn.chitanda.music.preference

import com.tencent.mmkv.MMKV
import kotlin.reflect.KProperty

/**
 *@author: Chen
 *@createTime: 2021/8/13 16:44
 *@description:
 **/

open class MMKVPreference<T>(
    private val mmkv: MMKV,
