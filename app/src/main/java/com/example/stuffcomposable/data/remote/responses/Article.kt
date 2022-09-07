package com.example.stuffcomposable.data.remote.responses


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class Article(
    @Expose
    val allowComments: Boolean? = false,
    @SerializedName("alt_headline")
    @Expose
    val altHeadline: String = "",
    @SerializedName("alt_intro")
    @Expose
    val altIntro: String = "",
    @SerializedName("asset_type")
    @Expose
    val assetType: String = "",
    @Expose
    val body: String? = "",
    @Expose
    val byline: String = "",
    @SerializedName("category-description")
    @Expose
    val categoryDescription: String = "",
    @SerializedName("category_id")
    @Expose
    val categoryId: String? = "",
    @SerializedName("datetime_display")
    @Expose
    val datetimeDisplay: String = "",
    @SerializedName("datetime_iso8601")
    @Expose
    val datetimeIso8601: String = "",
    @Expose
    val galleries: List<Any>? = listOf(),
    @SerializedName("headline_flags")
    @Expose
    val headlineFlags: List<String> = listOf(),
    @SerializedName("html_assets")
    @Expose
    val htmlAssets: List<HtmlAsset>? = listOf(),
    @Expose
    val id: Int = 0,
    @Expose
    val identifier: String? = "",
    @SerializedName("image_overrides")
    @Expose
    val imageOverrides: List<ImageOverride>? = listOf(),
    @Expose
    val images: List<Image> = listOf(),
    @Expose
    val intro: String? = "",
    @Expose
    val isHeadlineOverrideApplied: Boolean = false,
    @Expose
    val layout: String? = "",
    @Expose
    val path: String = "",
    @Expose
    val section: String = "",
    @SerializedName("section-home")
    @Expose
    val sectionHome: String = "",
    @SerializedName("section-top-level")
    @Expose
    val sectionTopLevel: String = "",
    @SerializedName("source_code")
    @Expose
    val sourceCode: String? = "",
    @SerializedName("source_name")
    @Expose
    val sourceName: String? = "",
    @Expose
    val sponsored: Boolean? = false,
    @SerializedName("stream_id")
    @Expose
    val streamId: String? = "",
    @Expose
    val title: String = "",
    @Expose
    val url: String = "",
    @Expose
    val videos: List<Video>? = listOf()
)