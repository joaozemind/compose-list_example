package com.example.stuffcomposable.data.remote.responses


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Video(
    @SerializedName("asset_type")
    @Expose
    val assetType: String = "",
    @Expose
    val caption: String = "",
    @Expose
    val creditline: String = "",
    @SerializedName("datetime_display")
    @Expose
    val datetimeDisplay: String = "",
    @SerializedName("datetime_iso8601")
    @Expose
    val datetimeIso8601: String = "",
    @Expose
    val id: Int = 0,
    @Expose
    val layout: String = "",
    @SerializedName("position_after_paragraph")
    @Expose
    val positionAfterParagraph: Int = 0,
    @SerializedName("source_code")
    @Expose
    val sourceCode: String = "",
    @SerializedName("source_name")
    @Expose
    val sourceName: String? = null,
    @Expose
    val src: String = "",
    @SerializedName("video_poster")
    @Expose
    val videoPoster: VideoPoster = VideoPoster()
)