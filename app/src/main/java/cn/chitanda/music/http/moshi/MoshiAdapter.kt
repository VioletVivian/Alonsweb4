
package cn.chitanda.music.http.moshi

import cn.chitanda.music.http.bean.HomeBanner
import cn.chitanda.music.http.bean.HomeRoundIcon
import cn.chitanda.music.http.bean.RCMDShowType
import cn.chitanda.music.http.bean.SubTitleType
import com.squareup.moshi.FromJson
import com.squareup.moshi.JsonReader
import com.squareup.moshi.JsonWriter
import com.squareup.moshi.Moshi
import com.squareup.moshi.ToJson
import com.squareup.moshi.internal.Util
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import java.util.*

/**
 *@author: Chen
 *@createTime: 2021/8/14 22:27
 *@description:
 **/

val moshi: Moshi by lazy {
    Moshi.Builder()
//        .add(StringAdapter())
//        .add(IntAdapter())
//        .add(FloatAdapter())
//        .add(DoubleAdapter())
//        .add(BooleanAdapter())
//        .add(LongAdapter())
//        .add(AnyAdapter())
        .add(BannerTagColorAdapter())
        .add(RCMDShowTypeAdapter())
        .add(HomeIconTypeAdapter())
        .add(SubTitleTypeAdapter())
        .add(KotlinJsonAdapterFactory())
        .build()
}

class BannerTagColorAdapter {
    @FromJson
    fun fromJson(reader: JsonReader): HomeBanner.Banner.TagColor {
        val color = if (reader.peek() != JsonReader.Token.NULL) {
            reader.nextString()
        } else {
            ""
        }
        return when (color) {
            "blue" -> HomeBanner.Banner.TagColor.Blue
            else -> HomeBanner.Banner.TagColor.Red
        }
    }

    @ToJson
    fun toJson(writer: JsonWriter, value: HomeBanner.Banner.TagColor?) {
        writer.value(
            when (value) {
                null -> ""
                HomeBanner.Banner.TagColor.Blue -> "blue"
                HomeBanner.Banner.TagColor.Red -> "red"
            }
        )
    }
}

class RCMDShowTypeAdapter {
    @FromJson
    fun fromJson(reader: JsonReader): RCMDShowType {
        val showType = if (reader.peek() != JsonReader.Token.NULL) {
            reader.nextString()
        } else {
            ""
        }
        return when (showType) {
            RCMDShowType.Banner.type -> RCMDShowType.Banner
            RCMDShowType.PlayList.type -> RCMDShowType.PlayList
            RCMDShowType.SongList.type -> RCMDShowType.SongList
            RCMDShowType.PlayableMLog.type -> RCMDShowType.PlayableMLog
            else -> RCMDShowType.Unknown
        }
    }

    @ToJson
    fun toJson(writer: JsonWriter, value: RCMDShowType?) {
        writer.value(value?.type)
    }
}

class SubTitleTypeAdapter {
    @FromJson
    fun fromJson(reader: JsonReader): SubTitleType {
        val showType = if (reader.peek() != JsonReader.Token.NULL) {
            reader.nextString()
        } else {
            ""
        }
        return when (showType) {
            SubTitleType.FromComment.type -> SubTitleType.FromComment
            else -> SubTitleType.TAG
        }
    }

    @ToJson
    fun toJson(writer: JsonWriter, value: SubTitleType?) {
        writer.value(value?.type)
    }
}

class HomeIconTypeAdapter {
    @FromJson
    fun fromJson(reader: JsonReader): HomeRoundIcon.Type {
        val type = if (reader.peek() != JsonReader.Token.NULL) {
            reader.nextInt()
        } else {
            Int.MIN_VALUE
        }
        return when (type) {
            HomeRoundIcon.Type.DailyRCMD.id -> HomeRoundIcon.Type.DailyRCMD
            HomeRoundIcon.Type.PersonalFM.id -> HomeRoundIcon.Type.PersonalFM
            HomeRoundIcon.Type.PlayListCollection.id -> HomeRoundIcon.Type.PlayListCollection
            HomeRoundIcon.Type.Leaderboard.id -> HomeRoundIcon.Type.Leaderboard
            HomeRoundIcon.Type.DigitalAlbum.id -> HomeRoundIcon.Type.DigitalAlbum
            HomeRoundIcon.Type.Meditation.id -> HomeRoundIcon.Type.Meditation
            HomeRoundIcon.Type.SongRoom.id -> HomeRoundIcon.Type.SongRoom
            HomeRoundIcon.Type.Game.id -> HomeRoundIcon.Type.Game
            else -> HomeRoundIcon.Type.Unknown
        }
    }

    @ToJson
    fun toJson(writer: JsonWriter, value: HomeRoundIcon.Type?) {
        writer.value(value?.id)
    }
}

class StringAdapter {
    @FromJson
    fun fromJson(reader: JsonReader): String {
        if (reader.peek() != JsonReader.Token.NULL) {
            return reader.nextString()
        }
        reader.nextNull<Unit>()
        return ""

    }

    @ToJson
    fun toJson(writer: JsonWriter, value: String?) {
        writer.value(value.toString())
    }
}

class FloatAdapter {
    @FromJson
    fun fromJson(reader: JsonReader): Float {
        with(reader) {
            return when (peek()) {
                JsonReader.Token.NULL -> {
                    nextNull<Any>()
                    0f
                }
                else -> {
                    nextDouble().toFloat()
                }
            }
        }
    }

    @ToJson
    fun toJson(writer: JsonWriter, value: Float?) {
        writer.value(value.toString())
    }
}

class IntAdapter {
    @FromJson
    fun fromJson(reader: JsonReader): Int {
        with(reader) {
            return when (peek()) {
                JsonReader.Token.NULL -> {
                    nextNull<Any>()
                    0
                }
                else -> {
                    nextInt()
                }
            }
        }
    }

    @ToJson
    fun toJson(writer: JsonWriter, value: Int?) {
        writer.value(value.toString())
    }
}

class DoubleAdapter {
    @FromJson
    fun fromJson(reader: JsonReader): Double {
        with(reader) {
            return when (peek()) {
                JsonReader.Token.NULL -> {
                    nextNull<Any>()
                    0.toDouble()
                }