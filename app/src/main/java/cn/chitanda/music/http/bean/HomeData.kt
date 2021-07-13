
package  cn.chitanda.music.http.bean

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class HomeData(
    @Json(name = "code")
    override val code: Int = 0,
    @Json(name = "data")
    override val data: Data? = Data(),
    @Json(name = "message")
    override val message: String? = "",
    @Json(name = "msg")
    override val msg: String? = "",
) : BaseJson<HomeData.Data?>() {
    @JsonClass(generateAdapter = true)
    data class Data(
        @Json(name = "blockUUIDs")
        val blockUUIDs: Any? = Any(),
        @Json(name = "blocks")
        val blocks: List<Block>? = listOf(),
        @Json(name = "cursor")
        val cursor: String? = "",
        @Json(name = "guideToast")
        val guideToast: GuideToast? = GuideToast(),
        @Json(name = "hasMore")
        val hasMore: Boolean? = false,
        @Json(name = "pageConfig")
        val pageConfig: PageConfig? = PageConfig()
    ) {
        @JsonClass(generateAdapter = true)
        data class Block(
            @Json(name = "action")
            val action: String? = "",
            @Json(name = "actionType")
            val actionType: String? = "",
            @Json(name = "blockCode")
            val blockCode: String? = "",
            @Json(name = "canClose")
            val canClose: Boolean? = false,
            @Json(name = "creatives")
            val creatives: List<Creative>? = listOf(),
            @Json(name = "extInfo")
            val extInfo: Any? = Any(),
            @Json(name = "showType")
            val showType: RCMDShowType? = RCMDShowType.Unknown,
            @Json(name = "uiElement")
            val uiElement: UiElement? = UiElement()
        ) {
            @JsonClass(generateAdapter = true)
            data class Creative(
                @Json(name = "action")
                val action: String? = "",
                @Json(name = "actionType")
                val actionType: String? = "",
                @Json(name = "alg")
                val alg: String? = "",
                @Json(name = "creativeId")
                val creativeId: String? = "",
                @Json(name = "creativeType")
                val creativeType: String? = "",
                @Json(name = "logInfo")
                val logInfo: String? = "",
                @Json(name = "position")
                val position: Long? = 0,
                @Json(name = "resources")
                val resources: List<Resource>? = listOf(),
                @Json(name = "uiElement")
                val uiElement: UiElement? = UiElement()
            ) {
                @JsonClass(generateAdapter = true)
                data class Resource(
                    @Json(name = "action")
                    val action: String? = "",
                    @Json(name = "actionType")
                    val actionType: String? = "",
                    @Json(name = "alg")
                    val alg: String? = "",
                    @Json(name = "logInfo")
                    val logInfo: String? = "",
                    @Json(name = "resourceExtInfo")
                    val resourceExtInfo: ResourceExtInfo? = ResourceExtInfo(),
                    @Json(name = "resourceId")
                    val resourceId: String? = "",
                    @Json(name = "resourceType")
                    val resourceType: String? = "",
                    @Json(name = "resourceUrl")
                    val resourceUrl: Any? = Any(),
                    @Json(name = "uiElement")
                    val uiElement: UiElement? = UiElement(),
                    @Json(name = "valid")
                    val valid: Boolean? = false
                ) {
                    @JsonClass(generateAdapter = true)
                    data class ResourceExtInfo(
                        @Json(name = "artists")
                        val artists: List<Artist>? = listOf(),
                        @Json(name = "commentSimpleData")
                        val commentSimpleData: CommentSimpleData? = CommentSimpleData(),
                        @Json(name = "highQuality")
                        val highQuality: Boolean? = false,
                        @Json(name = "playCount")
                        val playCount: Long? = 0,
                        @Json(name = "songData")
                        val songData: SongData? = SongData(),
                        @Json(name = "songPrivilege")
                        val songPrivilege: SongPrivilege? = SongPrivilege(),
                        @Json(name = "specialCover")
                        val specialCover: Long? = 0,
                        @Json(name = "specialType")
                        val specialType: Long? = 0,
                        @Json(name = "users")
                        val users: List<User?>? = listOf()
                    ) {

                        @JsonClass(generateAdapter = true)
                        data class CommentSimpleData(
                            @Json(name = "commentId")
                            val commentId: Long? = 0,
                            @Json(name = "content")
                            val content: String? = "",
                            @Json(name = "threadId")
                            val threadId: String? = "",
                            @Json(name = "userId")
                            val userId: Long? = 0,
                            @Json(name = "userName")
                            val userName: String? = ""
                        )

                        @JsonClass(generateAdapter = true)
                        data class SongData(
                            @Json(name = "album")
                            val album: Album? = Album(),
                            @Json(name = "alias")
                            val alias: List<Any?>? = listOf(),
                            @Json(name = "artists")
                            val artists: List<Artist?>? = listOf(),
                            @Json(name = "audition")
                            val audition: Any? = Any(),
                            @Json(name = "bMusic")
                            val bMusic: BMusic? = BMusic(),
                            @Json(name = "commentThreadId")
                            val commentThreadId: String? = "",
                            @Json(name = "copyFrom")
                            val copyFrom: String? = "",
                            @Json(name = "copyright")
                            val copyright: Long? = 0,
                            @Json(name = "copyrightId")
                            val copyrightId: Long? = 0,
                            @Json(name = "crbt")
                            val crbt: Any? = Any(),
                            @Json(name = "dayPlays")
                            val dayPlays: Long? = 0,
                            @Json(name = "disc")
                            val disc: String? = "",
                            @Json(name = "duration")
                            val duration: Long? = 0,
                            @Json(name = "fee")
                            val fee: Long? = 0,
                            @Json(name = "ftype")
                            val ftype: Long? = 0,
                            @Json(name = "hMusic")
                            val hMusic: Any? = Any(),
                            @Json(name = "hearTime")
                            val hearTime: Long? = 0,
                            @Json(name = "id")
                            val id: Long? = 0,
                            @Json(name = "lMusic")
                            val lMusic: LMusic? = LMusic(),
                            @Json(name = "mMusic")
                            val mMusic: MMusic? = MMusic(),
                            @Json(name = "mark")
                            val mark: Long? = 0,
                            @Json(name = "mp3Url")
                            val mp3Url: Any? = Any(),
                            @Json(name = "mvid")
                            val mvid: Long? = 0,
                            @Json(name = "name")
                            val name: String? = "",
                            @Json(name = "no")
                            val no: Long? = 0,
                            @Json(name = "noCopyrightRcmd")
                            val noCopyrightRcmd: Any? = Any(),
                            @Json(name = "originCoverType")
                            val originCoverType: Long? = 0,
                            @Json(name = "originSongSimpleData")
                            val originSongSimpleData: Any? = Any(),
                            @Json(name = "playedNum")
                            val playedNum: Long? = 0,
                            @Json(name = "popularity")
                            val popularity: Long? = 0,
                            @Json(name = "position")
                            val position: Long? = 0,
                            @Json(name = "ringtone")
                            val ringtone: Any? = Any(),
                            @Json(name = "rtUrl")
                            val rtUrl: Any? = Any(),
                            @Json(name = "rtUrls")
                            val rtUrls: List<Any?>? = listOf(),
                            @Json(name = "rtype")
                            val rtype: Long? = 0,
                            @Json(name = "rurl")
                            val rurl: Any? = Any(),
                            @Json(name = "score")
                            val score: Long? = 0,
                            @Json(name = "sign")
                            val sign: Any? = Any(),
                            @Json(name = "single")
                            val single: Long? = 0,
                            @Json(name = "starred")
                            val starred: Boolean? = false,
                            @Json(name = "starredNum")
                            val starredNum: Long? = 0,
                            @Json(name = "status")
                            val status: Long? = 0,
                            @Json(name = "transName")
                            val transName: Any? = Any(),
                            @Json(name = "videoInfo")
                            val videoInfo: Any? = Any()
                        ) {
                            @JsonClass(generateAdapter = true)
                            data class Album(
                                @Json(name = "alias")
                                val alias: List<Any?>? = listOf(),
                                @Json(name = "artist")
                                val artist: Artist? = Artist(),
                                @Json(name = "artists")
                                val artists: List<Artist?>? = listOf(),
                                @Json(name = "blurPicUrl")
                                val blurPicUrl: String? = "",
                                @Json(name = "briefDesc")
                                val briefDesc: String? = "",
                                @Json(name = "commentThreadId")
                                val commentThreadId: String? = "",
                                @Json(name = "company")
                                val company: String? = "",
                                @Json(name = "companyId")
                                val companyId: Long? = 0,
                                @Json(name = "copyrightId")
                                val copyrightId: Long? = 0,
                                @Json(name = "description")
                                val description: String? = "",