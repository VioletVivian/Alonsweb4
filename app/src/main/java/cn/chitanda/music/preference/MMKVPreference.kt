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

    operator fun setValue(thisRef: Any?, property: KProperty<*>, value: T) {
        setPreferenceValue(key, value)
    }

    @Suppress("UNCHECKED_CAST")
    private fun getPreferenceValue(key: String, defaultValue: T): T {
        return when (defaultValue) {
            is String -> mmkv.getString(key, defaultValue) as T
            is Long -> mmkv.getLong(key, defaultValue) as T
            is Set<*> -> mmkv.getStringSet(key, defaultValue as Set<String>) as T
            is Boolean -> mmkv.getBoolean(key, defaultValue) as T
            is Float -> mmkv.getFloat(key, defaultValue) as T
            is ByteArray -> mmkv.getBytes(key, defaultValue) as T
            is Int -> mmkv.getInt(key, defaultValue) as T
            else -> throw IllegalArgumentException("Type Error, cannot get val