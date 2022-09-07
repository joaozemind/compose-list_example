package com.example.stuffcomposable.data.remote.responses


import com.google.gson.annotations.SerializedName
import com.google.gson.annotations.Expose

data class HtmlAsset(
    @SerializedName("asset_type")
    @Expose
    val assetType: String = "",
    @SerializedName("data_content")
    @Expose
    val dataContent: String = "",
    @Expose
    val id: Int = 0,
    @SerializedName("position_after_paragraph")
    @Expose
    val positionAfterParagraph: Int = 0
)