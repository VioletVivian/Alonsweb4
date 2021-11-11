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
    private val key: String,
    private val defaultValue: T
) {

    operator fun getValue(thisRef: Any?, property: KProperty<*>): T {
        return getPreferenceValue(key, defaultValue)
    }

    operator fun setValue(thisRef: Any?, property: KPro