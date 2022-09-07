package com.example.stuffcomposable.data.remote.responses


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class ImageOverride(
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
    @SerializedName("source_code")
    @Expose
    val sourceCode: String? = null,
    @SerializedName("source_name")
    @Expose
    val sourceName: String = "",
    @Expose
    val variants: List<Variant> = listOf()
)